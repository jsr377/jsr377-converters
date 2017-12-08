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
package org.kordamp.jsr377.converter;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.application.converter.ConversionException;
import javax.application.converter.Converter;
import javax.application.converter.ConverterRegistry;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Andres Almiray
 */
@RunWith(JUnitParamsRunner.class)
public class DefaultConverterRegistryTest {
    @Test
    @Parameters(method = "where_types")
    @SuppressWarnings("rawtypes")
    public <T> void checkDefaultSetup(Class<T> targetType, Class<? extends Converter<T>> converterType) {
        // given:
        ConverterRegistry converterRegistry = new DefaultConverterRegistry();

        // when:
        Converter<T> converter = converterRegistry.findConverter(targetType);

        // then:
        assertThat(converter, instanceOf(converterType));
    }

    @Test
    public void converterAtIndex0IsCalled() {
        // given:
        Converter1.called = false;
        Converter2.called = false;
        ConverterRegistry converterRegistry = new DefaultConverterRegistry();
        converterRegistry.clear();
        converterRegistry.registerConverter(Integer.class, Converter1.class);
        converterRegistry.registerConverter(Integer.class, Converter2.class);

        // when:
        Converter<Integer> converter = converterRegistry.findConverter(Integer.class);
        Integer result = converter.fromObject("1");

        // then:
        assertThat(result, equalTo(1));
        assertThat(Converter1.called, equalTo(true));
        assertThat(Converter2.called, equalTo(false));
    }


    @Test
    public void converterAtIndex1IsCalled() {
        // given:
        Converter1.called = false;
        Converter2.called = false;
        ConverterRegistry converterRegistry = new DefaultConverterRegistry();
        converterRegistry.clear();
        converterRegistry.registerConverter(Integer.class, Converter1.class);
        converterRegistry.registerConverter(Integer.class, Converter2.class);

        // when:
        Converter<Integer> converter = converterRegistry.findConverter(Integer.class);
        Integer result = converter.fromObject(1);

        // then:
        assertThat(result, equalTo(1));
        assertThat(Converter1.called, equalTo(false));
        assertThat(Converter2.called, equalTo(true));
    }

    @Test
    public void registerAndUnregisterConverter() {
        // given:
        ConverterRegistry converterRegistry = new DefaultConverterRegistry();
        converterRegistry.clear();
        converterRegistry.registerConverter(Integer.class, Converter1.class);

        // when:
        Converter<Integer> converter = converterRegistry.findConverter(Integer.class);

        // then:
        assertThat(converter, instanceOf(Converter1.class));

        // when:
        converterRegistry.unregisterConverter(Integer.class, Converter1.class);
        converter = converterRegistry.findConverter(Integer.class);

        // when:
        assertThat(converter, nullValue());
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
            new Object[]{Boolean.TYPE, BooleanConverter.class},
            new Object[]{Byte.TYPE, ByteConverter.class},
            new Object[]{Double.TYPE, DoubleConverter.class},
            new Object[]{Float.TYPE, FloatConverter.class},
            new Object[]{Integer.TYPE, IntegerConverter.class},
            new Object[]{Long.TYPE, LongConverter.class},
            new Object[]{Short.TYPE, ShortConverter.class},
            new Object[]{MyEnum.class, EnumConverter.class}
        };
    }

    public enum MyEnum {
        ONE,
        TWO
    }

    public static class Converter1 extends IntegerConverter {
        private static boolean called;

        @Override
        public Integer fromObject(Object value) throws ConversionException {
            if (value instanceof CharSequence) {
                called = true;
                return super.fromObject(value);
            } else {
                throw illegalValue(value, Integer.class);
            }
        }
    }

    public static class Converter2 extends IntegerConverter {
        private static boolean called;

        @Override
        public Integer fromObject(Object value) throws ConversionException {
            if (value instanceof Number) {
                called = true;
                return super.fromObject(value);
            } else {
                throw illegalValue(value, Integer.class);
            }
        }
    }
}
