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

import io.joyrpc.codec.serialization.exception.NotFoundException;
import io.joyrpc.codec.serialization.model.Animal;
import io.joyrpc.codec.serialization.model.Employee;
import io.joyrpc.codec.serialization.model.MyBook;

import java.util.concurrent.CompletableFuture;

public interface HelloWold {

    CompletableFuture<Integer> update(MyBook book) throws NotFoundException;

    void hello(AnimalTest<Employee> test);

    interface AnimalTest<T> {

        void hello(Animal<T> animal);
    }
}
