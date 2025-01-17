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
package org.apache.commons.collections4;

import static org.apache.commons.collections4.functors.EqualPredicate.equalPredicate;
import static org.easymock.EasyMock.mock;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.*;

import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.functors.*;
import org.apache.commons.collections4.list.FixedSizeList;
import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * Tests for CollectionUtils.
 */
@SuppressWarnings("boxing")
class CollectionUtilsTest extends MockTestCase {

    private static final Predicate<Number> EQUALS_TWO = input -> input.intValue() == 2;

    /**
     * Collection of {@link Integer}s
     */
    private List<Integer> collectionA = null;

    /**
     * Collection of {@link Long}s
     */
    private List<Long> collectionB = null;

    /**
     * Collection of {@link Integer}s that are equivalent to the Longs in
     * collectionB.
     */
    private Collection<Integer> collectionC = null;

    /**
     * Sorted Collection of {@link Integer}s
     */
    private Collection<Integer> collectionD = null;

    /**
     * Sorted Collection of {@link Integer}s
     */
    private Collection<Integer> collectionE = null;

    /**
     * Collection of {@link Integer}s, bound as {@link Number}s
     */
    private Collection<Number> collectionA2 = null;

    /**
     * Collection of {@link Long}s, bound as {@link Number}s
     */
    private Collection<Number> collectionB2 = null;

    /**
     * Collection of {@link Integer}s (cast as {@link Number}s) that are
     * equivalent to the Longs in collectionB.
     */
    private Collection<Number> collectionC2 = null;

    private Iterable<Integer> iterableA = null;

    private Iterable<Long> iterableB = null;

    private Iterable<Integer> iterableC = null;

    private Iterable<Number> iterableA2 = null;

    private Iterable<Number> iterableB2 = null;

    private final Collection<Integer> emptyCollection = new ArrayList<>(1);

    Transformer<Object, Integer> TRANSFORM_TO_INTEGER = input -> Integer.valueOf(((Long) input).intValue());

    @Test
    void addAllForElements() {
        CollectionUtils.addAll(collectionA, 5);
        assertTrue(collectionA.contains(5));
    }

    @Test
    void addAllForEnumeration() {
        final Hashtable<Integer, Integer> h = new Hashtable<>();
        h.put(5, 5);
        final Enumeration<? extends Integer> enumeration = h.keys();
        CollectionUtils.addAll(collectionA, enumeration);
        assertTrue(collectionA.contains(5));
    }

    /**
     * This test ensures that {@link Iterable}s are supported by {@link CollectionUtils}.
     * Specifically, it uses mocks to ensure that if the passed in
     * {@link Iterable} is a {@link Collection} then
     * {@link Collection#addAll(Collection)} is called instead of iterating.
     */
    @Test
    void addAllForIterable() {
        final Collection<Integer> inputCollection = createMock(Collection.class);
        final Iterable<Integer> inputIterable = inputCollection;
        final Iterable<Long> iterable = createMock(Iterable.class);
        final Iterator<Long> iterator = createMock(Iterator.class);
        final Collection<Number> c = createMock(Collection.class);

        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        next(iterator, 2L);
        next(iterator, 3L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(true);
        expect(c.add(2L)).andReturn(true);
        expect(c.add(3L)).andReturn(true);
        // Check that the collection is added using
        // Collection.addAll(Collection)
        expect(c.addAll(inputCollection)).andReturn(true);

        // Ensure the method returns false if nothing is added
        expect(iterable.iterator()).andReturn(iterator);
        next(iterator, 1L);
        expect(iterator.hasNext()).andReturn(false);
        expect(c.add(1L)).andReturn(false);
        expect(c.addAll(inputCollection)).andReturn(false);

        replay();
        assertTrue(CollectionUtils.addAll(c, iterable));
        assertTrue(CollectionUtils.addAll(c, inputIterable));

        assertFalse(CollectionUtils.addAll(c, iterable));
        assertFalse(CollectionUtils.addAll(c, inputIterable));
        verify();
    }

    @Test
     void addIgnoreNull() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        assertFalse(CollectionUtils.addIgnoreNull(set, null));
        assertEquals(3, set.size());
        assertFalse(CollectionUtils.addIgnoreNull(set, "1"));
        assertEquals(3, set.size());
        assertTrue(CollectionUtils.addIgnoreNull(set, "4"));
        assertEquals(4, set.size());
        assertTrue(set.contains("4"));
    }

    private void assertCollectResult(final Collection<Number> collection) {
        assertTrue(collectionA.contains(1) && !collectionA.contains(2L));
        assertTrue(collection.contains(2L) && !collection.contains(1));
    }

    @Test
    @Deprecated
    void cardinality() {
        assertEquals(1, CollectionUtils.cardinality(1, iterableA));
        assertEquals(2, CollectionUtils.cardinality(2, iterableA));
        assertEquals(3, CollectionUtils.cardinality(3, iterableA));
        assertEquals(4, CollectionUtils.cardinality(4, iterableA));
        assertEquals(0, CollectionUtils.cardinality(5, iterableA));

        assertEquals(0, CollectionUtils.cardinality(1L, iterableB));
        assertEquals(4, CollectionUtils.cardinality(2L, iterableB));
        assertEquals(3, CollectionUtils.cardinality(3L, iterableB));
        assertEquals(2, CollectionUtils.cardinality(4L, iterableB));
        assertEquals(1, CollectionUtils.cardinality(5L, iterableB));

        // Ensure that generic bounds accept valid parameters, but return
        // expected results
        // e.g. no longs in the "int" Iterable<Number>, and vice versa.
        assertEquals(0, CollectionUtils.cardinality(2L, iterableA2));
        assertEquals(0, CollectionUtils.cardinality(2, iterableB2));

        final Set<String> set = new HashSet<>();
        set.add("A");
        set.add("C");
        set.add("E");
        set.add("E");
        assertEquals(1, CollectionUtils.cardinality("A", set));
        assertEquals(0, CollectionUtils.cardinality("B", set));
        assertEquals(1, CollectionUtils.cardinality("C", set));
        assertEquals(0, CollectionUtils.cardinality("D", set));
        assertEquals(1, CollectionUtils.cardinality("E", set));

        final Bag<String> bag = new HashBag<>();
        bag.add("A", 3);
        bag.add("C");
        bag.add("E");
        bag.add("E");
        assertEquals(3, CollectionUtils.cardinality("A", bag));
        assertEquals(0, CollectionUtils.cardinality("B", bag));
        assertEquals(1, CollectionUtils.cardinality("C", bag));
        assertEquals(0, CollectionUtils.cardinality("D", bag));
        assertEquals(2, CollectionUtils.cardinality("E", bag));
    }

    @Test
    @Deprecated
    void cardinalityOfNull() {
        final List<String> list = new ArrayList<>();
        assertEquals(0, CollectionUtils.cardinality(null, list));
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertNull(freq.get(null));
        }
        list.add("A");
        assertEquals(0, CollectionUtils.cardinality(null, list));
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertNull(freq.get(null));
        }
        list.add(null);
        assertEquals(1, CollectionUtils.cardinality(null, list));
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(1), freq.get(null));
        }
        list.add("B");
        assertEquals(1, CollectionUtils.cardinality(null, list));
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(1), freq.get(null));
        }
        list.add(null);
        assertEquals(2, CollectionUtils.cardinality(null, list));
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(2), freq.get(null));
        }
        list.add("B");
        assertEquals(2, CollectionUtils.cardinality(null, list));
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(2), freq.get(null));
        }
        list.add(null);
        assertEquals(3, CollectionUtils.cardinality(null, list));
        {
            final Map<String, Integer> freq = CollectionUtils.getCardinalityMap(list);
            assertEquals(Integer.valueOf(3), freq.get(null));
        }
    }

    @Test
    void collateException0() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.collate(null, collectionC));
    }

    @Test
    void collateException1() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.collate(collectionA, null));
    }

    @Test
    void collateException2() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.collate(collectionA, collectionC, null));
    }

    @Test
    void collect() {
        final Transformer<Number, Long> transformer = TransformerUtils.constantTransformer(2L);
        Collection<Number> collection = CollectionUtils.<Integer, Number>collect(iterableA, transformer);
        assertEquals(collection.size(), collectionA.size());
        assertCollectResult(collection);

        ArrayList<Number> list;
        list = CollectionUtils.collect(collectionA, transformer, new ArrayList<Number>());
        assertEquals(list.size(), collectionA.size());
        assertCollectResult(list);

        Iterator<Integer> iterator = null;
        list = CollectionUtils.collect(iterator, transformer, new ArrayList<Number>());

        iterator = iterableA.iterator();
        list = CollectionUtils.collect(iterator, transformer, list);
        assertEquals(collection.size(), collectionA.size());
        assertCollectResult(collection);

        iterator = collectionA.iterator();
        collection = CollectionUtils.<Integer, Number>collect(iterator, transformer);
        assertEquals(collection.size(), collectionA.size());
        assertTrue(collection.contains(2L) && !collection.contains(1));
        collection = CollectionUtils.collect((Iterator<Integer>) null, (Transformer<Integer, Number>) null);
        assertTrue(collection.isEmpty());

        final int size = collectionA.size();
        collectionB = CollectionUtils.collect((Collection<Integer>) null, transformer, collectionB);
        assertTrue(collectionA.size() == size && collectionA.contains(1));
        CollectionUtils.collect(collectionB, null, collectionA);
        assertTrue(collectionA.size() == size && collectionA.contains(1));
    }

    @Test
    void containsAll() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final Collection<String> multiples = new ArrayList<>(3);
        multiples.add("1");
        multiples.add("3");
        multiples.add("1");

        assertFalse(CollectionUtils.containsAll(one, odds), "containsAll({1},{1,3}) should return false.");
        assertTrue(CollectionUtils.containsAll(odds, one), "containsAll({1,3},{1}) should return true.");
        assertFalse(CollectionUtils.containsAll(three, odds), "containsAll({3},{1,3}) should return false.");
        assertTrue(CollectionUtils.containsAll(odds, three), "containsAll({1,3},{3}) should return true.");
        assertTrue(CollectionUtils.containsAll(two, two), "containsAll({2},{2}) should return true.");
        assertTrue(CollectionUtils.containsAll(odds, odds), "containsAll({1,3},{1,3}) should return true.");

        assertFalse(CollectionUtils.containsAll(two, odds), "containsAll({2},{1,3}) should return false.");
        assertFalse(CollectionUtils.containsAll(odds, two), "containsAll({1,3},{2}) should return false.");
        assertFalse(CollectionUtils.containsAll(one, three), "containsAll({1},{3}) should return false.");
        assertFalse(CollectionUtils.containsAll(three, one), "containsAll({3},{1}) should return false.");
        assertTrue(CollectionUtils.containsAll(odds, empty), "containsAll({1,3},{}) should return true.");
        assertFalse(CollectionUtils.containsAll(empty, odds), "containsAll({},{1,3}) should return false.");
        assertTrue(CollectionUtils.containsAll(empty, empty), "containsAll({},{}) should return true.");

        assertTrue(CollectionUtils.containsAll(odds, multiples), "containsAll({1,3},{1,3,1}) should return true.");
        assertTrue(CollectionUtils.containsAll(odds, odds), "containsAll({1,3,1},{1,3,1}) should return true.");
    }

    @Test
    void containsAnyInArray() {
        final Collection<String> empty = new ArrayList<>(0);
        final String[] emptyArr = {};
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final String[] oneArr = {"1"};
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final String[] twoArr = {"2"};
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final String[] threeArr = {"3"};
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");
        final String[] oddsArr = {"1", "3"};

        assertTrue(CollectionUtils.containsAny(one, oddsArr), "containsAny({1},{1,3}) should return true.");
        assertTrue(CollectionUtils.containsAny(odds, oneArr), "containsAny({1,3},{1}) should return true.");
        assertTrue(CollectionUtils.containsAny(three, oddsArr), "containsAny({3},{1,3}) should return true.");
        assertTrue(CollectionUtils.containsAny(odds, threeArr), "containsAny({1,3},{3}) should return true.");
        assertTrue(CollectionUtils.containsAny(two, twoArr), "containsAny({2},{2}) should return true.");
        assertTrue(CollectionUtils.containsAny(odds, oddsArr), "containsAny({1,3},{1,3}) should return true.");

        assertFalse(CollectionUtils.containsAny(two, oddsArr), "containsAny({2},{1,3}) should return false.");
        assertFalse(CollectionUtils.containsAny(odds, twoArr), "containsAny({1,3},{2}) should return false.");
        assertFalse(CollectionUtils.containsAny(one, threeArr), "containsAny({1},{3}) should return false.");
        assertFalse(CollectionUtils.containsAny(three, oneArr), "containsAny({3},{1}) should return false.");
        assertFalse(CollectionUtils.containsAny(odds, emptyArr), "containsAny({1,3},{}) should return false.");
        assertFalse(CollectionUtils.containsAny(empty, oddsArr), "containsAny({},{1,3}) should return false.");
        assertFalse(CollectionUtils.containsAny(empty, emptyArr), "containsAny({},{}) should return false.");
    }

    @Test
    void containsAnyInCollection() {
        final Collection<String> empty = new ArrayList<>(0);
        final Collection<String> one = new ArrayList<>(1);
        one.add("1");
        final Collection<String> two = new ArrayList<>(1);
        two.add("2");
        final Collection<String> three = new ArrayList<>(1);
        three.add("3");
        final Collection<String> odds = new ArrayList<>(2);
        odds.add("1");
        odds.add("3");

        assertTrue(CollectionUtils.containsAny(one, odds), "containsAny({1},{1,3}) should return true.");
        assertTrue(CollectionUtils.containsAny(odds, one), "containsAny({1,3},{1}) should return true.");
        assertTrue(CollectionUtils.containsAny(three, odds), "containsAny({3},{1,3}) should return true.");
        assertTrue(CollectionUtils.containsAny(odds, three), "containsAny({1,3},{3}) should return true.");
        assertTrue(CollectionUtils.containsAny(two, two), "containsAny({2},{2}) should return true.");
        assertTrue(CollectionUtils.containsAny(odds, odds), "containsAny({1,3},{1,3}) should return true.");

        assertFalse(CollectionUtils.containsAny(two, odds), "containsAny({2},{1,3}) should return false.");
        assertFalse(CollectionUtils.containsAny(odds, two), "containsAny({1,3},{2}) should return false.");
        assertFalse(CollectionUtils.containsAny(one, three), "containsAny({1},{3}) should return false.");
        assertFalse(CollectionUtils.containsAny(three, one), "containsAny({3},{1}) should return false.");
        assertFalse(CollectionUtils.containsAny(odds, empty), "containsAny({1,3},{}) should return false.");
        assertFalse(CollectionUtils.containsAny(empty, odds), "containsAny({},{1,3}) should return false.");
        assertFalse(CollectionUtils.containsAny(empty, empty), "containsAny({},{}) should return false.");
    }

    @Test
    @Deprecated
    void countMatches() {
        assertEquals(4, CollectionUtils.countMatches(iterableB, EQUALS_TWO));
        assertEquals(0, CollectionUtils.countMatches(iterableA, null));
        assertEquals(0, CollectionUtils.countMatches(null, EQUALS_TWO));
        assertEquals(0, CollectionUtils.countMatches(null, null));
    }

    @Test
    void disjunction() {
        final Collection<Integer> col = CollectionUtils.disjunction(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        assertEquals(Integer.valueOf(1), freq.get(1));
        assertEquals(Integer.valueOf(2), freq.get(2));
        assertNull(freq.get(3));
        assertEquals(Integer.valueOf(2), freq.get(4));
        assertEquals(Integer.valueOf(1), freq.get(5));

        final Collection<Number> col2 = CollectionUtils.disjunction(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        assertEquals(Integer.valueOf(1), freq2.get(1));
        assertEquals(Integer.valueOf(2), freq2.get(2));
        assertNull(freq2.get(3));
        assertEquals(Integer.valueOf(2), freq2.get(4));
        assertEquals(Integer.valueOf(1), freq2.get(5));
    }

    @Test
    void emptyCollection() throws Exception {
        final Collection<Number> coll = CollectionUtils.emptyCollection();
        assertEquals(CollectionUtils.getEmptyCollection(), coll);
    }

    @Test
    void emptyIfNull() {
        assertTrue(CollectionUtils.emptyIfNull(null).isEmpty());
        final Collection<Object> collection = new ArrayList<>();
        assertSame(collection, CollectionUtils.emptyIfNull(collection));
    }

    @Test
    @Deprecated
    void exists() {
        final List<Integer> list = new ArrayList<>();
        assertFalse(CollectionUtils.exists(null, null));
        assertFalse(CollectionUtils.exists(list, null));
        assertFalse(CollectionUtils.exists(null, EQUALS_TWO));
        assertFalse(CollectionUtils.exists(list, EQUALS_TWO));
        list.add(1);
        list.add(3);
        list.add(4);
        assertFalse(CollectionUtils.exists(list, EQUALS_TWO));

        list.add(2);
        assertTrue(CollectionUtils.exists(list, EQUALS_TWO));
    }

    @Test
    void extractSingleton() {
        assertAll(
                () -> {
                    final ArrayList<String> collNull = null;
                    assertThrows(NullPointerException.class, () -> CollectionUtils.extractSingleton(collNull),
                            "expected NullPointerException from extractSingleton(null)");
                },
                () -> {
                    final ArrayList<String> collEmpty = new ArrayList<>();
                    assertThrows(IllegalArgumentException.class, () -> CollectionUtils.extractSingleton(collEmpty),
                            "expected IllegalArgumentException from extractSingleton(empty)");
                },
                () -> {
                    final ArrayList<String> coll = new ArrayList<>();
                    coll.add("foo");
                    assertEquals("foo", CollectionUtils.extractSingleton(coll));
                    coll.add("bar");

                    assertThrows(IllegalArgumentException.class, () -> CollectionUtils.extractSingleton(coll),
                            "expected IllegalArgumentException from extractSingleton(size == 2)");
                }
        );
    }

    //Up to here
    @Test
    void filter() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        assertTrue(CollectionUtils.filter(iterable, EQUALS_TWO));
        assertEquals(1, ints.size());
        assertEquals(2, (int) ints.get(0));
    }

    @Test
    void filterInverse() {
        final List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.add(3);
        ints.add(3);
        final Iterable<Integer> iterable = ints;
        assertTrue(CollectionUtils.filterInverse(iterable, EQUALS_TWO));
        assertEquals(3, ints.size());
        assertEquals(1, (int) ints.get(0));
        assertEquals(3, (int) ints.get(1));
        assertEquals(3, (int) ints.get(2));
    }

    @Test
    void filterInverseNullParameters() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        assertFalse(CollectionUtils.filterInverse(longs, null));
        assertEquals(4, longs.size());
        assertFalse(CollectionUtils.filterInverse(null, EQUALS_TWO));
        assertEquals(4, longs.size());
        assertFalse(CollectionUtils.filterInverse(null, null));
        assertEquals(4, longs.size());
    }

    @Test
    void filterNullParameters() throws Exception {
        final List<Long> longs = Collections.nCopies(4, 10L);
        assertFalse(CollectionUtils.filter(longs, null));
        assertEquals(4, longs.size());
        assertFalse(CollectionUtils.filter(null, EQUALS_TWO));
        assertEquals(4, longs.size());
        assertFalse(CollectionUtils.filter(null, null));
        assertEquals(4, longs.size());
    }

    @Test
    @Deprecated
    void find() {
        Predicate<Number> testPredicate = equalPredicate((Number) 4);
        Integer test = CollectionUtils.find(collectionA, testPredicate);
        assertEquals(4, (int) test);
        testPredicate = equalPredicate((Number) 45);
        test = CollectionUtils.find(collectionA, testPredicate);
        assertNull(test);
        assertNull(CollectionUtils.find(null, testPredicate));
        assertNull(CollectionUtils.find(collectionA, null));
    }

    @Test
    @Deprecated
    void forAllButLastDoCollection() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        assertSame(lastElement, collectionB);
        assertTrue(collectionA.isEmpty() && !collectionB.isEmpty());

        col.clear();
        col.add(collectionB);
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        assertSame(lastElement, collectionB);
        assertFalse(collectionB.isEmpty());

        col.clear();
        lastElement = CollectionUtils.forAllButLastDo(col, testClosure);
        assertNull(lastElement);

        final Collection<String> strings = Arrays.asList("a", "b", "c");
        final StringBuilder result = new StringBuilder();
        result.append(CollectionUtils.forAllButLastDo(strings, (Closure<String>) input -> result.append(input+";")));
        assertEquals("a;b;c", result.toString());

        final Collection<String> oneString = Arrays.asList("a");
        final StringBuilder resultOne = new StringBuilder();
        resultOne.append(CollectionUtils.forAllButLastDo(oneString, (Closure<String>) input -> resultOne.append(input+";")));
        assertEquals("a", resultOne.toString());
        assertNull(CollectionUtils.forAllButLastDo(strings, (Closure<String>) null)); // do not remove cast
        assertNull(CollectionUtils.forAllButLastDo((Collection<String>) null, (Closure<String>) null)); // do not remove cast
    }

    @Test
    @Deprecated
    void forAllButLastDoIterator() {
        final Closure<List<? extends Number>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<List<? extends Number>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionB);
        final List<? extends Number> lastElement = CollectionUtils.forAllButLastDo(col.iterator(), testClosure);
        assertSame(lastElement, collectionB);
        assertTrue(collectionA.isEmpty() && !collectionB.isEmpty());

        assertNull(CollectionUtils.forAllButLastDo(col.iterator(), (Closure<List<? extends Number>>) null));
        assertNull(CollectionUtils.forAllButLastDo((Iterator<String>) null, (Closure<String>) null)); // do not remove cast
    }

    @Test
    @Deprecated
    void forAllDoCollection() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col, testClosure);
        assertSame(testClosure, resultClosure);
        assertTrue(collectionA.isEmpty() && collectionC.isEmpty());
        // fix for various java 1.6 versions: keep the cast
        resultClosure = CollectionUtils.forAllDo(col, (Closure<Collection<Integer>>) null);
        assertNull(resultClosure);
        assertTrue(collectionA.isEmpty() && collectionC.isEmpty());
        resultClosure = CollectionUtils.forAllDo((Collection<Collection<Integer>>) null, testClosure);
        col.add(null);
        // null should be OK
        CollectionUtils.forAllDo(col, testClosure);
    }

    @Test
    @Deprecated
     void forAllDoFailure() {
        final Closure<String> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<String> col = new ArrayList<>();
        col.add("x");
        assertThrows(FunctorException.class, () -> CollectionUtils.forAllDo(col, testClosure));
    }

    @Test
    @Deprecated
    void forAllDoIterator() {
        final Closure<Collection<Integer>> testClosure = ClosureUtils.invokerClosure("clear");
        final Collection<Collection<Integer>> col = new ArrayList<>();
        col.add(collectionA);
        col.add(collectionC);
        Closure<Collection<Integer>> resultClosure = CollectionUtils.forAllDo(col.iterator(), testClosure);
        assertSame(testClosure, resultClosure);
        assertTrue(collectionA.isEmpty() && collectionC.isEmpty());
        // fix for various java 1.6 versions: keep the cast
        resultClosure = CollectionUtils.forAllDo(col.iterator(), (Closure<Collection<Integer>>) null);
        assertNull(resultClosure);
        assertTrue(collectionA.isEmpty() && collectionC.isEmpty());
        resultClosure = CollectionUtils.forAllDo((Iterator<Collection<Integer>>) null, testClosure);
        col.add(null);
        // null should be OK
        CollectionUtils.forAllDo(col.iterator(), testClosure);
    }

    @Test
    void get() {
        assertEquals(2, CollectionUtils.get((Object) collectionA, 2));
        assertEquals(2, CollectionUtils.get((Object) collectionA.iterator(), 2));
        final Map<Integer, Integer> map = CollectionUtils.getCardinalityMap(collectionA);
        // Test assumes a defined iteration order so convert to a LinkedHashMap
        final Map<Integer, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.putAll(map);
        assertEquals(linkedMap.entrySet().iterator().next(), CollectionUtils.get((Object) linkedMap, 0));
    }

    @Test
    void get1() {
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.get((Object) null, 0));
    }

    @Test
    void getCardinalityMap() {
        final Map<Number, Integer> freqA = CollectionUtils.<Number>getCardinalityMap(iterableA);
        assertEquals(1, (int) freqA.get(1));
        assertEquals(2, (int) freqA.get(2));
        assertEquals(3, (int) freqA.get(3));
        assertEquals(4, (int) freqA.get(4));
        assertNull(freqA.get(5));

        final Map<Long, Integer> freqB = CollectionUtils.getCardinalityMap(iterableB);
        assertNull(freqB.get(1L));
        assertEquals(4, (int) freqB.get(2L));
        assertEquals(3, (int) freqB.get(3L));
        assertEquals(2, (int) freqB.get(4L));
        assertEquals(1, (int) freqB.get(5L));
    }

    @Test
    void getEnumeration() {
        final Vector<Integer> vectorA = new Vector<>(collectionA);
        final Enumeration<Integer> e = vectorA.elements();
        assertEquals(Integer.valueOf(2), CollectionUtils.get(e, 2));
        assertTrue(e.hasMoreElements());
        assertEquals(Integer.valueOf(4), CollectionUtils.get(e, 6));
        assertFalse(e.hasMoreElements());
    }

    @Test
    @Deprecated
    void getFromEnumeration() throws Exception {
        // Enumeration, entry exists
        final Vector<String> vector = new Vector<>();
        vector.addElement("zero");
        vector.addElement("one");
        Enumeration<String> en = vector.elements();
        assertEquals("zero", CollectionUtils.get(en, 0));
        en = vector.elements();
        assertEquals("one", CollectionUtils.get(en, 1));

        // Enumerator, non-existent entry
        final Enumeration<String> finalEn = en;
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(finalEn, 3),
                "Expecting IndexOutOfBoundsException.");

        assertFalse(en.hasMoreElements());
    }

    @Test
    void getFromHashMap() {
        // Unordered map, entries exist
        final Map<String, String> expected = new HashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        Map.Entry<String, String> entry = CollectionUtils.get(expected, 0);
        assertTrue(entry.toString().equals("zeroKey=zero") || entry.toString().equals("oneKey=one"));
        entry = CollectionUtils.get(expected, 1);
        assertTrue(entry.toString().equals("zeroKey=zero") || entry.toString().equals("oneKey=one"));

        // Map index out of range
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(expected, 2),
                        "Expecting IndexOutOfBoundsException."),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(expected, -2),
                        "Expecting IndexOutOfBoundsException.")
        );
    }

    @Test
    @Deprecated
    void getFromIterable() throws Exception {
        // Collection, entry exists
        final Bag<String> bag = new HashBag<>();
        bag.add("element", 1);
        assertEquals("element", CollectionUtils.get(bag, 0));

        // Collection, non-existent entry
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(bag, 1));
    }

    @Test
    @Deprecated
    void getFromIterator() throws Exception {
        // Iterator, entry exists
        Iterator<Integer> iterator = iterableA.iterator();
        assertEquals(1, (int) CollectionUtils.get(iterator, 0));
        iterator = iterableA.iterator();
        assertEquals(2, (int) CollectionUtils.get(iterator, 1));

        // Iterator, non-existent entry
        final Iterator<Integer> finalIterator = iterator;
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(finalIterator, 10),
                "Expecting IndexOutOfBoundsException.");

        assertFalse(iterator.hasNext());
    }

    @Test
    void getFromLinkedHashMap() {
        // Ordered map, entries exist
        final Map<String, String> expected = new LinkedHashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        final Map<String, String> found = new LinkedHashMap<>();
        Map.Entry<String, String> entry = CollectionUtils.get(expected, 0);
        found.put(entry.getKey(), entry.getValue());
        entry = CollectionUtils.get(expected, 1);
        found.put(entry.getKey(), entry.getValue());
        assertEquals(expected, found);
    }

    /**
     * Tests that {@link List}s are handled correctly - e.g. using
     * {@link List#get(int)}.
     */
    @Test
    void getFromList() throws Exception {
        // List, entry exists
        final List<String> list = createMock(List.class);
        expect(list.get(0)).andReturn("zero");
        expect(list.get(1)).andReturn("one");
        replay();
        final String string = CollectionUtils.get(list, 0);
        assertEquals("zero", string);
        assertEquals("one", CollectionUtils.get(list, 1));
        // list, non-existent entry -- IndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(new ArrayList<>(), 2));
    }

    @Test
    void getFromMapIndexOutOfRange() {
        // Ordered map, entries exist
        final Map<String, String> expected = new LinkedHashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        // Map index out of range
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(expected, 2),
                        "Expecting IndexOutOfBoundsException."),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(expected, -2),
                        "Expecting IndexOutOfBoundsException.")
        );
    }

    @Test
    void getFromObject() throws Exception {
        // Invalid object
        final Object obj = new Object();
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.get(obj, 0));
    }

    @Test
    void getFromObjectArray() throws Exception {
        // Object array, entry exists
        final Object[] objArray = new Object[2];
        objArray[0] = "zero";
        objArray[1] = "one";
        assertEquals("zero", CollectionUtils.get(objArray, 0));
        assertEquals("one", CollectionUtils.get(objArray, 1));

        // Object array, non-existent entry --
        // ArrayIndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(objArray, 2));
    }

    @Test
    void getFromPrimitiveArray() throws Exception {
        // Primitive array, entry exists
        final int[] array = new int[2];
        array[0] = 10;
        array[1] = 20;
        assertEquals(10, CollectionUtils.get(array, 0));
        assertEquals(20, CollectionUtils.get(array, 1));

        // Object array, non-existent entry --
        // ArrayIndexOutOfBoundsException
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get(array, 2));
    }

    @Test
    void getFromTreeMap() {
        // Ordered map, entries exist
        final Map<String, String> expected = new LinkedHashMap<>();
        expected.put("zeroKey", "zero");
        expected.put("oneKey", "one");

        // Sorted map, entries exist, should respect order
        final SortedMap<String, String> map = new TreeMap<>();
        map.put("zeroKey", "zero");
        map.put("oneKey", "one");
        Map.Entry<String, String> test = CollectionUtils.get(map, 1);
        assertEquals("zeroKey", test.getKey());
        assertEquals("zero", test.getValue());
        test = CollectionUtils.get(map, 0);
        assertEquals("oneKey", test.getKey());
        assertEquals("one", test.getValue());
    }

    @Test
    void getIterator() {
        final Iterator<Integer> it = collectionA.iterator();
        assertEquals(Integer.valueOf(2), CollectionUtils.get((Object) it, 2));
        assertTrue(it.hasNext());
        assertEquals(Integer.valueOf(4), CollectionUtils.get((Object) it, 6));
        assertFalse(it.hasNext());
    }

    @Test
    void getNegative() {
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get((Object) collectionA, -3));
    }

    @Test
    void getPositiveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.get((Object) collectionA.iterator(), 30));
    }

    @Test
     void intersection() {
        final Collection<Integer> col = CollectionUtils.intersection(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        assertNull(freq.get(1));
        assertEquals(Integer.valueOf(2), freq.get(2));
        assertEquals(Integer.valueOf(3), freq.get(3));
        assertEquals(Integer.valueOf(2), freq.get(4));
        assertNull(freq.get(5));

        final Collection<Number> col2 = CollectionUtils.intersection(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        assertNull(freq2.get(1));
        assertEquals(Integer.valueOf(2), freq2.get(2));
        assertEquals(Integer.valueOf(3), freq2.get(3));
        assertEquals(Integer.valueOf(2), freq2.get(4));
        assertNull(freq2.get(5));
    }

    @Test
    void intersectionUsesMethodEquals() {
        // Let elta and eltb be objects...
        final Integer elta = 17;
        final Integer eltb = 17;

        // ...which are the same (==)
        assertSame(elta, eltb);

        // Let cola and colb be collections...
        final Collection<Number> cola = new ArrayList<>();
        final Collection<Integer> colb = new ArrayList<>();

        // ...which contain elta and eltb,
        // respectively.
        cola.add(elta);
        colb.add(eltb);

        // Then the intersection of the two
        // should contain one element.
        final Collection<Number> intersection = CollectionUtils.intersection(cola, colb);
        assertEquals(1, intersection.size());

        // In practice, this element will be the same (==) as elta
        // or eltb, although this isn't strictly part of the
        // contract.
        final Object eltc = intersection.iterator().next();
        assertTrue(eltc == elta && eltc == eltb);

        // In any event, this element remains equal,
        // to both elta and eltb.
        assertEquals(elta, eltc);
        assertEquals(eltc, elta);
        assertEquals(eltb, eltc);
        assertEquals(eltc, eltb);
    }

    @Test
    void isEmpty() {
        assertFalse(CollectionUtils.isNotEmpty(null));
        assertTrue(CollectionUtils.isNotEmpty(collectionA));
    }

    @Test
    void isFull() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        assertFalse(CollectionUtils.isFull(set));

        final CircularFifoQueue<String> buf = new CircularFifoQueue<>(set);
        assertFalse(CollectionUtils.isFull(buf));
        buf.remove("2");
        assertFalse(CollectionUtils.isFull(buf));
        buf.add("2");
        assertFalse(CollectionUtils.isFull(buf));
    }

    @Test
    void maxSize() {
        final Set<String> set = new HashSet<>();
        set.add("1");
        set.add("2");
        set.add("3");
        assertEquals(-1, CollectionUtils.maxSize(set));

        final Queue<String> buf = new CircularFifoQueue<>(set);
        assertEquals(3, CollectionUtils.maxSize(buf));
        buf.remove("2");
        assertEquals(3, CollectionUtils.maxSize(buf));
        buf.add("2");
        assertEquals(3, CollectionUtils.maxSize(buf));
    }

    /**
     * Records the next object returned for a mock iterator
     */
    private <T> void next(final Iterator<T> iterator, final T t) {
        expect(iterator.hasNext()).andReturn(true);
        expect(iterator.next()).andReturn(t);
    }

    @Test
    void predicatedCollection() {
        final Predicate<Object> predicate = PredicateUtils.instanceofPredicate(Integer.class);
        final Collection<Number> collection = CollectionUtils.predicatedCollection(new ArrayList<Number>(), predicate);
        assertTrue(collection instanceof PredicatedCollection, "returned object should be a PredicatedCollection");
    }

    @Test
     void reverse() {
        CollectionUtils.reverseArray(new Object[] {});
        final Integer[] a = collectionA.toArray(ArrayUtils.EMPTY_INTEGER_OBJECT_ARRAY);
        CollectionUtils.reverseArray(a);
        // assume our implementation is correct if it returns the same order as the Java function
        Collections.reverse(collectionA);
        assertEquals(collectionA, Arrays.asList(a));
    }

    @Test
    void select() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // Ensure that the collection is the input type or a super type
        final Collection<Integer> output1 = CollectionUtils.select(list, EQUALS_TWO);
        final Collection<Number> output2 = CollectionUtils.<Number>select(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.select(list, EQUALS_TWO, new HashSet<Number>());
        assertTrue(CollectionUtils.isEqualCollection(output1, output3));
        assertEquals(4, list.size());
        assertEquals(1, output1.size());
        assertEquals(2, output2.iterator().next());
    }

    @Test
    void selectRejected() {
        final List<Long> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        list.add(4L);
        final Collection<Long> output1 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final Collection<? extends Number> output2 = CollectionUtils.selectRejected(list, EQUALS_TWO);
        final HashSet<Number> output3 = CollectionUtils.selectRejected(list, EQUALS_TWO, new HashSet<Number>());
        assertTrue(CollectionUtils.isEqualCollection(output1, output2));
        assertTrue(CollectionUtils.isEqualCollection(output1, output3));
        assertEquals(4, list.size());
        assertEquals(3, output1.size());
        assertTrue(output1.contains(1L));
        assertTrue(output1.contains(3L));
        assertTrue(output1.contains(4L));
    }

    @Test
    void selectWithOutputCollections() {
        final List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);

        final List<Integer> output = new ArrayList<>();
        final List<Integer> rejected = new ArrayList<>();

        CollectionUtils.select(input, EQUALS_TWO, output, rejected);

        // output contains 2
        assertEquals(1, output.size());
        assertEquals(2, CollectionUtils.extractSingleton(output).intValue());

        // rejected contains 1, 3, and 4
        final Integer[] expected = {1, 3, 4};
        assertArrayEquals(expected, rejected.toArray());

        output.clear();
        rejected.clear();
        CollectionUtils.select((List<Integer>) null, EQUALS_TWO, output, rejected);
        assertTrue(output.isEmpty());
        assertTrue(rejected.isEmpty());
    }

    @BeforeEach
    public void setUp() {
        collectionA = new ArrayList<>();
        collectionA.add(1);
        collectionA.add(2);
        collectionA.add(2);
        collectionA.add(3);
        collectionA.add(3);
        collectionA.add(3);
        collectionA.add(4);
        collectionA.add(4);
        collectionA.add(4);
        collectionA.add(4);
        collectionB = new LinkedList<>();
        collectionB.add(5L);
        collectionB.add(4L);
        collectionB.add(4L);
        collectionB.add(3L);
        collectionB.add(3L);
        collectionB.add(3L);
        collectionB.add(2L);
        collectionB.add(2L);
        collectionB.add(2L);
        collectionB.add(2L);

        collectionC = new ArrayList<>();
        for (final Long l : collectionB) {
            collectionC.add(l.intValue());
        }

        iterableA = collectionA;
        iterableB = collectionB;
        iterableC = collectionC;
        collectionA2 = new ArrayList<>(collectionA);
        collectionB2 = new LinkedList<>(collectionB);
        collectionC2 = new LinkedList<>(collectionC);
        iterableA2 = collectionA2;
        iterableB2 = collectionB2;

        collectionD = new ArrayList<>();
        collectionD.add(1);
        collectionD.add(3);
        collectionD.add(3);
        collectionD.add(3);
        collectionD.add(5);
        collectionD.add(7);
        collectionD.add(7);
        collectionD.add(10);

        collectionE = new ArrayList<>();
        collectionE.add(2);
        collectionE.add(4);
        collectionE.add(4);
        collectionE.add(5);
        collectionE.add(6);
        collectionE.add(6);
        collectionE.add(9);
    }

    @Test
     void testaddAllNullColl1() {
        final List<Integer> list = new ArrayList<>();
        assertThrows(NullPointerException.class, () -> CollectionUtils.addAll(null, list));
    }

    @Test
     void testAddAllNullColl2() {
        final List<Integer> list = new ArrayList<>();
        final Iterable<Integer> list2 = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.addAll(list, list2));
    }

    @Test
     void testAddAllNullColl3() {
        final List<Integer> list = new ArrayList<>();
        final Iterator<Integer> list2 = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.addAll(list, list2));
    }

    @Test
     void testAddAllNullColl4() {
        final List<Integer> list = new ArrayList<>();
        final Enumeration<Integer> enumArray = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.addAll(list, enumArray));
    }

    @Test
     void testAddAllNullColl5() {
        final List<Integer> list = new ArrayList<>();
        final Integer[] array = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.addAll(list, array));
    }

    @Test
     void testAddIgnoreNullNullColl() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.addIgnoreNull(null, "1"));
    }

    @Test
     void testCollate() {
        List<Integer> result = CollectionUtils.collate(emptyCollection, emptyCollection);
        assertEquals(0, result.size(), "Merge empty with empty");

        result = CollectionUtils.collate(collectionA, emptyCollection);
        assertEquals(collectionA, result, "Merge empty with non-empty");

        List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE);
        List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD);
        assertEquals(result1, result2, "Merge two lists 1");

        final List<Integer> combinedList = new ArrayList<>(collectionD);
        combinedList.addAll(collectionE);
        combinedList.sort(null);

        assertEquals(combinedList, result2, "Merge two lists 2");

        final Comparator<Integer> reverseComparator =
                ComparatorUtils.reversedComparator(ComparatorUtils.<Integer>naturalComparator());

        result = CollectionUtils.collate(emptyCollection, emptyCollection, reverseComparator);
        assertEquals(0, result.size(), "Comparator Merge empty with empty");

        Collections.reverse((List<Integer>) collectionD);
        Collections.reverse((List<Integer>) collectionE);
        Collections.reverse(combinedList);

        result1 = CollectionUtils.collate(collectionD, collectionE, reverseComparator);
        result2 = CollectionUtils.collate(collectionE, collectionD, reverseComparator);
        assertEquals(result1, result2, "Comparator Merge two lists 1");
        assertEquals(combinedList, result2, "Comparator Merge two lists 2");
    }

    @Test
     void testCollateIgnoreDuplicates() {
        final List<Integer> result1 = CollectionUtils.collate(collectionD, collectionE, false);
        final List<Integer> result2 = CollectionUtils.collate(collectionE, collectionD, false);
        assertEquals(result1, result2, "Merge two lists 1 - ignore duplicates");

        final Set<Integer> combinedSet = new HashSet<>(collectionD);
        combinedSet.addAll(collectionE);
        final List<Integer> combinedList = new ArrayList<>(combinedSet);
        combinedList.sort(null);

        assertEquals(combinedList, result2, "Merge two lists 2 - ignore duplicates");
    }

    @Test
     void testContainsAnyInArrayNullArray() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        final String[] array = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAny(list, array));
    }

    @Test
     void testContainsAnyInArrayNullColl1() {
        final String[] oneArr = {"1"};
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAny(null, oneArr));
    }

    @Test
     void testContainsAnyInArrayNullColl2() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        final Collection<String> list2 = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAny(list, list2));
    }

    @Test
     void testContainsAnyNullColl1() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAny(null, list));
    }

    @Test
     void testContainsAnyNullColl2() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        final Collection<String> list2 = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAny(list, list2));
    }

    @Test
     void testContainsAnyNullColl3() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        final String[] array = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.containsAny(list, array));
    }

    @Test
     void testDisjunctionAsSymmetricDifference() {
        final Collection<Number> dis = CollectionUtils.<Number>disjunction(collectionA, collectionC);
        final Collection<Number> amb = CollectionUtils.<Number>subtract(collectionA, collectionC);
        final Collection<Number> bma = CollectionUtils.<Number>subtract(collectionC, collectionA);
        assertTrue(CollectionUtils.isEqualCollection(dis, CollectionUtils.union(amb, bma)));
    }

    @Test
     void testDisjunctionAsUnionMinusIntersection() {
        final Collection<Number> dis = CollectionUtils.<Number>disjunction(collectionA, collectionC);
        final Collection<Number> un = CollectionUtils.<Number>union(collectionA, collectionC);
        final Collection<Number> inter = CollectionUtils.<Number>intersection(collectionA, collectionC);
        assertTrue(CollectionUtils.isEqualCollection(dis, CollectionUtils.subtract(un, inter)));
    }

    @Test
     void testDisjunctionNullColl1() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.disjunction(null, list));
    }

    @Test
     void testDisjunctionNullColl2() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.disjunction(list, null));
    }

    @Test
     void testGetCardinalityMapNull() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.getCardinalityMap(null));
    }

    @Test
     void testHashCode() {
        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1 % 2 == 0 ^ o2 % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o == null ? 0 : Objects.hashCode(o);
            }
        };

        assertEquals(collectionA.hashCode(), CollectionUtils.hashCode(collectionA, e));
    }

    @Test
     void testHashCodeNullCollection() {
        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1 % 2 == 0 ^ o2 % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o == null ? 0 : Objects.hashCode(o);
            }
        };

        final Collection<Integer> collection = null;
        assertEquals(0, CollectionUtils.hashCode(collection, e));
    }

    @Test
     void testHashCodeNullEquator() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.hashCode(collectionB, null));
    }

    @Test
     void testIntersectionNullColl1() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.intersection(null, list));
    }

    @Test
     void testIntersectionNullColl2() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.intersection(list, null));
    }

    @Test
     void testIsEmptyWithEmptyCollection() {
        assertTrue(CollectionUtils.isEmpty(new ArrayList<>()));
    }

    @Test
     void testIsEmptyWithNonEmptyCollection() {
        assertFalse(CollectionUtils.isEmpty(Collections.singletonList("item")));
    }

    @Test
     void testIsEmptyWithNull() {
        assertTrue(CollectionUtils.isEmpty(null));
    }

    @Test
     void testIsEqualCollection() {
        assertFalse(CollectionUtils.isEqualCollection(collectionA, collectionC));
        assertFalse(CollectionUtils.isEqualCollection(collectionC, collectionA));
    }

    @Test
     void testIsEqualCollection2() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        assertTrue(CollectionUtils.isEqualCollection(a, b));
        assertTrue(CollectionUtils.isEqualCollection(b, a));
        a.add("1");
        assertFalse(CollectionUtils.isEqualCollection(a, b));
        assertFalse(CollectionUtils.isEqualCollection(b, a));
        b.add("1");
        assertTrue(CollectionUtils.isEqualCollection(a, b));
        assertTrue(CollectionUtils.isEqualCollection(b, a));
        a.add("2");
        assertFalse(CollectionUtils.isEqualCollection(a, b));
        assertFalse(CollectionUtils.isEqualCollection(b, a));
        b.add("2");
        assertTrue(CollectionUtils.isEqualCollection(a, b));
        assertTrue(CollectionUtils.isEqualCollection(b, a));
        a.add("1");
        assertFalse(CollectionUtils.isEqualCollection(a, b));
        assertFalse(CollectionUtils.isEqualCollection(b, a));
        b.add("1");
        assertTrue(CollectionUtils.isEqualCollection(a, b));
        assertTrue(CollectionUtils.isEqualCollection(b, a));
    }

    @Test
     void testIsEqualCollectionEquator() {
        final Collection<Integer> collB = CollectionUtils.collect(collectionB, TRANSFORM_TO_INTEGER);

        // odd / even equator
        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1.intValue() % 2 == 0 ^ o2.intValue() % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o.intValue() % 2 == 0 ? Integer.valueOf(0).hashCode() : Integer.valueOf(1).hashCode();
            }
        };

        assertTrue(CollectionUtils.isEqualCollection(collectionA, collectionA, e));
        assertTrue(CollectionUtils.isEqualCollection(collectionA, collB, e));
        assertTrue(CollectionUtils.isEqualCollection(collB, collectionA, e));

        final Equator<Number> defaultEquator = DefaultEquator.defaultEquator();
        assertFalse(CollectionUtils.isEqualCollection(collectionA, collectionB, defaultEquator));
        assertFalse(CollectionUtils.isEqualCollection(collectionA, collB, defaultEquator));
    }

    @Test
     void testIsEqualCollectionNullColl1() {
        final Collection<Integer> list = new ArrayList<>(1);
        list.add(1);

        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1.intValue() % 2 == 0 ^ o2.intValue() % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o.intValue() % 2 == 0 ? Integer.valueOf(0).hashCode() : Integer.valueOf(1).hashCode();
            }
        };
        assertThrows(NullPointerException.class, () -> CollectionUtils.isEqualCollection(null, list, e));
    }

    @Test
     void testIsEqualCollectionNullColl2() {
        final Collection<Integer> list = new ArrayList<>(1);
        list.add(1);

        final Equator<Integer> e = new Equator<Integer>() {
            @Override
            public boolean equate(final Integer o1, final Integer o2) {
                if (o1.intValue() % 2 == 0 ^ o2.intValue() % 2 == 0) {
                    return false;
                }
                return true;
            }

            @Override
            public int hash(final Integer o) {
                return o.intValue() % 2 == 0 ? Integer.valueOf(0).hashCode() : Integer.valueOf(1).hashCode();
            }
        };
        assertThrows(NullPointerException.class, () -> CollectionUtils.isEqualCollection(list, null, e));
    }

    @Test
     void testIsEqualCollectionNullEquator() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.isEqualCollection(collectionA, collectionA, null));
    }

    @Test
     void testIsEqualCollectionReturnsFalse() {
        final List<Integer> b = new ArrayList<>(collectionA);
        // remove an extra '2', and add a 5.  This will increase the size of the cardinality
        b.remove(1);
        b.add(5);
        assertFalse(CollectionUtils.isEqualCollection(collectionA, b));
        assertFalse(CollectionUtils.isEqualCollection(b, collectionA));
    }

    @Test
     void testIsEqualCollectionToSelf() {
        assertTrue(CollectionUtils.isEqualCollection(collectionA, collectionA));
        assertTrue(CollectionUtils.isEqualCollection(collectionB, collectionB));
    }

    @Test
     void testIsFullNullColl() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.isFull(null));
    }

    @Test
     void testIsNotEmptyWithEmptyCollection() {
        assertFalse(CollectionUtils.isNotEmpty(new ArrayList<>()));
    }

    @Test
     void testIsNotEmptyWithNonEmptyCollection() {
        assertTrue(CollectionUtils.isNotEmpty(Collections.singletonList("item")));
    }

    @Test
     void testIsNotEmptyWithNull() {
        assertFalse(CollectionUtils.isNotEmpty(null));
    }

    @Test
     void testIsProperSubCollection() {
        final Collection<String> a = new ArrayList<>();
        final Collection<String> b = new ArrayList<>();
        assertFalse(CollectionUtils.isProperSubCollection(a, b));
        b.add("1");
        assertTrue(CollectionUtils.isProperSubCollection(a, b));
        assertFalse(CollectionUtils.isProperSubCollection(b, a));
        assertFalse(CollectionUtils.isProperSubCollection(b, b));
        assertFalse(CollectionUtils.isProperSubCollection(a, a));
        a.add("1");
        a.add("2");
        b.add("2");
        assertFalse(CollectionUtils.isProperSubCollection(b, a));
        assertFalse(CollectionUtils.isProperSubCollection(a, b));
        a.add("1");
        assertTrue(CollectionUtils.isProperSubCollection(b, a));
        assertTrue(CollectionUtils.isProperSubCollection(CollectionUtils.intersection(collectionA, collectionC), collectionA));
        assertTrue(CollectionUtils.isProperSubCollection(CollectionUtils.subtract(a, b), a));
        assertFalse(CollectionUtils.isProperSubCollection(a, CollectionUtils.subtract(a, b)));
    }

    @Test
     void testIsProperSubCollectionNullColl1() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.isProperSubCollection(null, list));
    }

    @Test
     void testIsProperSubCollectionNullColl2() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.isProperSubCollection(list, null));
    }

    @Test
     void testIsSubCollection() {
        assertFalse(CollectionUtils.isSubCollection(collectionA, collectionC));
        assertFalse(CollectionUtils.isSubCollection(collectionC, collectionA));
    }

    @Test
     void testIsSubCollection2() {
        final Collection<Integer> c = new ArrayList<>();
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(1);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(2);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(2);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(3);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(3);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(3);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(4);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(4);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(4);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertFalse(CollectionUtils.isSubCollection(collectionA, c));
        c.add(4);
        assertTrue(CollectionUtils.isSubCollection(c, collectionA));
        assertTrue(CollectionUtils.isSubCollection(collectionA, c));
        c.add(5);
        assertFalse(CollectionUtils.isSubCollection(c, collectionA));
        assertTrue(CollectionUtils.isSubCollection(collectionA, c));
    }

    @Test
     void testIsSubCollectionNullColl1() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.isSubCollection(null, list));
    }

    @Test
     void testIsSubCollectionNullColl2() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.isSubCollection(list, null));
    }

    @Test
     void testIsSubCollectionOfSelf() {
        assertTrue(CollectionUtils.isSubCollection(collectionA, collectionA));
        assertTrue(CollectionUtils.isSubCollection(collectionB, collectionB));
    }

    @Test
    @Deprecated
     void testMatchesAll() {
        assertFalse(CollectionUtils.matchesAll(null, null));
        assertFalse(CollectionUtils.matchesAll(collectionA, null));

        final Predicate<Integer> lessThanFive = object -> object < 5;
        assertTrue(CollectionUtils.matchesAll(collectionA, lessThanFive));

        final Predicate<Integer> lessThanFour = object -> object < 4;
        assertFalse(CollectionUtils.matchesAll(collectionA, lessThanFour));

        assertTrue(CollectionUtils.matchesAll(null, lessThanFour));
        assertTrue(CollectionUtils.matchesAll(emptyCollection, lessThanFour));
    }

    @Test
     void testMaxSizeNullColl() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.maxSize(null));
    }

    @Test
     void testPermutations() {
        final List<Integer> sample = collectionA.subList(0, 5);
        final Collection<List<Integer>> permutations = CollectionUtils.permutations(sample);

        // result size = n!
        final int collSize = sample.size();
        int factorial = 1;
        for (int i = 1; i <= collSize; i++) {
            factorial *= i;
        }
        assertEquals(factorial, permutations.size());
    }

    @Test
     void testPermutationsWithNullCollection() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.permutations(null));
    }

    @Test
     void testPredicatedCollectionNullColl() {
        final Predicate<Object> predicate = PredicateUtils.instanceofPredicate(Integer.class);
        assertThrows(NullPointerException.class, () -> CollectionUtils.predicatedCollection(null, predicate));
    }

    @Test
     void testPredicatedCollectionNullPredicate() {
        final Collection<Integer> list = new ArrayList<>();
        assertThrows(NullPointerException.class, () -> CollectionUtils.predicatedCollection(list, null));
    }

    @Test
     void testRemoveAll() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.removeAll(base, sub);
        assertEquals(1, result.size());
        assertFalse(result.contains("A"));
        assertTrue(result.contains("B"));
        assertFalse(result.contains("C"));
        assertEquals(3, base.size());
        assertTrue(base.contains("A"));
        assertTrue(base.contains("B"));
        assertTrue(base.contains("C"));
        assertEquals(3, sub.size());
        assertTrue(sub.contains("A"));
        assertTrue(sub.contains("C"));
        assertTrue(sub.contains("X"));
    }

    @Test
     void testRemoveAllNullBaseColl() {
        final List<String> sub = new ArrayList<>();
        sub.add("A");
        assertThrows(NullPointerException.class, () -> CollectionUtils.removeAll(null, sub));
    }

    @Test
     void testRemoveAllNullSubColl() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        assertThrows(NullPointerException.class, () -> CollectionUtils.removeAll(base, null));
    }

    @Test
     void testRemoveAllWithEquator() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> remove = new ArrayList<>();
        remove.add("AA");
        remove.add("CX");
        remove.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.removeAll(base, remove, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });

        assertEquals(2, result.size());
        assertTrue(result.contains("AC"));
        assertTrue(result.contains("BB"));
        assertFalse(result.contains("CA"));
        assertEquals(3, base.size());
        assertTrue(base.contains("AC"));
        assertTrue(base.contains("BB"));
        assertTrue(base.contains("CA"));
        assertEquals(3, remove.size());
        assertTrue(remove.contains("AA"));
        assertTrue(remove.contains("CX"));
        assertTrue(remove.contains("XZ"));
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> CollectionUtils.removeAll(null, null, DefaultEquator.defaultEquator()),
                        "expecting NullPointerException"),
                () -> assertThrows(NullPointerException.class, () -> CollectionUtils.removeAll(base, remove, null),
                        "expecting NullPointerException")
        );
    }

    @Test
     void testRemoveCount() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Collection<Integer> result = CollectionUtils.removeCount(list, 0, 0);
        assertEquals(4, list.size());
        assertEquals(0, result.size());

        result = CollectionUtils.removeCount(list, 0, 1);
        assertEquals(3, list.size());
        assertEquals(2, (int) list.get(0));
        assertEquals(1, result.size());
        assertTrue(result.contains(1));

        list.add(5);
        list.add(6);
        result = CollectionUtils.removeCount(list, 1, 3);

        assertEquals(2, list.size());
        assertEquals(2, (int) list.get(0));
        assertEquals(6, (int) list.get(1));
        assertEquals(3, result.size());
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
    }

    @Test
     void testRemoveCountNegative() {
        final Collection<Integer> list = new ArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.removeCount(list, 0, -1));
    }

    @Test
     void testRemoveCountStartNegative() {
        final Collection<Integer> list = new ArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.removeCount(list, -1, 1));
    }

    @Test
     void testRemoveCountWithNull() {
        final Collection<Integer> list = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.removeCount(list, 0, 1));
    }

    @Test
     void testRemoveCountWrongCount() {
        final Collection<Integer> list = new ArrayList<>();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.removeCount(list, 0, 2));
    }

    @Test
     void testRemoveRange() {
        final List<Integer> list = new ArrayList<>();
        list.add(1);
        Collection<Integer> result = CollectionUtils.removeRange(list, 0, 0);
        assertEquals(1, list.size());
        assertEquals(0, result.size());

        list.add(2);
        list.add(3);
        result = CollectionUtils.removeRange(list, 1, 3);
        assertEquals(1, list.size());
        assertEquals(1, (int) list.get(0));
        assertEquals(2, result.size());
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
    }

    @Test
     void testRemoveRangeEndIndexNegative() {
        final Collection<Integer> list = new ArrayList<>();
        list.add(1);
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.removeRange(list, 0, -1));
    }

    @Test
     void testRemoveRangeEndLowStart() {
        final Collection<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.removeRange(list, 1, 0));
    }

    @Test
     void testRemoveRangeNull() {
        final Collection<Integer> list = null;
        assertThrows(NullPointerException.class, () -> CollectionUtils.removeRange(list, 0, 0));
    }

    @Test
     void testRemoveRangeStartIndexNegative() {
        final Collection<Integer> list = new ArrayList<>();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.removeRange(list, -1, 1));
    }

    @Test
     void testRemoveRangeWrongEndIndex() {
        final Collection<Integer> list = new ArrayList<>();
        list.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> CollectionUtils.removeRange(list, 0, 2));
    }

    @Test
     void testRetainAll() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        base.add("B");
        base.add("C");
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        sub.add("C");
        sub.add("X");

        final Collection<String> result = CollectionUtils.retainAll(base, sub);
        assertEquals(2, result.size());
        assertTrue(result.contains("A"));
        assertFalse(result.contains("B"));
        assertTrue(result.contains("C"));
        assertEquals(3, base.size());
        assertTrue(base.contains("A"));
        assertTrue(base.contains("B"));
        assertTrue(base.contains("C"));
        assertEquals(3, sub.size());
        assertTrue(sub.contains("A"));
        assertTrue(sub.contains("C"));
        assertTrue(sub.contains("X"));
    }

    @Test
     void testRetainAllNullBaseColl() {
        final List<Object> sub = new ArrayList<>();
        sub.add("A");
        assertThrows(NullPointerException.class, () -> CollectionUtils.retainAll(null, sub));
    }

    @Test
     void testRetainAllNullSubColl() {
        final List<String> base = new ArrayList<>();
        base.add("A");
        assertThrows(NullPointerException.class, () -> CollectionUtils.retainAll(base, null));
    }

    @Test
     void testRetainAllWithEquator() {
        final List<String> base = new ArrayList<>();
        base.add("AC");
        base.add("BB");
        base.add("CA");

        final List<String> retain = new ArrayList<>();
        retain.add("AA");
        retain.add("CX");
        retain.add("XZ");

        // use an equator which compares the second letter only
        final Collection<String> result = CollectionUtils.retainAll(base, retain, new Equator<String>() {

            @Override
            public boolean equate(final String o1, final String o2) {
                return o1.charAt(1) == o2.charAt(1);
            }

            @Override
            public int hash(final String o) {
                return o.charAt(1);
            }
        });
        assertEquals(1, result.size());
        assertTrue(result.contains("CA"));
        assertFalse(result.contains("BB"));
        assertFalse(result.contains("AC"));

        assertEquals(3, base.size());
        assertTrue(base.contains("AC"));
        assertTrue(base.contains("BB"));
        assertTrue(base.contains("CA"));

        assertEquals(3, retain.size());
        assertTrue(retain.contains("AA"));
        assertTrue(retain.contains("CX"));
        assertTrue(retain.contains("XZ"));
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> CollectionUtils.retainAll(null, null, null),
                        "expecting NullPointerException"),
                () -> assertThrows(NullPointerException.class, () -> CollectionUtils.retainAll(base, retain, null),
                        "expecting NullPointerException")
        );
    }

    @Test
     void testReverseArrayNull() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.reverseArray(null));
    }

    @Test
     void testSize_Array() {
        final Object[] objectArray = {};
        assertEquals(0, CollectionUtils.size(objectArray));

        final String[] stringArray = new String[3];
        assertEquals(3, CollectionUtils.size(stringArray));
        stringArray[0] = "a";
        stringArray[1] = "b";
        stringArray[2] = "c";
        assertEquals(3, CollectionUtils.size(stringArray));
    }

    @Test
     void testSize_Enumeration() {
        final Vector<String> list = new Vector<>();
        assertEquals(0, CollectionUtils.size(list.elements()));
        list.add("a");
        assertEquals(1, CollectionUtils.size(list.elements()));
        list.add("b");
        assertEquals(2, CollectionUtils.size(list.elements()));
    }

    @Test
     void testSize_Iterator() {
        final List<String> list = new ArrayList<>();
        assertEquals(0, CollectionUtils.size(list.iterator()));
        list.add("a");
        assertEquals(1, CollectionUtils.size(list.iterator()));
        list.add("b");
        assertEquals(2, CollectionUtils.size(list.iterator()));
    }

    @Test
     void testSize_List() {
        List<String> list = null;
        assertEquals(0, CollectionUtils.size(list));
        list = new ArrayList<>();
        assertEquals(0, CollectionUtils.size(list));
        list.add("a");
        assertEquals(1, CollectionUtils.size(list));
        list.add("b");
        assertEquals(2, CollectionUtils.size(list));
    }

    @Test
     void testSize_Map() {
        final Map<String, String> map = new HashMap<>();
        assertEquals(0, CollectionUtils.size(map));
        map.put("1", "a");
        assertEquals(1, CollectionUtils.size(map));
        map.put("2", "b");
        assertEquals(2, CollectionUtils.size(map));
    }

    @Test
     void testSize_Other() {
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.size("not a list"));
    }

    @Test
     void testSize_PrimitiveArray() {
        final int[] intArray = {};
        assertEquals(0, CollectionUtils.size(intArray));

        final double[] doubleArray = new double[3];
        assertEquals(3, CollectionUtils.size(doubleArray));
        doubleArray[0] = 0.0d;
        doubleArray[1] = 1.0d;
        doubleArray[2] = 2.5d;
        assertEquals(3, CollectionUtils.size(doubleArray));
    }

    @Test
     void testSizeIsEmpty_Array() {
        final Object[] objectArray = {};
        assertTrue(CollectionUtils.sizeIsEmpty(objectArray));

        final String[] stringArray = new String[3];
        assertFalse(CollectionUtils.sizeIsEmpty(stringArray));
        stringArray[0] = "a";
        stringArray[1] = "b";
        stringArray[2] = "c";
        assertFalse(CollectionUtils.sizeIsEmpty(stringArray));
    }

    @Test
     void testSizeIsEmpty_Enumeration() {
        final Vector<String> list = new Vector<>();
        assertTrue(CollectionUtils.sizeIsEmpty(list.elements()));
        list.add("a");
        assertFalse(CollectionUtils.sizeIsEmpty(list.elements()));
        final Enumeration<String> en = list.elements();
        en.nextElement();
        assertTrue(CollectionUtils.sizeIsEmpty(en));
    }

    @Test
     void testSizeIsEmpty_Iterator() {
        final List<String> list = new ArrayList<>();
        assertTrue(CollectionUtils.sizeIsEmpty(list.iterator()));
        list.add("a");
        assertFalse(CollectionUtils.sizeIsEmpty(list.iterator()));
        final Iterator<String> it = list.iterator();
        it.next();
        assertTrue(CollectionUtils.sizeIsEmpty(it));
    }

    @Test
     void testSizeIsEmpty_List() {
        final List<String> list = new ArrayList<>();
        assertTrue(CollectionUtils.sizeIsEmpty(list));
        list.add("a");
        assertFalse(CollectionUtils.sizeIsEmpty(list));
    }

    @Test
     void testSizeIsEmpty_Map() {
        final Map<String, String> map = new HashMap<>();
        assertTrue(CollectionUtils.sizeIsEmpty(map));
        map.put("1", "a");
        assertFalse(CollectionUtils.sizeIsEmpty(map));
    }

    @Test
     void testSizeIsEmpty_Null() {
        assertTrue(CollectionUtils.sizeIsEmpty(null));
    }

    @Test
     void testSizeIsEmpty_Other() {
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.sizeIsEmpty("not a list"),
                "Expecting IllegalArgumentException");
    }

    @Test
     void testSizeIsEmpty_PrimitiveArray() {
        final int[] intArray = {};
        assertTrue(CollectionUtils.sizeIsEmpty(intArray));

        final double[] doubleArray = new double[3];
        assertFalse(CollectionUtils.sizeIsEmpty(doubleArray));
        doubleArray[0] = 0.0d;
        doubleArray[1] = 1.0d;
        doubleArray[2] = 2.5d;
        assertFalse(CollectionUtils.sizeIsEmpty(doubleArray));
    }

    @Test
     void testSubtract() {
        final Collection<Integer> col = CollectionUtils.subtract(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        assertEquals(Integer.valueOf(1), freq.get(1));
        assertNull(freq.get(2));
        assertNull(freq.get(3));
        assertEquals(Integer.valueOf(2), freq.get(4));
        assertNull(freq.get(5));

        final Collection<Number> col2 = CollectionUtils.subtract(collectionC2, collectionA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        assertEquals(Integer.valueOf(1), freq2.get(5));
        assertNull(freq2.get(4));
        assertNull(freq2.get(3));
        assertEquals(Integer.valueOf(2), freq2.get(2));
        assertNull(freq2.get(1));
    }

    @Test
     void testSubtractNullColl1() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.subtract(null, list));
    }

    @Test
     void testSubtractNullColl2() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.subtract(list, null));
    }

    @Test
     void testSubtractWithPredicate() {
        // greater than 3
        final Predicate<Number> predicate = n -> n.longValue() > 3L;

        final Collection<Number> col = CollectionUtils.subtract(iterableA, collectionC, predicate);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col);
        assertEquals(Integer.valueOf(1), freq2.get(1));
        assertEquals(Integer.valueOf(2), freq2.get(2));
        assertEquals(Integer.valueOf(3), freq2.get(3));
        assertEquals(Integer.valueOf(2), freq2.get(4));
        assertNull(freq2.get(5));
    }

    @Test
    @Deprecated
     void testSynchronizedCollection() {
        final Collection<Object> col = CollectionUtils.synchronizedCollection(new ArrayList<>());
        assertTrue(col instanceof SynchronizedCollection, "Returned object should be a SynchronizedCollection.");

        assertThrows(NullPointerException.class, () -> CollectionUtils.synchronizedCollection(null),
                "Expecting NullPointerException for null collection.");
    }

    @Test
     void testTransformedCollection() {
        final Transformer<Object, Object> transformer = TransformerUtils.nopTransformer();
        final Collection<Object> collection = CollectionUtils.transformingCollection(new ArrayList<>(), transformer);
        assertTrue(collection instanceof TransformedCollection, "returned object should be a TransformedCollection");
    }

    @Test
     void testTransformedCollection_2() {
        final List<Object> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        final Collection<Object> result = CollectionUtils.transformingCollection(list, TRANSFORM_TO_INTEGER);
        assertTrue(result.contains("1")); // untransformed
        assertTrue(result.contains("2")); // untransformed
        assertTrue(result.contains("3")); // untransformed
    }

    @Test
     void testTransformingCollectionNullColl() {
        final Transformer<Object, Object> transformer = TransformerUtils.nopTransformer();
        assertThrows(NullPointerException.class, () -> CollectionUtils.transformingCollection(null, transformer));
    }

    @Test
     void testTransformingCollectionNullTransformer() {
        final List<String> list = new ArrayList<>();
        assertThrows(NullPointerException.class, () -> CollectionUtils.transformingCollection(list, null));
    }

    @Test
     void testUnionNullColl1() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.union(null, list));
    }

    @Test
     void testUnionNullColl2() {
        final Collection<String> list = new ArrayList<>(1);
        list.add("1");
        assertThrows(NullPointerException.class, () -> CollectionUtils.union(list, null));
    }

    @Test
    @Deprecated
     void testUnmodifiableCollection() {
        final Collection<Object> col = CollectionUtils.unmodifiableCollection(new ArrayList<>());
        assertTrue(col instanceof UnmodifiableCollection, "Returned object should be a UnmodifiableCollection.");

        assertThrows(NullPointerException.class, () -> CollectionUtils.unmodifiableCollection(null),
                "Expecting NullPointerException for null collection.");
    }

    @Test
     void transform1() {
        List<Number> list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(list, TRANSFORM_TO_INTEGER);
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        list = new ArrayList<>();
        list.add(1L);
        list.add(2L);
        list.add(3L);
        CollectionUtils.transform(null, TRANSFORM_TO_INTEGER);
        assertEquals(3, list.size());
        CollectionUtils.transform(list, null);
        assertEquals(3, list.size());
        CollectionUtils.transform(null, null);
        assertEquals(3, list.size());
    }

    @Test
     void transform2() {
        final Set<Number> set = new HashSet<>();
        set.add(1L);
        set.add(2L);
        set.add(3L);
        CollectionUtils.transform(set, input -> 4);
        assertEquals(1, set.size());
        assertEquals(4, set.iterator().next());
    }

    @Test
     void union() {
        final Collection<Integer> col = CollectionUtils.union(iterableA, iterableC);
        final Map<Integer, Integer> freq = CollectionUtils.getCardinalityMap(col);
        assertEquals(Integer.valueOf(1), freq.get(1));
        assertEquals(Integer.valueOf(4), freq.get(2));
        assertEquals(Integer.valueOf(3), freq.get(3));
        assertEquals(Integer.valueOf(4), freq.get(4));
        assertEquals(Integer.valueOf(1), freq.get(5));

        final Collection<Number> col2 = CollectionUtils.union(collectionC2, iterableA);
        final Map<Number, Integer> freq2 = CollectionUtils.getCardinalityMap(col2);
        assertEquals(Integer.valueOf(1), freq2.get(1));
        assertEquals(Integer.valueOf(4), freq2.get(2));
        assertEquals(Integer.valueOf(3), freq2.get(3));
        assertEquals(Integer.valueOf(4), freq2.get(4));
        assertEquals(Integer.valueOf(1), freq2.get(5));
    }

    /*
     * These tests were automatically generated by EvoSuite
     * Tue May 16 09:18:40 GMT 2023
     */

    @Test
    @Timeout(4000)
     void test00()  throws Throwable  {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        // Undeclared exception!
        try {
            CollectionUtils.removeRange((Collection<Integer>) linkedList0, (-1), 0);
            fail("Expecting exception: IndexOutOfBoundsException");

        } catch(IndexOutOfBoundsException e) {
            //
            // The start index can't be less than 0.
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
     void test01()  throws Throwable  {
        LinkedHashSet<Locale.FilteringMode> linkedHashSet0 = new LinkedHashSet<Locale.FilteringMode>();
        DefaultEquator<Locale.FilteringMode> defaultEquator0 = DefaultEquator.defaultEquator();
        Collection<Locale.FilteringMode> collection0 = CollectionUtils.retainAll((Iterable<Locale.FilteringMode>) linkedHashSet0, (Iterable<? extends Locale.FilteringMode>) linkedHashSet0, (Equator<? super Locale.FilteringMode>) defaultEquator0);
        Collection<Locale.FilteringMode> collection1 = CollectionUtils.retainAll(collection0, (Collection<?>) linkedHashSet0);
        assertTrue(collection1.equals((Object)collection0));
    }

    @Test
    @Timeout(4000)
     void test02()  throws Throwable  {
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        Iterator<Object> iterator0 = linkedList0.descendingIterator();
        // Undeclared exception!
        try {
            CollectionUtils.get(iterator0, 0);
            fail("Expecting exception: IndexOutOfBoundsException");

        } catch(IndexOutOfBoundsException e) {
            //
            // Entry does not exist: 0
            //
            // The exception was thrown as expected
            assertTrue(true); //
        }
    }

    @Test
    @Timeout(4000)
     void test03()  throws Throwable  {
        // Undeclared exception!
        try {
            CollectionUtils.union((Iterable<? extends Integer>) null, (Iterable<? extends Integer>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test04()  throws Throwable  {
        // Undeclared exception!
        try {
            CollectionUtils.isSubCollection((Collection<?>) null, (Collection<?>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test05()  throws Throwable  {
        // Undeclared exception!
        try {
            CollectionUtils.isEqualCollection((Collection<?>) null, (Collection<?>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test06()  throws Throwable  {
        // Undeclared exception!
        try {
            CollectionUtils.intersection((Iterable<? extends Integer>) null, (Iterable<? extends Integer>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test07()  throws Throwable  {
        // Undeclared exception!
        try {
            CollectionUtils.collate((Iterable<? extends Integer>) null, (Iterable<? extends Integer>) null, true);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // iterableA
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test08()  throws Throwable  {
        LinkedHashSet<Locale.FilteringMode> linkedHashSet0 = new LinkedHashSet<Locale.FilteringMode>();
        boolean boolean0 = CollectionUtils.isEmpty(linkedHashSet0);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
     void test09()  throws Throwable  {
        Class<Locale.FilteringMode> class0 = Locale.FilteringMode.class;
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        ServiceLoader<Locale.FilteringMode> serviceLoader0 = ServiceLoader.load(class0, classLoader0);
        Collection<Locale.FilteringMode> collection0 = CollectionUtils.subtract((Iterable<? extends Locale.FilteringMode>) serviceLoader0, (Iterable<? extends Locale.FilteringMode>) serviceLoader0);
        Stack<Integer> stack0 = new Stack<Integer>();
        FixedSizeList<Integer> fixedSizeList0 = FixedSizeList.fixedSizeList((List<Integer>) stack0);
        ListIterator<Integer> listIterator0 = fixedSizeList0.listIterator();
        HashMap<Integer, Locale.FilteringMode> hashMap0 = new HashMap<Integer, Locale.FilteringMode>();
        Transformer<Integer, Locale.FilteringMode> transformer0 = MapTransformer.mapTransformer((Map<? super Integer, ? extends Locale.FilteringMode>) hashMap0);
        Collection<Locale.FilteringMode> collection1 = CollectionUtils.collect((Iterator<? extends Integer>) listIterator0, (Transformer<? super Integer, ? extends Locale.FilteringMode>) transformer0, collection0);
        assertSame(collection0, collection1);
    }

    @Test
    @Timeout(4000)
     void test10()  throws Throwable  {
        Class<Object> class0 = Object.class;
        InstanceofPredicate instanceofPredicate0 = new InstanceofPredicate(class0);
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        Iterator<Object> iterator0 = linkedList0.descendingIterator();
        Class<Object>[] classArray0 = (Class<Object>[]) Array.newInstance(Class.class, 2);
        InvokerTransformer<Object, LinkedList<Object>> invokerTransformer0 = new InvokerTransformer<Object, LinkedList<Object>>("", classArray0, classArray0);
        IfTransformer<Object, LinkedList<Object>> ifTransformer0 = new IfTransformer<Object, LinkedList<Object>>(instanceofPredicate0, invokerTransformer0, invokerTransformer0);
        Collection<LinkedList<Object>> collection0 = CollectionUtils.collect(iterator0, (Transformer<? super Object, ? extends LinkedList<Object>>) ifTransformer0);
        // Undeclared exception!
        try {
            CollectionUtils.extractSingleton(collection0);
            fail("Expecting exception: IllegalArgumentException");

        } catch(IllegalArgumentException e) {
            //
            // Can extract singleton only when collection size == 1
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test11()  throws Throwable  {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>(linkedList0);
        // Undeclared exception!
        try {
            CollectionUtils.removeRange((Collection<Integer>) treeSet0, 0, 2);
            fail("Expecting exception: IndexOutOfBoundsException");

        } catch(IndexOutOfBoundsException e) {
            //
            // The end index can't be greater than the size of collection.
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test12()  throws Throwable  {
        Stack<InstanceofPredicate> stack0 = new Stack<InstanceofPredicate>();
        Collection<InstanceofPredicate> collection0 = CollectionUtils.removeRange((Collection<InstanceofPredicate>) stack0, 0, 0);
        assertNotNull(collection0);
    }

    @Test
    @Timeout(4000)
     void test13()  throws Throwable  {
        HashSet<Integer> hashSet0 = new HashSet<Integer>();
        DefaultEquator<Integer> defaultEquator0 = DefaultEquator.defaultEquator();
        Collection<Integer> collection0 = CollectionUtils.retainAll((Iterable<Integer>) hashSet0, (Iterable<? extends Integer>) hashSet0, (Equator<? super Integer>) defaultEquator0);
        boolean boolean0 = CollectionUtils.containsAny((Collection<?>) collection0, (Collection<?>) hashSet0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
     void test14()  throws Throwable  {
        ArrayList<Integer> arrayList0 = new ArrayList<Integer>(1);
        CollectionUtils.permutations((Collection<Integer>) arrayList0);
        assertTrue(arrayList0.isEmpty());
        assertEquals(0, arrayList0.size());
    }

    @Test
    @Timeout(4000)
     void test15()  throws Throwable  {
        Stack<Integer> stack0 = new Stack<Integer>();
        FixedSizeList<Integer> fixedSizeList0 = FixedSizeList.fixedSizeList((List<Integer>) stack0);
        boolean boolean0 = CollectionUtils.isFull(fixedSizeList0);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
     void test16()  throws Throwable  {
        Object[] objectArray0 = new Object[8];
        CollectionUtils.reverseArray(objectArray0);
        assertEquals(8, objectArray0.length);
    }

    @Test
    @Timeout(4000)
     void test17()  throws Throwable  {
        Collection<Integer> collection0 = CollectionUtils.emptyCollection();
        boolean boolean0 = CollectionUtils.sizeIsEmpty(collection0);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
     void test18()  throws Throwable  {
        Integer integer0 = new Integer(3);
        // Undeclared exception!
        try {
            CollectionUtils.size(integer0);
            fail("Expecting exception: IllegalArgumentException");

        } catch(IllegalArgumentException e) {
            //
            // Unsupported object type: java.lang.Integer
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test19()  throws Throwable  {
        Locale.Category locale_Category0 = Locale.Category.DISPLAY;
        // Undeclared exception!
        try {
            CollectionUtils.get((Object) locale_Category0, (-551));
            fail("Expecting exception: IndexOutOfBoundsException");

        } catch(IndexOutOfBoundsException e) {
            //
            // Index cannot be negative: -551
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test20()  throws Throwable  {
        // Undeclared exception!
        try {
            CollectionUtils.get((Object) null, 0);
            fail("Expecting exception: IllegalArgumentException");

        } catch(IllegalArgumentException e) {
            //
            // Unsupported object type: null
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test21()  throws Throwable  {
        // Undeclared exception!
        try {
            CollectionUtils.checkIndexBounds((-518));
            fail("Expecting exception: IndexOutOfBoundsException");

        } catch(IndexOutOfBoundsException e) {
            //
            // Index cannot be negative: -518
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test22()  throws Throwable  {
        CircularFifoQueue<Integer> circularFifoQueue0 = new CircularFifoQueue<Integer>(1633);
        Integer integer0 = new Integer(1633);
        boolean boolean0 = CollectionUtils.addIgnoreNull((Collection<Integer>) circularFifoQueue0, integer0);
        assertTrue(circularFifoQueue0.contains(1633));
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
     void test23()  throws Throwable  {
        Predicate<Object> predicate0 = NotNullPredicate.notNullPredicate();
        Vector<Collection<Object>> vector0 = new Vector<Collection<Object>>();
        boolean boolean0 = CollectionUtils.exists((Iterable<Collection<Object>>) vector0, (Predicate<? super Collection<Object>>) predicate0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
     void test24()  throws Throwable  {
        LinkedHashSet<LinkedList<Integer>> linkedHashSet0 = new LinkedHashSet<LinkedList<Integer>>();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        linkedHashSet0.add(linkedList0);
        Predicate<LinkedList<Integer>> predicate0 = EqualPredicate.equalPredicate(linkedList0);
        boolean boolean0 = CollectionUtils.filterInverse((Iterable<LinkedList<Integer>>) linkedHashSet0, (Predicate<? super LinkedList<Integer>>) predicate0);
        assertFalse(linkedHashSet0.contains(linkedList0));
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
     void test25()  throws Throwable  {
        LinkedHashSet<LinkedList<Integer>> linkedHashSet0 = new LinkedHashSet<LinkedList<Integer>>();
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        Predicate<LinkedList<Integer>> predicate0 = EqualPredicate.equalPredicate(linkedList0);
        boolean boolean0 = CollectionUtils.filterInverse((Iterable<LinkedList<Integer>>) linkedHashSet0, (Predicate<? super LinkedList<Integer>>) predicate0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
     void test28()  throws Throwable  {
        Integer integer0 = new Integer((-2264));
        EqualPredicate<Integer> equalPredicate0 = new EqualPredicate<Integer>(integer0);
        ArrayDeque<Integer> arrayDeque0 = new ArrayDeque<Integer>();
        Integer integer1 = CollectionUtils.find((Iterable<Integer>) arrayDeque0, (Predicate<? super Integer>) equalPredicate0);
        assertNull(integer1);
    }

    @Test
    @Timeout(4000)
     void test29()  throws Throwable  {
        LinkedHashSet<LinkedList<Integer>> linkedHashSet0 = new LinkedHashSet<LinkedList<Integer>>();
        DefaultEquator<LinkedList<Integer>> defaultEquator0 = DefaultEquator.defaultEquator();
        boolean boolean0 = CollectionUtils.isEqualCollection((Collection<? extends LinkedList<Integer>>) linkedHashSet0, (Collection<? extends LinkedList<Integer>>) linkedHashSet0, (Equator<? super LinkedList<Integer>>) defaultEquator0);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
     void test30()  throws Throwable  {
        LinkedList<Locale.Category> linkedList0 = new LinkedList<Locale.Category>();
        Locale.Category locale_Category0 = Locale.Category.DISPLAY;
        linkedList0.add(locale_Category0);
        linkedList0.add(locale_Category0);
        boolean boolean0 = CollectionUtils.isEqualCollection((Collection<?>) linkedList0, (Collection<?>) linkedList0);
        assertTrue(boolean0);
    }

    @Test
    @Timeout(4000)
     void test31()  throws Throwable  {
        HashSet<Integer> hashSet0 = new HashSet<Integer>();
        Integer[] integerArray0 = new Integer[2];
        boolean boolean0 = CollectionUtils.containsAny((Collection<?>) hashSet0, integerArray0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
     void test32()  throws Throwable  {
        Class<Locale.FilteringMode> class0 = Locale.FilteringMode.class;
        ClassLoader classLoader0 = ClassLoader.getSystemClassLoader();
        ServiceLoader<Locale.FilteringMode> serviceLoader0 = ServiceLoader.load(class0, classLoader0);
        Collection<Locale.FilteringMode> collection0 = CollectionUtils.subtract((Iterable<? extends Locale.FilteringMode>) serviceLoader0, (Iterable<? extends Locale.FilteringMode>) serviceLoader0);
        boolean boolean0 = CollectionUtils.isNotEmpty(collection0);
        assertFalse(boolean0);
    }

    @Test
    @Timeout(4000)
     void test34()  throws Throwable  {
        Stack<Integer> stack0 = new Stack<Integer>();
        List<Integer> list0 = CollectionUtils.collate((Iterable<? extends Integer>) stack0, (Iterable<? extends Integer>) stack0, false);
        assertEquals(0, list0.size());
    }


    @Test
    @Timeout(4000)
     void test36()  throws Throwable  {
        HashMap<Transformer<Locale.Category, Locale.Category>, Locale.Category> hashMap0 = new HashMap<Transformer<Locale.Category, Locale.Category>, Locale.Category>();
        // Undeclared exception!
        try {
            CollectionUtils.get((Map<Transformer<Locale.Category, Locale.Category>, Locale.Category>) hashMap0, 10);
            fail("Expecting exception: IndexOutOfBoundsException");

        } catch(IndexOutOfBoundsException e) {
            //
            // Entry does not exist: 10
            //
            // The exception was thrown as expected
            assertTrue(true); // or any other assertion you may need
        }
    }

    @Test
    @Timeout(4000)
     void test37()  throws Throwable  {
        Stack<Integer> stack0 = new Stack<Integer>();
        Integer integer0 = new Integer((-2264));
        EqualPredicate<Integer> equalPredicate0 = new EqualPredicate<Integer>(integer0);
        Class<Integer>[] classArray0 = (Class<Integer>[]) Array.newInstance(Class.class, 2);
        Transformer<Integer, Integer> transformer0 = InvokerTransformer.invokerTransformer("924&b.Ji{.:Zb", (Class<?>[]) classArray0, (Object[]) classArray0);
        IfTransformer<Integer, Integer> ifTransformer0 = new IfTransformer<Integer, Integer>(equalPredicate0, transformer0, transformer0);
        Collection<Integer> collection0 = CollectionUtils.transformingCollection((Collection<Integer>) stack0, (Transformer<? super Integer, ? extends Integer>) ifTransformer0);
        assertFalse(collection0.contains(integer0));
    }

    @Test
    @Timeout(4000)
     void test38()  throws Throwable  {
        Class<Object> class0 = Object.class;
        InstanceofPredicate instanceofPredicate0 = new InstanceofPredicate(class0);
        LinkedList<Object> linkedList0 = new LinkedList<Object>();
        Iterator<Object> iterator0 = linkedList0.descendingIterator();
        Class<Object>[] classArray0 = (Class<Object>[]) Array.newInstance(Class.class, 2);
        InvokerTransformer<Object, LinkedList<Object>> invokerTransformer0 = new InvokerTransformer<Object, LinkedList<Object>>("", classArray0, classArray0);
        IfTransformer<Object, LinkedList<Object>> ifTransformer0 = new IfTransformer<Object, LinkedList<Object>>(instanceofPredicate0, invokerTransformer0, invokerTransformer0);
        Collection<LinkedList<Object>> collection0 = CollectionUtils.collect(iterator0, (Transformer<? super Object, ? extends LinkedList<Object>>) ifTransformer0);
        Collection<LinkedList<Object>> collection1 = CollectionUtils.disjunction((Iterable<? extends LinkedList<Object>>) collection0, (Iterable<? extends LinkedList<Object>>) collection0);
        assertNotSame(collection1, collection0);
    }

    @Test
    @Timeout(4000)
     void test39()  throws Throwable  {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        Collection<Integer> collection0 = CollectionUtils.synchronizedCollection((Collection<Integer>) linkedList0);
        assertNotNull(collection0);
    }

    @Test
    @Timeout(4000)
     void test40()  throws Throwable  {
        LinkedList<Integer> linkedList0 = new LinkedList<Integer>();
        TreeSet<Integer> treeSet0 = new TreeSet<Integer>(linkedList0);
        Integer integer0 = new Integer((-2794));
        treeSet0.add(integer0);
        Collection<Integer> collection0 = CollectionUtils.union((Iterable<? extends Integer>) treeSet0, (Iterable<? extends Integer>) treeSet0);
        DefaultEquator<Integer> defaultEquator0 = DefaultEquator.defaultEquator();
        Collection<Integer> collection1 = CollectionUtils.removeAll((Iterable<Integer>) collection0, (Iterable<? extends Integer>) collection0, (Equator<? super Integer>) defaultEquator0);
        assertFalse(collection1.equals((Object)collection0));
    }

}
