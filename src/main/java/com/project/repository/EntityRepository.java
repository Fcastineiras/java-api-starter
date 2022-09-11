package com.project.repository;

import static com.project.utils.Reader.read;

import com.project.dto.request.EntityBodyReq;
import com.project.model.Entity;
import com.project.repository.rowMapper.EntityRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class EntityRepository {

  private final Logger logger = LoggerFactory.getLogger(EntityRepository.class);

  @Autowired public NamedParameterJdbcTemplate jdbcTemplate;

  public Entity getEntityByCriteria(EntityBodyReq criteria) {
    try {
      final String query = read("db/scripts/entity/search_entity_by_criteria.sql");
      final SqlParameterSource params = getParams(criteria);

      return jdbcTemplate.queryForObject(query, params, new EntityRowMapper());
    } catch (EmptyResultDataAccessException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"); // message with details
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private SqlParameterSource getParams(EntityBodyReq criteria) {
    final MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("small_id", criteria.getSomeSmallId());
    params.addValue("id", criteria.getSomeBigId());
    params.addValue("price", criteria.getSomePrice());
    params.addValue("enum", criteria.getSomeEnum().name());
    params.addValue("some_date", criteria.getSomeDate().toString());
    return params;
  }
}
