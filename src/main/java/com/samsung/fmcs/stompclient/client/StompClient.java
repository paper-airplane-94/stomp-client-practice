package com.samsung.fmcs.stompclient.client;

import com.samsung.fmcs.stompclient.handler.TrendStompSessionHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompClient {
    private final TrendStompSessionHandler trendStompSessionHandler;
    private final WebSocketStompClient webSocketStompClient = new WebSocketStompClient(new StandardWebSocketClient());
    private final TaskScheduler taskScheduler;

    @PostConstruct
    private void init() {
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());
        webSocketStompClient.connect("ws://192.168.0.44:9090/trend", trendStompSessionHandler);
        webSocketStompClient.setAutoStartup(true);
        webSocketStompClient.setTaskScheduler(taskScheduler);
    }

}
