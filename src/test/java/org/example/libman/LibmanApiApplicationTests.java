package org.example.libman;

import org.example.libman.controllers.BookController;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LibmanApiApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(LibmanApiApplicationTests.class);

	@Autowired
	private BookController bookController;

	@Test
	void checkBookControllerOnLoad() {
		assertThat(bookController).isNotNull();
	}
}
