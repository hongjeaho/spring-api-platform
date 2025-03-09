package com.platform.api.busan.implementer.controller;

import com.platform.api.busan.implementer.dto.ImplementerApplicationResponse;
import com.platform.api.busan.implementer.service.ImplementerService;
import com.platform.datasource.base.dto.ltis.LtisListSearch;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Implementer Application API", description = "사업 시행자 API")
@RestController
@RequestMapping("/api/implementer/application")
@RequiredArgsConstructor
public class ImplementerApplicationController {

  private final ImplementerService implementerService;

  @Operation(summary = "LTIS 입력리스트 조회", description = "LTIS에 접수된 정보를 내려준다.")
  @GetMapping
  public ImplementerApplicationResponse getImplementerApplication(
      @ParameterObject LtisListSearch search) {
    return implementerService.getImplementerApplication(search);
  }

}
