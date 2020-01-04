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
package org.kordamp.jsr377.converter.spi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kordamp.jsr377.converter.BigDecimalConverter;
import org.kordamp.jsr377.converter.BigIntegerConverter;
import org.kordamp.jsr377.converter.BooleanConverter;
import org.kordamp.jsr377.converter.ByteConverter;
import org.kordamp.jsr377.converter.CalendarConverter;
import org.kordamp.jsr377.converter.DateConverter;
import org.kordamp.jsr377.converter.DoubleConverter;
import org.kordamp.jsr377.converter.FileConverter;
import org.kordamp.jsr377.converter.FloatConverter;
import org.kordamp.jsr377.converter.IntegerConverter;
import org.kordamp.jsr377.converter.LocalDateConverter;
import org.kordamp.jsr377.converter.LocalDateTimeConverter;
import org.kordamp.jsr377.converter.LocalTimeConverter;
import org.kordamp.jsr377.converter.LocaleConverter;
import org.kordamp.jsr377.converter.LongConverter;
import org.kordamp.jsr377.converter.PathConverter;
import org.kordamp.jsr377.converter.ShortConverter;
import org.kordamp.jsr377.converter.StringConverter;
import org.kordamp.jsr377.converter.URIConverter;
import org.kordamp.jsr377.converter.URLConverter;

import javax.application.converter.Converter;
import javax.application.converter.spi.ConverterProvider;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ServiceLoader;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Andres Almiray
 */
public class ConverterProviderTest {
    @ParameterizedTest
    @MethodSource("where_types")
    @SuppressWarnings("rawtypes")
    public <T> void loadAndCheckConverterProvider(Class<T> targetType, Class<? extends Converter<T>> converterType) {
        // given:
        ServiceLoader<ConverterProvider> providers = ServiceLoader.load(ConverterProvider.class);

        // when:
        ConverterProvider converterProvider = StreamSupport.stream(providers.spliterator(), false)
            .filter(cp -> targetType.equals(cp.getTargetType()))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Did not find a ConverterProvider for target type " + targetType.getName()));

        // then:
        assertThat(converterProvider.getConverterType(), equalTo(converterType));
    }

    public static Stream<Arguments> where_types() {
        return Stream.of(
            Arguments.of(BigDecimal.class, BigDecimalConverter.class),
            Arguments.of(BigInteger.class, BigIntegerConverter.class),
            Arguments.of(Boolean.class, BooleanConverter.class),
            Arguments.of(Byte.class, ByteConverter.class),
            Arguments.of(Calendar.class, CalendarConverter.class),
            Arguments.of(Date.class, DateConverter.class),
            Arguments.of(Double.class, DoubleConverter.class),
            Arguments.of(File.class, FileConverter.class),
            Arguments.of(Float.class, FloatConverter.class),
            Arguments.of(Integer.class, IntegerConverter.class),
            Arguments.of(LocalDate.class, LocalDateConverter.class),
            Arguments.of(LocalDateTime.class, LocalDateTimeConverter.class),
            Arguments.of(LocalTime.class, LocalTimeConverter.class),
            Arguments.of(Locale.class, LocaleConverter.class),
            Arguments.of(Long.class, LongConverter.class),
            Arguments.of(Path.class, PathConverter.class),
            Arguments.of(Short.class, ShortConverter.class),
            Arguments.of(String.class, StringConverter.class),
            Arguments.of(URI.class, URIConverter.class),
            Arguments.of(URL.class, URLConverter.class)
        );
    }
}
