package com.sharmachait.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
	info = @Info(
		title="Accounts API",
		description = "some description",
		version = "v1",
		contact = @Contact(
				name = "Chaitanya Sharma",
				email = "chait8126@gmail.com",
				url = "https://github.com/sharmachait"
		),
		license = @License(
			name= "Apache 2.0",
			url = "https://github.com/sharmachait"
		)
	),
	externalDocs = @ExternalDocumentation(
		description = "Documentation website",
		url = "https://github.com/sharmachait"
	)
)
public class AccountsApplication {
	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}
}