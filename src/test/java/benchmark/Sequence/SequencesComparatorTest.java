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
package benchmark.Sequence;

import org.apache.commons.collections4.sequence.CommandVisitor;
import org.apache.commons.collections4.sequence.SequencesComparator;
import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class SequencesComparatorTest {

    @Param({"10", "100", "1000"})
    private int size;

    private List<Character> before;
    private List<Character> after;

    @Setup
    public void setup() {
        before = new ArrayList<>(size);
        after = new ArrayList<>(size);

        // Initialize before and after lists with random characters
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            before.add((char) ('a' + random.nextInt(26)));
            after.add((char) ('a' + random.nextInt(26)));
        }
    }

    @Benchmark
    public void testLength() {
        final SequencesComparator<Character> comparator =
                new SequencesComparator<>(before, after);
    }

    @Benchmark
    public void testExecution() {
        final ExecutionVisitor<Character> ev = new ExecutionVisitor<>();
        ev.setList(before);
        new SequencesComparator<>(before, after).getScript().visit(ev);
    }


    private static class ExecutionVisitor<T> implements CommandVisitor<T> {

        private List<T> v;
        private int index;

        public void setList(final List<T> array) {
            v = new ArrayList<>(array);
            index = 0;
        }

        @Override
        public void visitInsertCommand(final T object) {
            v.add(index++, object);
        }

        @Override
        public void visitKeepCommand(final T object) {
            ++index;
        }

        @Override
        public void visitDeleteCommand(final T object) {
            v.remove(index);
        }

        public String getString() {
            final StringBuilder buffer = new StringBuilder();
            for (final T c : v) {
                buffer.append(c);
            }
            return buffer.toString();
        }

    }



}
