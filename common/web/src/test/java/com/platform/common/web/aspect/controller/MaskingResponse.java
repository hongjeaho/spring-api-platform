package com.platform.common.web.aspect.controller;

import com.platform.common.web.annotation.ApplyMasking;
import com.platform.common.web.annotation.MaskingPattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaskingResponse {
    @ApplyMasking(pattern = MaskingPattern.NAME)
    private String name;;

    @ApplyMasking(pattern = MaskingPattern.PHONE)
    private String phoneNumber;

    @ApplyMasking(pattern = MaskingPattern.EMAIL)
    private String email;
}
