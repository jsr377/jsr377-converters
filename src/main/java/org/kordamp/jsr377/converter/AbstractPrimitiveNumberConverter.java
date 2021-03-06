/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kordamp.jsr377.converter;

import javax.application.converter.ConversionException;

/**
 * @author Andres Almiray
 */
public abstract class AbstractPrimitiveNumberConverter<T extends Number> extends AbstractPrimitiveConverter<T> implements NumberConverter<T> {
    protected abstract Class<T> getTypeClass();

    @Override
    protected T doConvertFromObject(Object value) throws ConversionException {
        Class<T> typeClass = getTypeClass();
        if (typeClass.isAssignableFrom(value.getClass())) {
            return typeClass.cast(value);
        } else if (value instanceof Number) {
            return convertFromNumber((Number) value);
        } else if (value instanceof CharSequence) {
            return convertFromString(String.valueOf(value).trim());
        }
        throw illegalValue(value, typeClass);
    }

    protected T convertFromString(String str) {
        if (isBlank(str)) {
            if (isNullAccepted()) {
                return null;
            } else {
                T defaultValue = getDefaultValue();
                if (null != defaultValue) {
                    return defaultValue;
                } else {
                    throw new ConversionException(getClass().getSimpleName() + " does not accept empty input");
                }
            }
        }
        return doConvertFromString(str);
    }

    protected abstract T convertFromNumber(Number value);

    protected abstract T doConvertFromString(String value) throws ConversionException;
}
