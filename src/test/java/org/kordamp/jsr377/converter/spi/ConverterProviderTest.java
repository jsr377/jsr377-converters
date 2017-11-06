/*
 * Copyright 2015-2017 the original author or authors.
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

import junit.framework.AssertionFailedError;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
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
import java.util.stream.StreamSupport;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
@RunWith(JUnitParamsRunner.class)
public class ConverterProviderTest {
    @Test
    @Parameters(method = "where_types")
    @SuppressWarnings("rawtypes")
    public <T> void loadAndCheckConverterProvider(Class<T> targetType, Class<? extends Converter<T>> converterType) {
        // given:
        ServiceLoader<ConverterProvider> providers = ServiceLoader.load(ConverterProvider.class);

        // when:
        ConverterProvider converterProvider = StreamSupport.stream(providers.spliterator(), false)
            .filter(cp -> targetType.equals(cp.getTargetType()))
            .findFirst()
            .orElseThrow(() -> new AssertionFailedError("Did not find a ConverterProvider for target type " + targetType.getName()));

        // then:
        assertThat(converterProvider.getConverterType(), equalTo(converterType));
    }

    protected Object[] where_types() {
        return new Object[]{
            new Object[]{BigDecimal.class, BigDecimalConverter.class},
            new Object[]{BigInteger.class, BigIntegerConverter.class},
            new Object[]{Boolean.class, BooleanConverter.class},
            new Object[]{Byte.class, ByteConverter.class},
            new Object[]{Calendar.class, CalendarConverter.class},
            new Object[]{Date.class, DateConverter.class},
            new Object[]{Double.class, DoubleConverter.class},
            new Object[]{File.class, FileConverter.class},
            new Object[]{Float.class, FloatConverter.class},
            new Object[]{Integer.class, IntegerConverter.class},
            new Object[]{LocalDate.class, LocalDateConverter.class},
            new Object[]{LocalDateTime.class, LocalDateTimeConverter.class},
            new Object[]{LocalTime.class, LocalTimeConverter.class},
            new Object[]{Locale.class, LocaleConverter.class},
            new Object[]{Long.class, LongConverter.class},
            new Object[]{Path.class, PathConverter.class},
            new Object[]{Short.class, ShortConverter.class},
            new Object[]{String.class, StringConverter.class},
            new Object[]{URI.class, URIConverter.class},
            new Object[]{URL.class, URLConverter.class},
        };
    }
}
