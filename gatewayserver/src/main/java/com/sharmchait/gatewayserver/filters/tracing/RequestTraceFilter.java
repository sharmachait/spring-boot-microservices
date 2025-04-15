package com.sharmchait.gatewayserver.filters.tracing;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;

import java.util.UUID;

@Order(1)
@Component
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger logger  = LoggerFactory.getLogger(RequestTraceFilter.class);

    @Autowired
    TraceFilterUtility traceFilterUtility;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if(isCorrelationIdPresent(requestHeaders)){
            logger.debug("correlation if found: {}", traceFilterUtility.getCorrelationId(requestHeaders));
        }else{
            String correlationid = generateCorrelationId();
            exchange = traceFilterUtility.setCorrelationId(exchange, correlationid);
            logger.debug("correlation Id set in request, {}", correlationid);
        }
        return chain.filter(exchange);
    }

    private String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        return traceFilterUtility.getCorrelationId(requestHeaders) != null;
    }
}
