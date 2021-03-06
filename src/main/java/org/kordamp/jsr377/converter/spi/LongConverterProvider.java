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
package org.kordamp.jsr377.converter.spi;

import org.kordamp.jsr377.converter.LongConverter;

import javax.application.converter.Converter;
import javax.application.converter.spi.ConverterProvider;

/**
 * @author Andres Almiray
 */
public class LongConverterProvider implements ConverterProvider<Long> {
    @Override
    public Class<Long> getTargetType() {
        return Long.class;
    }

    @Override
    public Class<? extends Converter<Long>> getConverterType() {
        return LongConverter.class;
    }
}
