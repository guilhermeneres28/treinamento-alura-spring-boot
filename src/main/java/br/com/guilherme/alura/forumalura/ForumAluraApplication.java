package br.com.guilherme.alura.forumalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ForumAluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForumAluraApplication.class, args);
	}

}
