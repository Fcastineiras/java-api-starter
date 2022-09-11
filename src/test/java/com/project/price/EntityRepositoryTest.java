package com.project.price;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.project.dto.request.EntityBodyReq;
import com.project.repository.EntityRepository;
import com.project.repository.rowMapper.EntityRowMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

public class EntityRepositoryTest {

  private final NamedParameterJdbcTemplate jdbcTemplate =
      Mockito.mock(NamedParameterJdbcTemplate.class);
  private final EntityRepository entityRepository = new EntityRepository();

  @BeforeEach
  public void setUp() {
    ReflectionTestUtils.setField(entityRepository, "jdbcTemplate", jdbcTemplate);
  }

  @Test
  public void givenJdbcTemplateThrowExceptionWhenGetPriceByCriteriaThenThrowException() {
    when(jdbcTemplate.queryForObject(
            any(), any(MapSqlParameterSource.class), any(EntityRowMapper.class)))
        .thenThrow(new RuntimeException());
    try {
      entityRepository.getEntityByCriteria(new EntityBodyReq());
    } catch (Exception e) {
      Assertions.assertEquals(ResponseStatusException.class, e.getClass());
    }
  }
}
