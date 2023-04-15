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
