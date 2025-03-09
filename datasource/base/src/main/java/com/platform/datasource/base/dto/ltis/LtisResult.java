package com.platform.datasource.base.dto.ltis;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "LTIS 정보")
public class LtisResult {

  @Schema(description = "재결일련번호")
  private final long judgSeq;
  @Schema(description = "사건번호")
  private final String caseNo;
  @Schema(description = "사건명")
  private final String caseTitle;
  @Schema(description = "시행사명")
  private final String implementerNm;
  @Schema(description = "진행상태 코드")
  private final String judgDivCd;
  @Schema(description = "진행상태 명")
  private final String judgDivNm;
  @Schema(description = "소재지")
  private final String address;
}
