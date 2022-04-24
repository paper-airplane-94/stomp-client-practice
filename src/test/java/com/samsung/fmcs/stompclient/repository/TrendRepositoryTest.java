package com.samsung.fmcs.stompclient.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

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
}