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


/*
 * This file was automatically generated by EvoSuite
 * Tue May 16 10:39:43 GMT 2023
 */

package org.apache.commons.collections4.trie;

import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.collections4.trie.KeyAnalyzer;
import org.apache.commons.collections4.trie.analyzer.StringKeyAnalyzer;
import org.evosuite.runtime.EvoRunner;
import org.evosuite.runtime.EvoRunnerParameters;
import org.junit.runner.RunWith;

@RunWith(EvoRunner.class) @EvoRunnerParameters(mockJVMNonDeterminism = true, useVFS = true, useVNET = true, resetStaticState = true, separateClassLoader = true) 
public class KeyAnalyzer_ESTest extends KeyAnalyzer_ESTest_scaffolding {

  @Test
  public void test00()  throws Throwable  {
      StringKeyAnalyzer stringKeyAnalyzer0 = StringKeyAnalyzer.INSTANCE;
      int int0 = stringKeyAnalyzer0.compare("", "org.apache.commons.collections4.trie.KeyAnalyzer");
      assertEquals((-48), int0);
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isValidBitIndex(0);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isNullBitKey((-3));
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isEqualBitKey((-1103));
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isOutOfBoundsIndex((-1419));
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      StringKeyAnalyzer stringKeyAnalyzer0 = StringKeyAnalyzer.INSTANCE;
      int int0 = stringKeyAnalyzer0.compare(")0KriI fd", ")0KriI fd");
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      StringKeyAnalyzer stringKeyAnalyzer0 = new StringKeyAnalyzer();
      int int0 = stringKeyAnalyzer0.compare((String) null, "|)");
      assertEquals((-1), int0);
  }

  @Test(timeout = 4000)
  public void test07()  throws Throwable  {
      StringKeyAnalyzer stringKeyAnalyzer0 = new StringKeyAnalyzer();
      int int0 = stringKeyAnalyzer0.compare((String) null, (String) null);
      assertEquals(0, int0);
  }

  @Test(timeout = 4000)
  public void test08()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isValidBitIndex(1210);
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test09()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isValidBitIndex((-2));
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test10()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isNullBitKey((-1));
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test11()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isNullBitKey(0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test12()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isEqualBitKey((-2));
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test13()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isEqualBitKey(0);
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test14()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isOutOfBoundsIndex((-3));
      assertTrue(boolean0);
  }

  @Test(timeout = 4000)
  public void test15()  throws Throwable  {
      boolean boolean0 = KeyAnalyzer.isOutOfBoundsIndex((-2));
      assertFalse(boolean0);
  }

  @Test(timeout = 4000)
  public void test16()  throws Throwable  {
      StringKeyAnalyzer stringKeyAnalyzer0 = new StringKeyAnalyzer();
      int int0 = stringKeyAnalyzer0.compare("", (String) null);
      assertEquals(1, int0);
  }
}
