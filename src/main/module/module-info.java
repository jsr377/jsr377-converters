/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2015-2020 the original author or authors.
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

module org.kordamp.jsr377.converters {
    exports org.kordamp.jsr377.converter;
    exports org.kordamp.jsr377.formatter;

    requires javax.application.converter;
    requires javax.application.converter.spi;

    provides javax.application.converter.spi.ConverterProvider
        with org.kordamp.jsr377.converter.spi.BigDecimalConverterProvider,
             org.kordamp.jsr377.converter.spi.BigIntegerConverterProvider,
             org.kordamp.jsr377.converter.spi.BooleanConverterProvider,
             org.kordamp.jsr377.converter.spi.ByteConverterProvider,
             org.kordamp.jsr377.converter.spi.CalendarConverterProvider,
             org.kordamp.jsr377.converter.spi.DateConverterProvider,
             org.kordamp.jsr377.converter.spi.DoubleConverterProvider,
             org.kordamp.jsr377.converter.spi.FileConverterProvider,
             org.kordamp.jsr377.converter.spi.FloatConverterProvider,
             org.kordamp.jsr377.converter.spi.IntegerConverterProvider,
             org.kordamp.jsr377.converter.spi.LocalDateConverterProvider,
             org.kordamp.jsr377.converter.spi.LocalDateTimeConverterProvider,
             org.kordamp.jsr377.converter.spi.LocalTimeConverterProvider,
             org.kordamp.jsr377.converter.spi.LocaleConverterProvider,
             org.kordamp.jsr377.converter.spi.LongConverterProvider,
             org.kordamp.jsr377.converter.spi.PathConverterProvider,
             org.kordamp.jsr377.converter.spi.ShortConverterProvider,
             org.kordamp.jsr377.converter.spi.StringConverterProvider,
             org.kordamp.jsr377.converter.spi.URIConverterProvider,
             org.kordamp.jsr377.converter.spi.URLConverterProvider;
}