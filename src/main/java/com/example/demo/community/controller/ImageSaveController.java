package com.example.demo.community.controller;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ImageSaveController {

	@PostMapping("/image/save")
	public String saveImage(MultipartFile file) {
		log.info("file=>{}",file);
		return "test";
	}
}
