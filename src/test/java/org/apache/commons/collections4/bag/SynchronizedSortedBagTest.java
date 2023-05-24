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
package org.apache.commons.collections4.bag;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.bag.PredicatedBag;
import org.apache.commons.collections4.bag.PredicatedSortedBag;
import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.collections4.bag.TransformedSortedBag;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.bag.UnmodifiableBag;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.IdentityPredicate;
import org.apache.commons.collections4.functors.MapTransformer;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.checkerframework.checker.units.qual.Time;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.runner.RunWith;

import static org.easymock.EasyMock.mock;
import static org.junit.jupiter.api.Assertions.*;

public class SynchronizedSortedBagTest {

    @Test
    @Timeout(4000)
    public void test03()  throws Throwable  {
        TreeBag<Integer> treeBag0 = new TreeBag<Integer>();
        Integer integer0 = new Integer(0);
        treeBag0.add(integer0);
        SynchronizedSortedBag<Integer> synchronizedSortedBag0 = SynchronizedSortedBag.synchronizedSortedBag((SortedBag<Integer>) treeBag0);
        SortedBag<Integer> sortedBag0 = synchronizedSortedBag0.getSortedBag();
        assertEquals(1, sortedBag0.size());
    }

    @Test
    @Timeout(4000)
    public void test05()  throws Throwable  {
        TreeBag<Integer> treeBag0 = new TreeBag<Integer>();
        SynchronizedSortedBag<Integer> synchronizedSortedBag0 = SynchronizedSortedBag.synchronizedSortedBag((SortedBag<Integer>) treeBag0);
        Comparator<? super Integer> comparator0 = synchronizedSortedBag0.comparator();
        assertNull(comparator0);
    }

    @Test
    @Timeout(4000)
    public void test07()  throws Throwable  {
        // Undeclared exception!
        try {
            SynchronizedSortedBag.synchronizedSortedBag((SortedBag<Integer>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // collection
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test08()  throws Throwable  {
        TreeBag<Predicate<Object>> treeBag0 = new TreeBag<Predicate<Object>>();
        SynchronizedSortedBag<Predicate<Object>> synchronizedSortedBag0 = new SynchronizedSortedBag<Predicate<Object>>(treeBag0, treeBag0);
        // Undeclared exception!
        try {
            synchronizedSortedBag0.last();
            fail("Expecting exception: NoSuchElementException");

        } catch(NoSuchElementException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test09()  throws Throwable  {
        TreeBag<Predicate<Object>> treeBag0 = new TreeBag<Predicate<Object>>();
        Bag<Object> bag0 = UnmodifiableBag.unmodifiableBag((Bag<?>) treeBag0);
        SynchronizedSortedBag<Object> synchronizedSortedBag0 = new SynchronizedSortedBag<Object>(bag0, treeBag0);
        // Undeclared exception!
        try {
            synchronizedSortedBag0.getSortedBag();
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // org.apache.commons.collections4.bag.UnmodifiableBag cannot be cast to org.apache.commons.collections4.SortedBag
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test10()  throws Throwable  {
        TreeBag<Predicate<Object>> treeBag0 = new TreeBag<Predicate<Object>>();
        SynchronizedSortedBag<Predicate<Object>> synchronizedSortedBag0 = new SynchronizedSortedBag<Predicate<Object>>(treeBag0);
        // Undeclared exception!
        try {
            synchronizedSortedBag0.first();
            fail("Expecting exception: NoSuchElementException");

        } catch(NoSuchElementException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test11()  throws Throwable  {
        SynchronizedSortedBag<Object> synchronizedSortedBag0 = null;
        try {
            synchronizedSortedBag0 = new SynchronizedSortedBag<Object>((SortedBag<Object>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // collection
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test12()  throws Throwable  {
        SynchronizedSortedBag<Object> synchronizedSortedBag0 = null;
        try {
            synchronizedSortedBag0 = new SynchronizedSortedBag<Object>((Bag<Object>) null, (Object) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // collection
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test13()  throws Throwable  {
        HashBag<Boolean> hashBag0 = new HashBag<Boolean>();
        UniquePredicate<Boolean> uniquePredicate0 = new UniquePredicate<Boolean>();
        PredicatedBag<Boolean> predicatedBag0 = PredicatedBag.predicatedBag((Bag<Boolean>) hashBag0, (Predicate<? super Boolean>) uniquePredicate0);
        SynchronizedSortedBag<Boolean> synchronizedSortedBag0 = new SynchronizedSortedBag<Boolean>(predicatedBag0, predicatedBag0);
        // Undeclared exception!
        try {
            synchronizedSortedBag0.comparator();
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // org.apache.commons.collections4.bag.PredicatedBag cannot be cast to org.apache.commons.collections4.SortedBag
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test14()  throws Throwable  {
        TreeBag<Predicate<Object>> treeBag0 = new TreeBag<Predicate<Object>>();
        SynchronizedSortedBag<Predicate<Object>> synchronizedSortedBag0 = new SynchronizedSortedBag<Predicate<Object>>(treeBag0);
        HashBag<Integer> hashBag0 = new HashBag<Integer>();
        SynchronizedSortedBag<Integer> synchronizedSortedBag1 = new SynchronizedSortedBag<Integer>(hashBag0, synchronizedSortedBag0);
        // Undeclared exception!
        try {
            synchronizedSortedBag1.last();
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // org.apache.commons.collections4.bag.HashBag cannot be cast to org.apache.commons.collections4.SortedBag
            //
            assertTrue(true);
        }
    }

    @Test
    @Timeout(4000)
    public void test15()  throws Throwable  {
        HashBag<Object> hashBag0 = new HashBag<Object>();
        SynchronizedSortedBag<Object> synchronizedSortedBag0 = new SynchronizedSortedBag<Object>(hashBag0, hashBag0);
        // Undeclared exception!
        try {
            synchronizedSortedBag0.first();
            fail("Expecting exception: ClassCastException");

        } catch(ClassCastException e) {
            //
            // org.apache.commons.collections4.bag.HashBag cannot be cast to org.apache.commons.collections4.SortedBag
            //
            assertTrue(true);
        }
    }
}
