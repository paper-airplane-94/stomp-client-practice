package com.samsung.fmcs.stompclient.repository;

import com.samsung.fmcs.stompclient.entity.Trend;
import com.samsung.fmcs.stompclient.vo.TrendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface TrendRepository extends JpaRepository<Trend, TrendId> {
    @Query("select t from TH_CM_FMCS_TRND t")
    Stream<Trend> streamAll();

}
