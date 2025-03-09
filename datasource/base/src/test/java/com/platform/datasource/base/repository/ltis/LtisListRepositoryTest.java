package com.platform.datasource.base.repository.ltis;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.platform.common.base.BaseSpringBootTest;
import com.platform.datasource.base.dto.ltis.LtisListSearch;
import com.platform.datasource.base.dto.ltis.LtisResult;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LtisListRepositoryTest extends BaseSpringBootTest {

  @Autowired
  private LtisListRepository ltisListRepository;

  @DisplayName("LTIS 검증 - 첫번째 페이지 ")
  @Test
  public void findPageByOnePage() {
    var search = LtisListSearch.builder().build();

    var total = ltisListRepository.findTotalSize(search);
    var list = ltisListRepository.findPage(search);

    assertThat(14).isEqualTo(total);
    assertThat(10).isEqualTo(list.size());
  }

  @DisplayName("LTIS 검증 - 두번째 페이지")
  @Test
  public void findPageByTwoPage() {
    var search = LtisListSearch.builder().build();
    search.setPage(1);
    var total = ltisListRepository.findTotalSize(search);
    var list = ltisListRepository.findPage(search);

    assertThat(14).isEqualTo(total);
    assertThat(4).isEqualTo(list.size());
  }

  @DisplayName("LTIS 검증 - 검색 조건")
  @Test
  public void findPageByCondition() {
    var search = LtisListSearch.builder().keyword("24XX003").build();

    var total = ltisListRepository.findTotalSize(search);
    var list = ltisListRepository.findPage(search);

    assertEquals(1, total);
    assertEquals(1, list.size());
    assertThat(list).extracting(LtisResult::getCaseNo, LtisResult::getCaseTitle)
        .containsExactly(Tuple.tuple("24XX003", "사건 3"));
  }
}
