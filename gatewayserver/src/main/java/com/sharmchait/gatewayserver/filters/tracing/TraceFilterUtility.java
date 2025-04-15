package com.sharmchait.gatewayserver.filters.tracing;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;

@Component
public class TraceFilterUtility {
    public static final String correlationIdHeader = "eazybank-correlation-id";
    public String getCorrelationId(HttpHeaders requestHeaders) {
        if(requestHeaders.get(correlationIdHeader)!=null){
            List<String> headers = requestHeaders.get(correlationIdHeader);
            return headers.stream().findFirst().get();
        }
        return null;
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value){
        return exchange.mutate()
                .request(
                        exchange.getRequest().mutate().header(name, value).build()
                ).build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationid) {
        return this.setRequestHeader(exchange, correlationIdHeader, correlationid);
    }
}
