/**
 * Scaffolding file used to store all the setups needed to run 
 * tests automatically generated by EvoSuite
 * Tue May 16 09:48:59 GMT 2023
 */

package org.apache.commons.collections4.map;

import org.evosuite.runtime.annotation.EvoSuiteClassExclude;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.evosuite.runtime.sandbox.Sandbox;
import org.evosuite.runtime.sandbox.Sandbox.SandboxMode;

import static org.evosuite.shaded.org.mockito.Mockito.*;
@EvoSuiteClassExclude
public class AbstractHashedMap_ESTest_scaffolding {

  @org.junit.Rule
  public org.evosuite.runtime.vnet.NonFunctionalRequirementRule nfr = new org.evosuite.runtime.vnet.NonFunctionalRequirementRule();

  private static final java.util.Properties defaultProperties = (java.util.Properties) java.lang.System.getProperties().clone(); 

  private org.evosuite.runtime.thread.ThreadStopper threadStopper =  new org.evosuite.runtime.thread.ThreadStopper (org.evosuite.runtime.thread.KillSwitchHandler.getInstance(), 3000);


  @BeforeClass
  public static void initEvoSuiteFramework() { 
    org.evosuite.runtime.RuntimeSettings.className = "org.apache.commons.collections4.map.AbstractHashedMap"; 
    org.evosuite.runtime.GuiSupport.initialize(); 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfThreads = 100; 
    org.evosuite.runtime.RuntimeSettings.maxNumberOfIterationsPerLoop = 10000; 
    org.evosuite.runtime.RuntimeSettings.mockSystemIn = true; 
    org.evosuite.runtime.RuntimeSettings.sandboxMode = org.evosuite.runtime.sandbox.Sandbox.SandboxMode.RECOMMENDED; 
    org.evosuite.runtime.sandbox.Sandbox.initializeSecurityManagerForSUT(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.init();
    setSystemProperties();
    initializeClasses();
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    try { initMocksToAvoidTimeoutsInTheTests(); } catch(ClassNotFoundException e) {} 
  } 

  @AfterClass
  public static void clearEvoSuiteFramework(){ 
    Sandbox.resetDefaultSecurityManager(); 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
  } 

  @Before
  public void initTestCase(){ 
    threadStopper.storeCurrentThreads();
    threadStopper.startRecordingTime();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().initHandler(); 
    org.evosuite.runtime.sandbox.Sandbox.goingToExecuteSUTCode(); 
    setSystemProperties(); 
    org.evosuite.runtime.GuiSupport.setHeadless(); 
    org.evosuite.runtime.Runtime.getInstance().resetRuntime(); 
    org.evosuite.runtime.agent.InstrumentingAgent.activate(); 
  } 

  @After
  public void doneWithTestCase(){ 
    threadStopper.killAndJoinClientThreads();
    org.evosuite.runtime.jvm.ShutdownHookHandler.getInstance().safeExecuteAddedHooks(); 
    org.evosuite.runtime.classhandling.JDKClassResetter.reset(); 
    resetClasses(); 
    org.evosuite.runtime.sandbox.Sandbox.doneWithExecutingSUTCode(); 
    org.evosuite.runtime.agent.InstrumentingAgent.deactivate(); 
    org.evosuite.runtime.GuiSupport.restoreHeadlessMode(); 
  } 

  public static void setSystemProperties() {
 
    java.lang.System.setProperties((java.util.Properties) defaultProperties.clone()); 
    java.lang.System.setProperty("user.dir", "C:\\Users\\Utente\\IdeaProjects\\commons-collections"); 
    java.lang.System.setProperty("java.io.tmpdir", "C:\\Users\\Utente\\AppData\\Local\\Temp\\"); 
  }

  private static void initializeClasses() {
    org.evosuite.runtime.classhandling.ClassStateSupport.initializeClasses(AbstractHashedMap_ESTest_scaffolding.class.getClassLoader() ,
      "org.apache.commons.collections4.MapIterator",
      "org.apache.commons.collections4.KeyValue",
      "org.apache.commons.collections4.map.AbstractHashedMap",
      "org.apache.commons.collections4.map.AbstractHashedMap$KeySetIterator",
      "org.apache.commons.collections4.iterators.AbstractEmptyMapIterator",
      "org.apache.commons.collections4.map.AbstractHashedMap$EntrySet",
      "org.apache.commons.collections4.Get",
      "org.apache.commons.collections4.ResettableIterator",
      "org.apache.commons.collections4.iterators.AbstractEmptyIterator",
      "org.apache.commons.collections4.map.AbstractHashedMap$HashMapIterator",
      "org.apache.commons.collections4.iterators.EmptyIterator",
      "org.apache.commons.collections4.Put",
      "org.apache.commons.collections4.map.AbstractHashedMap$Values",
      "org.apache.commons.collections4.map.AbstractHashedMap$HashIterator",
      "org.apache.commons.collections4.map.AbstractHashedMap$ValuesIterator",
      "org.apache.commons.collections4.iterators.EmptyMapIterator",
      "org.apache.commons.collections4.IterableMap",
      "org.apache.commons.collections4.map.AbstractHashedMap$EntrySetIterator",
      "org.apache.commons.collections4.map.AbstractHashedMap$HashEntry",
      "org.apache.commons.collections4.map.AbstractHashedMap$KeySet",
      "org.apache.commons.collections4.IterableGet"
    );
  } 
  private static void initMocksToAvoidTimeoutsInTheTests() throws ClassNotFoundException { 
    mock(Class.forName("java.lang.Comparable", false, AbstractHashedMap_ESTest_scaffolding.class.getClassLoader()));
    mock(Class.forName("java.util.function.BiConsumer", false, AbstractHashedMap_ESTest_scaffolding.class.getClassLoader()));
  }

  private static void resetClasses() {
    org.evosuite.runtime.classhandling.ClassResetter.getInstance().setClassLoader(AbstractHashedMap_ESTest_scaffolding.class.getClassLoader()); 

    org.evosuite.runtime.classhandling.ClassStateSupport.resetClasses(
      "org.apache.commons.collections4.map.AbstractHashedMap",
      "org.apache.commons.collections4.map.AbstractHashedMap$HashEntry",
      "org.apache.commons.collections4.map.AbstractHashedMap$HashIterator",
      "org.apache.commons.collections4.map.AbstractHashedMap$HashMapIterator",
      "org.apache.commons.collections4.map.AbstractHashedMap$EntrySet",
      "org.apache.commons.collections4.map.AbstractHashedMap$EntrySetIterator",
      "org.apache.commons.collections4.map.AbstractHashedMap$KeySet",
      "org.apache.commons.collections4.map.AbstractHashedMap$KeySetIterator",
      "org.apache.commons.collections4.map.AbstractHashedMap$Values",
      "org.apache.commons.collections4.map.AbstractHashedMap$ValuesIterator",
      "org.apache.commons.collections4.iterators.AbstractEmptyIterator",
      "org.apache.commons.collections4.iterators.AbstractEmptyMapIterator",
      "org.apache.commons.collections4.iterators.EmptyMapIterator",
      "org.apache.commons.collections4.iterators.EmptyIterator"
    );
  }
}
