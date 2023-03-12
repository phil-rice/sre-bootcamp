package dev.srebootcamp.images.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

import java.sql.PreparedStatement;
import java.util.List;

public interface JdbcHelper {
    /**
     * The sql should have a single <ids> in it  for the ids
     */
    static <T> List<T> getFromIds(JdbcTemplate template, String sql, List<Object> params, List<String> ids, RowMapper<T> rowMapper) {
        String questionMarks = StringHelpers.repeat("?", ids.size(), ",");
        PreparedStatementCreator getFromIdsPSCreator = (con) -> {
            PreparedStatement ps = con.prepareStatement(sql.replace("<ids>", questionMarks));
            ListHelpers.forEachWithIndex(params, (param, i) -> ps.setObject(i + 1, param));
            ListHelpers.forEachWithIndex(ids, (id, i) -> ps.setString(i + 1 + params.size(), id));
            return ps;
        };
        return template.query(getFromIdsPSCreator, rowMapper);
    }
}
