package com.samsung.fmcs.stompclient.client;

import com.samsung.fmcs.stompclient.handler.TrendStompSessionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompClient {
    private final MessageConverter converter;
    private final TrendStompSessionHandler trendStompSessionHandler;
    private final WebSocketStompClient webSocketStompClient = new WebSocketStompClient(new StandardWebSocketClient());

    @PostConstruct
    private void init() {
        webSocketStompClient.setMessageConverter(converter);
        webSocketStompClient.connect("ws://192.168.0.44:9090/trend", trendStompSessionHandler);
        webSocketStompClient.setAutoStartup(true);
    }

}
