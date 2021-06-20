package com.my.resttemplete.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@GetMapping("/ping")
	public String ping() {
		return "hello world";
	}

	@GetMapping("/test/1/email/{email}")
	public String email(@PathVariable String email) {
		System.out.println("[CONTROLLER] email:" + email);
		return email;
	}

	@GetMapping("/test/2")
	public String queryEmail(@RequestParam String email) {
		System.out.println("[CONTROLLER] email:" + email);
		return email;
	}
}
