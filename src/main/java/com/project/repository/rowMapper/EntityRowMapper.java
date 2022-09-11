package com.project.repository.rowMapper;

import static com.project.utils.AdapterUtils.stringToTime;

import com.project.model.Entity;
import com.project.model.SomeEnum;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class EntityRowMapper implements RowMapper<Entity> {

  @Override
  public Entity mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Entity(
        rs.getInt("small_id"),
        rs.getLong("id"),
        rs.getBigDecimal("price"),
        SomeEnum.valueOfCaseIgnore(rs.getString("enum")),
        stringToTime(rs.getString("some_date")));
  }
}
