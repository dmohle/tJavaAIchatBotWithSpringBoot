package com.example.springboot;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@RestController
public class HelloController {

	@GetMapping("/")
	public ResponseEntity<String> index() throws IOException {
		// Load the content of the static/index.html file
		Resource resource = new ClassPathResource("static/index.html");
		if (resource.exists()) {
			byte[] contentBytes = Files.readAllBytes(resource.getFile().toPath());
			String content = new String(contentBytes, StandardCharsets.UTF_8);

			// Set the Content-Type header to indicate HTML content
			return ResponseEntity.ok()
					.contentType(MediaType.TEXT_HTML)
					.body(content);
		} else {
			// Return a 404 Not Found response if the file doesn't exist
			return ResponseEntity.notFound().build();
		}
	}
}
