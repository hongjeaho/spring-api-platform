package com.platform.api.busan.implementer.dto;

import com.platform.datasource.base.dto.ltis.LtisResult;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "LTIS 입력 조회 결과")
public class ImplementerApplicationResponse {

  @Schema(description = "전체 count")
  private int total;
  @Schema(description = "조회 결과")
  private List<LtisResult> resultList;
}
