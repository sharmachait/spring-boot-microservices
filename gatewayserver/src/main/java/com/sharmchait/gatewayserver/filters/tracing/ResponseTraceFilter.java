package com.sharmchait.gatewayserver.filters.tracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Order(2)
public class ResponseTraceFilter implements GlobalFilter {

    private static final Logger logger  = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    TraceFilterUtility traceFilterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange)
                .then(
                        Mono.fromRunnable(()->{
                            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
                            String correlationId = traceFilterUtility.getCorrelationId(requestHeaders);
                            logger.debug("Added correlation id to the response headers: {}", correlationId);
                            exchange.getResponse().getHeaders().add(TraceFilterUtility.correlationIdHeader, correlationId);
                        })
                ); // the .then is what makes it a post filter
    }

}