package com.sharmchait.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator apiPrefixConfig(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(path -> path
						.path("/eazybank/accounts/**")
						.filters(filter -> filter
								.rewritePath("/eazybank/accounts/(?<segment>.*)", "/${segment}"))
						.uri("lb://ACCOUNTS"))
				.route(path -> path
						.path("/eazybank/loans/**")
						.filters(filter -> filter
								.rewritePath("/eazybank/loans/(?<segment>.*)", "/${segment}"))
						.uri("lb://LOANS"))
				.route(path -> path
						.path("/eazybank/cards/**")
						.filters(filter -> filter
								.rewritePath("/eazybank/cards/(?<segment>.*)", "/${segment}"))
						.uri("lb://CARDS"))
				.build();
	}

}
