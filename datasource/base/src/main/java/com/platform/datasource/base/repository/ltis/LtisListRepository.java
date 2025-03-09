package com.platform.datasource.base.repository.ltis;

import static com.platform.datasource.base.util.JooqDateConditionUtil.betweenDateNotNull;
import static com.platform.datasource.base.util.JooqStringConditionUtil.likeIfNotBlank;

import com.platform.datasource.base.config.database.PlatFormTransactional;
import com.platform.datasource.base.dto.ltis.LtisListSearch;
import com.platform.datasource.base.dto.ltis.LtisResult;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.generated.tables.JLtisListApi;
import org.springframework.stereotype.Repository;

@PlatFormTransactional
@Repository
@RequiredArgsConstructor
public class LtisListRepository {

  private final DSLContext dslContext;
  private final JLtisListApi LTISLISTAPI = JLtisListApi.LTIS_LIST_API;

  /**
   * LTIS 입력 정보 Count
   *
   * @param search 검색조건
   * @return Total Count
   */
  public Integer findTotalSize(LtisListSearch search) {
    return dslContext.selectCount().from(LTISLISTAPI)
        .where(getCondition(search))
        .fetchOne(0, Integer.class);
  }

  public List<LtisResult> findPage(LtisListSearch search) {
    return dslContext.select(
            LTISLISTAPI.JUDG_SEQ,
            LTISLISTAPI.CASE_NO,
            LTISLISTAPI.CASE_TITLE,
            LTISLISTAPI.IMPLEMENTER_NM,
            LTISLISTAPI.JUDG_DIV_CD,
            LTISLISTAPI.JUDG_DIV_NM,
            LTISLISTAPI.ADDRESS
        ).from(LTISLISTAPI)
        .where(getCondition(search))
        .offset(search.getPage() * search.getPageSize())
        .limit(search.getPageSize())
        .fetchInto(LtisResult.class);
  }

  private Condition getCondition(LtisListSearch search) {
    return likeIfNotBlank(LTISLISTAPI.ADDRESS, search.getAddress()) // 소재지
        .and(
            likeIfNotBlank(LTISLISTAPI.CASE_NO, search.getKeyword()).or(
                likeIfNotBlank(LTISLISTAPI.CASE_TITLE, search.getKeyword())
            )
        )
        .and(
            likeIfNotBlank(LTISLISTAPI.IMPLEMENTER_NM, search.getImplementerNm()) // 사업시행자
        )
        .and(
            betweenDateNotNull(LTISLISTAPI.RECEP_DT, search.getStartRecepDt(),
                search.getEndRecepDt()) // 접수일
        );
  }
}
