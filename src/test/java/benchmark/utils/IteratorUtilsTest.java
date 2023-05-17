/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package benchmark.utils;

import org.apache.commons.collections4.IteratorUtils;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@State(Scope.Thread)
@Fork(3)
public class IteratorUtilsTest {

    @Param({"10", "100", "1000"})
    private int size;

    private List<Integer> list;

    @Setup
    public void setup() {
        list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }

    @Benchmark
    public List<Integer> testToList() {
        Iterator<Integer> iterator = list.iterator();
        return IteratorUtils.toList(iterator);
    }

    @Benchmark
    public void testForEach() {
        Iterator<Integer> iterator = list.iterator();
        IteratorUtils.forEach(iterator, integer -> {});
    }

    @Benchmark
    public List<Integer> testToArray() {
        Iterator<Integer> iterator = list.iterator();
        return IteratorUtils.toList(iterator);
    }

}
