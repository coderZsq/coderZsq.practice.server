package io.joyrpc.codec.serialization.jackson.java8;

/*-
 * #%L
 * joyrpc
 * %%
 * Copyright (C) 2019 joyrpc.io
 * %%
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
 * #L%
 */

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import io.joyrpc.exception.SerializerException;

import java.io.IOException;
import java.time.Instant;

/**
 * Instant反序列化
 */
public class InstantDeserializer extends JsonDeserializer<Instant> {

    public static final JsonDeserializer INSTANCE = new InstantDeserializer();

    @Override
    public Instant deserialize(final JsonParser parser, final DeserializationContext ctx) throws IOException {
        switch (parser.currentToken()) {
            case VALUE_NULL:
                return null;
            case VALUE_STRING:
                return Instant.parse(parser.getText());
            default:
                throw new SerializerException("Error occurs while parsing Instant");
        }
    }
}
