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
package org.apache.commons.collections4.iterators;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

 public class EntrySetMapIteratorTest {
    @Test(timeout = 4000)
     void test00()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        Integer integer0 = hashMap0.putIfAbsent((Integer) null, (Integer) null);
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        entrySetMapIterator0.next();
        Integer integer1 = entrySetMapIterator0.setValue(integer0);
        assertNull(integer1);
    }

    @Test(timeout = 4000)
     void test01()  throws Throwable  {
        HashMap<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>> hashMap0 = new HashMap<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>>(1373);
        EntrySetMapIterator<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>> entrySetMapIterator0 = new EntrySetMapIterator<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>>(hashMap0);
        Integer integer0 = new Integer(1373);
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry0 = new AbstractMap.SimpleImmutableEntry<Integer, Integer>(integer0, integer0);
        AbstractMap.SimpleEntry<Integer, Integer> abstractMap_SimpleEntry0 = new AbstractMap.SimpleEntry<Integer, Integer>(abstractMap_SimpleImmutableEntry0);
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry1 = new AbstractMap.SimpleImmutableEntry<Integer, Integer>(abstractMap_SimpleEntry0);
        hashMap0.put(abstractMap_SimpleImmutableEntry1, abstractMap_SimpleImmutableEntry1);
        entrySetMapIterator0.reset();
        boolean boolean0 = entrySetMapIterator0.hasNext();
        assertTrue(boolean0);
    }

    @Test(timeout = 4000)
     void test02()  throws Throwable  {
        HashMap<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> hashMap0 = new HashMap<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>>();
        EntrySetMapIterator<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> entrySetMapIterator0 = new EntrySetMapIterator<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>>(hashMap0);
        boolean boolean0 = entrySetMapIterator0.hasNext();
        assertFalse(boolean0);
    }

    @Test(timeout = 4000)
     void test03()  throws Throwable  {
        HashMap<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>> hashMap0 = new HashMap<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>>();
        Integer integer0 = new Integer(1);
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry0 = new AbstractMap.SimpleImmutableEntry<Integer, Integer>(integer0, integer0);
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry1 = new AbstractMap.SimpleImmutableEntry<Integer, Integer>(abstractMap_SimpleImmutableEntry0);
        hashMap0.put(abstractMap_SimpleImmutableEntry1, abstractMap_SimpleImmutableEntry1);
        EntrySetMapIterator<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>> entrySetMapIterator0 = new EntrySetMapIterator<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>>(hashMap0);
        entrySetMapIterator0.next();
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry2 = entrySetMapIterator0.getKey();
        assertSame(abstractMap_SimpleImmutableEntry2, abstractMap_SimpleImmutableEntry1);
    }

    @Test(timeout = 4000)
     void test04()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        Integer integer0 = new Integer((-575));
        Integer integer1 = hashMap0.put(integer0, integer0);
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        entrySetMapIterator0.next();
        hashMap0.put((Integer) null, integer1);
        // Undeclared exception!
        try {
            entrySetMapIterator0.remove();
            fail("Expecting exception: ConcurrentModificationException");

        } catch(ConcurrentModificationException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);
        }
    }

    @Test(timeout = 4000)
     void test05()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        // Undeclared exception!
        try {
            entrySetMapIterator0.next();
            fail("Expecting exception: NoSuchElementException");

        } catch(NoSuchElementException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);

        }
    }

    @Test(timeout = 4000)
     void test06()  throws Throwable  {
        HashMap<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>> hashMap0 = new HashMap<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>>();
        EntrySetMapIterator<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>> entrySetMapIterator0 = new EntrySetMapIterator<AbstractMap.SimpleImmutableEntry<Integer, Integer>, AbstractMap.SimpleImmutableEntry<Integer, Integer>>(hashMap0);
        hashMap0.put((AbstractMap.SimpleImmutableEntry<Integer, Integer>) null, (AbstractMap.SimpleImmutableEntry<Integer, Integer>) null);
        // Undeclared exception!
        try {
            entrySetMapIterator0.next();
            fail("Expecting exception: ConcurrentModificationException");

        } catch(ConcurrentModificationException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);

        }
    }

    @Test(timeout = 4000)
     void test07()  throws Throwable  {
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = null;
        try {
            entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>((Map<Integer, Integer>) null);
            fail("Expecting exception: NullPointerException");

        } catch(NullPointerException e) {
            //
            // no message in exception (getMessage() returned null)
            //
            assertTrue(true);

        }
    }
/*
    @Test(timeout = 4000)
    public void test08()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        Integer integer0 = new Integer((-1));
        hashMap0.put(integer0, (Integer) null);
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        Consumer<Integer> consumer0 = (Consumer<Integer>) mock(Consumer.class, new ViolatedAssumptionAnswer());
        entrySetMapIterator0.forEachRemaining(consumer0);
        Integer integer1 = entrySetMapIterator0.getValue();
        assertNull(integer1);
    }*/

    @Test(timeout = 4000)
     void test09()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        hashMap0.putIfAbsent((Integer) null, (Integer) null);
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        entrySetMapIterator0.next();
        Integer integer0 = entrySetMapIterator0.getKey();
        assertNull(integer0);
    }

    @Test(timeout = 4000)
     void test10()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        String string0 = entrySetMapIterator0.toString();
        assertEquals("MapIterator[]", string0);
    }

    @Test(timeout = 4000)
     void test11()  throws Throwable  {
        Integer integer0 = new Integer(535);
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        // Undeclared exception!
        try {
            entrySetMapIterator0.setValue(integer0);
            fail("Expecting exception: IllegalStateException");

        } catch(IllegalStateException e) {
            //
            // Iterator setValue() can only be called after next() and before remove()
            //
            assertTrue(true);

        }
    }

    @Test(timeout = 4000)
     void test12()  throws Throwable  {
        HashMap<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> hashMap0 = new HashMap<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>>();
        Integer integer0 = new Integer(535);
        AbstractMap.SimpleEntry<Integer, Integer> abstractMap_SimpleEntry0 = new AbstractMap.SimpleEntry<Integer, Integer>(integer0, integer0);
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry0 = new AbstractMap.SimpleImmutableEntry<Integer, Integer>(abstractMap_SimpleEntry0);
        hashMap0.put(integer0, abstractMap_SimpleImmutableEntry0);
        EntrySetMapIterator<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> entrySetMapIterator0 = new EntrySetMapIterator<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>>(hashMap0);
        entrySetMapIterator0.next();
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry1 = entrySetMapIterator0.setValue(abstractMap_SimpleImmutableEntry0);
        assertSame(abstractMap_SimpleImmutableEntry1, abstractMap_SimpleImmutableEntry0);
    }

    @Test(timeout = 4000)
     void test13()  throws Throwable  {
        HashMap<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer> hashMap0 = new HashMap<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer>();
        EntrySetMapIterator<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer> entrySetMapIterator0 = new EntrySetMapIterator<AbstractMap.SimpleImmutableEntry<Integer, Integer>, Integer>(hashMap0);
        // Undeclared exception!
        try {
            entrySetMapIterator0.getValue();
            fail("Expecting exception: IllegalStateException");

        } catch(IllegalStateException e) {
            //
            // Iterator getValue() can only be called after next() and before remove()
            //
            assertTrue(true);

        }
    }

    @Test(timeout = 4000)
     void test14()  throws Throwable  {
        HashMap<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> hashMap0 = new HashMap<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>>();
        EntrySetMapIterator<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> entrySetMapIterator0 = new EntrySetMapIterator<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>>(hashMap0);
        // Undeclared exception!
        try {
            entrySetMapIterator0.getKey();
            fail("Expecting exception: IllegalStateException");

        } catch(IllegalStateException e) {
            //
            // Iterator getKey() can only be called after next() and before remove()
            //
            assertTrue(true);

        }
    }

    @Test(timeout = 4000)
     void test15()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        hashMap0.put((Integer) null, (Integer) null);
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        entrySetMapIterator0.next();
        String string0 = entrySetMapIterator0.toString();
        assertEquals("MapIterator[null=null]", string0);
    }

    @Test(timeout = 4000)
     void test16()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        // Undeclared exception!
        try {
            entrySetMapIterator0.remove();
            fail("Expecting exception: IllegalStateException");

        } catch(IllegalStateException e) {
            //
            // Iterator remove() can only be called once after next()
            //
            assertTrue(true);

        }
    }

    @Test(timeout = 4000)
     void test17()  throws Throwable  {
        HashMap<Integer, Integer> hashMap0 = new HashMap<Integer, Integer>();
        Integer integer0 = new Integer((-575));
        hashMap0.put(integer0, integer0);
        EntrySetMapIterator<Integer, Integer> entrySetMapIterator0 = new EntrySetMapIterator<Integer, Integer>(hashMap0);
        entrySetMapIterator0.next();
        entrySetMapIterator0.remove();
    }

  /*  @Test(timeout = 4000)
    public void test18()  throws Throwable  {
        HashMap<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> hashMap0 = new HashMap<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>>();
        Integer integer0 = new Integer(0);
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry0 = new AbstractMap.SimpleImmutableEntry<Integer, Integer>(integer0, integer0);
        AbstractMap.SimpleEntry<Integer, Integer> abstractMap_SimpleEntry0 = new AbstractMap.SimpleEntry<Integer, Integer>(abstractMap_SimpleImmutableEntry0);
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry1 = new AbstractMap.SimpleImmutableEntry<Integer, Integer>(abstractMap_SimpleEntry0);
        hashMap0.put(integer0, abstractMap_SimpleImmutableEntry1);
        EntrySetMapIterator<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>> entrySetMapIterator0 = new EntrySetMapIterator<Integer, AbstractMap.SimpleImmutableEntry<Integer, Integer>>(hashMap0);
        Consumer<Integer> consumer0 = (Consumer<Integer>) mock(Consumer.class, new ViolatedAssumptionAnswer());
        entrySetMapIterator0.forEachRemaining(consumer0);
        AbstractMap.SimpleImmutableEntry<Integer, Integer> abstractMap_SimpleImmutableEntry2 = entrySetMapIterator0.getValue();
        assertSame(abstractMap_SimpleImmutableEntry2, abstractMap_SimpleImmutableEntry1);
    }*/
}
