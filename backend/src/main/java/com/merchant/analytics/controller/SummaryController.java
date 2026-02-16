package com.merchant.analytics.controller;

import com.merchant.analytics.dto.SummaryResponse;
import com.merchant.analytics.service.SummaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SummaryController {

    private final SummaryService summaryService;

    public SummaryController(SummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @GetMapping("/api/summary/mtd")
    public SummaryResponse getMTDSummary(
            @RequestParam(required = false) String cardBrand,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String declineReasonCode
    ) {
        return summaryService.getMonthToDateSummary(
                cardBrand,
                status,
                declineReasonCode
        );
    }

    @GetMapping("/api/summary/monthly")
    public Map<String, SummaryResponse> getMonthlySummary(
            @RequestParam(required = false) String cardBrand,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String declineReasonCode
    ) {
        return summaryService.getMonthlySummary(
                cardBrand,
                status,
                declineReasonCode
        );
    }
}
