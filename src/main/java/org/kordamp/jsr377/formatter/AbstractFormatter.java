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
package org.kordamp.jsr377.formatter;

import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public abstract class AbstractFormatter<T> implements Formatter<T> {
    protected static ParseException parseError(Object value, Class<?> klass) throws ParseException {
        throw new ParseException("Can't format '" + value + "' into " + requireNonNull(klass).getName());
    }

    protected static ParseException parseError(Object value, Class<?> klass, Exception e) throws ParseException {
        throw new ParseException("Can't format '" + value + "' into " + requireNonNull(klass).getName(), requireNonNull(e));
    }

    /**
     * <p>Determines whether a given string is <code>null</code>, empty,
     * or only contains whitespace. If it contains anything other than
     * whitespace then the string is not considered to be blank and the
     * method returns <code>false</code>.</p>
     *
     * @param str The string to test.
     *
     * @return <code>true</code> if the string is <code>null</code>, or
     * blank.
     */
    protected static boolean isBlank(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                return false;
            }
        }

        return true;
    }
}
