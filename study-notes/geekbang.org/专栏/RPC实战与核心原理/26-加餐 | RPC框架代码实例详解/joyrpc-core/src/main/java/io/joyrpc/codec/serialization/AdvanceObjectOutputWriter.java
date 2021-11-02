package io.joyrpc.codec.serialization;

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

import java.io.IOException;
import java.io.ObjectOutput;

/**
 * 高级的Java对象写入器
 */
public class AdvanceObjectOutputWriter extends ObjectOutputWriter {

    public AdvanceObjectOutputWriter(final ObjectOutput output) {
        super(output);
    }

    @Override
    public void writeObject(final Object v) throws IOException {
        //保持和原有一样
        if (v == null) {
            output.writeByte(0);
        } else {
            output.writeByte(1);
            output.writeObject(v);
        }
    }

    @Override
    public void writeUTF(final String s) throws IOException {
        //保持和原有一样
        if (s == null) {
            output.writeInt(-1);
        } else {
            output.writeInt(s.length());
            output.writeUTF(s);
        }
    }
}
