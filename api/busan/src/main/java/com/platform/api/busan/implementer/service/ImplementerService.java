package com.platform.api.busan.implementer.service;

import com.platform.api.busan.implementer.dto.ImplementerApplicationResponse;
import com.platform.datasource.base.config.database.PlatFormTransactional;
import com.platform.datasource.base.dto.ltis.LtisListSearch;
import com.platform.datasource.base.repository.ltis.LtisListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@PlatFormTransactional
public class ImplementerService {

  private final LtisListRepository ltisListRepository;

  /**
   * LTIS 입력 정보를 조회 한다.
   *
   * @param search 검색조건
   * @return 검색결과
   */
  public ImplementerApplicationResponse getImplementerApplication(LtisListSearch search) {
    return ImplementerApplicationResponse.builder()
        .total(ltisListRepository.findTotalSize(search))
        .resultList(ltisListRepository.findPage(search))
        .build();
  }
}
