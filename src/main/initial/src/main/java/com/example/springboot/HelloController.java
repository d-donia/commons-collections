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
package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Apache Commons Collections!";
	}

	@GetMapping("/merge")
	public String merge(@RequestParam(value = "list1") String[] list1, @RequestParam(value = "list2") String[] list2) {
		List<String> l1 = Arrays.asList(list1);
		List<String> l2 = Arrays.asList(list2);
		List<String> mergedList = CollectionUtils.collate(l1, l2);
		return "List1: " + l1 + "\nList2: " + l2 + "\nMerged list: " + mergedList;
	}

}
