package com.samsung.fmcs.stompclient.handler;

import com.google.common.collect.Lists;
import com.samsung.fmcs.stompclient.dto.TrendDto;
import com.samsung.fmcs.stompclient.dto.TrendRequestDto;
import com.samsung.fmcs.stompclient.dto.TrendResponseDto;
import com.samsung.fmcs.stompclient.service.TrendCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class TrendStompSessionHandler extends StompSessionHandlerAdapter {
    private final TrendCoreService trendCoreService;
    private Optional<StompSession> stompSession = Optional.empty();

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return TrendRequestDto.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        TrendRequestDto requestDto = (TrendRequestDto) payload;
        log.info("Thread Id : {}", Thread.currentThread());
        response(requestDto);
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        stompSession = Optional.of(session);
        stompSession.ifPresent(sess -> sess.subscribe("/topic/trend", this));
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Exception : {}", exception.getMessage());
    }

    public void response(TrendRequestDto requestDto) {
        List<TrendDto> trends = trendCoreService.response(requestDto);
        List<List<TrendDto>> partitions = Lists.partition(trends, 100);
        for (List<TrendDto> partition : partitions) {
            TrendResponseDto responseDto = TrendResponseDto.builder()
                    .requestId(requestDto.getRequestId())
                    .trends(partition)
                    .build();
            stompSession.ifPresent(sess -> sess.send("/app/trend", responseDto));
        }
    }

}
