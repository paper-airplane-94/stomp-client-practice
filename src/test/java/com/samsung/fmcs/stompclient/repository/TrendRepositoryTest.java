package com.samsung.fmcs.stompclient.repository;

import com.samsung.fmcs.stompclient.entity.Trend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
class TrendRepositoryTest {
    private final TrendRepository trendRepository;

    @Autowired
    public TrendRepositoryTest(TrendRepository trendRepository) {
        this.trendRepository = trendRepository;
    }

    @Test
    void 트렌드_조회() {
        trendRepository.findAll()
                .stream()
                .forEach(System.out::println);
    }

    @Transactional(readOnly = true)
    @Test
    void 트렌드_스트림_조회() {
        try(Stream<Trend> stream =trendRepository.streamAll()) {
            stream.forEach(System.out::println);
        }
    }
}