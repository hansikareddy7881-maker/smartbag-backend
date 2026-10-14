package com.smartbag.controller;


import com.smartbag.recommendation.DailyBagRecommendation;
import com.smartbag.recommendation.DailyBagService;
import com.smartbag.service.SubjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/daily-bag")
public class DailyBagController {

    private final DailyBagService dailyBagService;
    private final SubjectService subjectService;

    public DailyBagController(
            DailyBagService dailyBagService,
            SubjectService subjectService) {

        this.dailyBagService = dailyBagService;
        this.subjectService = subjectService;
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<DailyBagRecommendation> getDailyBag(
            @PathVariable Long subjectId) {

        return subjectService.getSubjectById(subjectId)
                .map(subject -> ResponseEntity.ok(
                        dailyBagService.getRecommendation(subject)
                ))
                .orElse(ResponseEntity.notFound().build());
    }
}