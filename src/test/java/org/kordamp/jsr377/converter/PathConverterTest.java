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

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.kordamp.jsr377.ConversionSupport;

import javax.application.converter.ConversionException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Andres Almiray
 */
public class PathConverterTest extends ConversionSupport {
    private static final File DEFAULT_FILE = new File(".");
    private static final Path DEFAULT_PATH = Paths.get(DEFAULT_FILE.getAbsolutePath());

    @ParameterizedTest
    @MethodSource("where_value_result")
    public void valueProducesResult(Object value, Path result) {
        // given:
        PathConverter converter = new PathConverter();

        // when:
        Path output = converter.fromObject(value);

        // then:
        assertThat(output, equalTo(result));
    }

    @ParameterizedTest
    @MethodSource("where_invalid_value")
    public void invalidValueProducesError(Object value) {
        // given:
        PathConverter converter = new PathConverter();

        // when:
        assertThrows(ConversionException.class, () -> converter.fromObject(value));
    }

    public static Stream<Arguments> where_value_result() throws Exception {
        return Stream.of(
            Arguments.of(null, null),
            Arguments.of("", null),
            Arguments.of(" ", null),
            Arguments.of(DEFAULT_PATH, DEFAULT_PATH),
            Arguments.of(DEFAULT_FILE.getAbsoluteFile(), DEFAULT_PATH),
            Arguments.of(DEFAULT_FILE.getAbsoluteFile().toURI(), DEFAULT_PATH)
        );
    }

    public static Stream<Arguments> where_invalid_value() {
        return Stream.of(
            Arguments.of(Collections.emptyList()),
            Arguments.of(Collections.emptyMap()),
            Arguments.of(new Object())
        );
    }
}
