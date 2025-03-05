package com.platform.common.web.aspect.controller;

import com.platform.common.web.annotation.ApplyMasking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class MaskingController {

    @GetMapping
    public MaskingResponse testMasking() {
        return new MaskingResponse("어드민", "01012341234", "admin@gmail.com");
    }

    @GetMapping("/masking")
    @ApplyMasking
    public MaskingResponse testMaskingEx() {
        return new MaskingResponse("어드민", "01012341234", "admin@gmail.com");
    }

    @GetMapping("/masking/list")
    @ApplyMasking
    public List<MaskingResponse> testMaskingList() {
        return List.of(
                new MaskingResponse("어드민", "01012341234", "admin@gmail.com"),
                new MaskingResponse("시행자", "01012341234", "bdmin@gmail.com")
        );
    }
}
