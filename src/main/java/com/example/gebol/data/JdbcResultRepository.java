package com.example.gebol.data;

import com.example.gebol.model.Discipline;
import com.example.gebol.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
public class JdbcResultRepository implements ResultRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcResultRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Result save(Result result) {
        jdbc.update("insert into Result (disciplineId, participantId, place) values (?, ?, ?)",
                result.getDisciplineId(), result.getParticipantId(), result.getPlace());
        return result;
    }

    @Override
    public List<Result> saveAll(List<Result> results) {
        for (Result result : results) {
            save(result);
        }
        return results;
    }

    @Override
    public List<Result> findByDisciplineId(Long disciplineId) {
        String sql = "select * from Result where disciplineId = (?)";
        return jdbc.query(sql, this::mapRowToResult, disciplineId);
    }

    @Override
    public List<Result> findByParticipantId(Long participantId) {
        String sql = "select * from Result where participantId = (?)";
        return jdbc.query(sql, this::mapRowToResult, participantId);
    }

    @Override
    public List<Long> findAllDisciplineIds() {
        String sql = "select distinct disciplineId from Result";
        return jdbc.queryForList(sql, Long.class);
    }

    @Override
    public void deleteByDisciplineId(Long id) {
        String sql = "delete from Result where disciplineId = (?)";
        jdbc.update(sql, id);
    }

    @Override
    public boolean hasParticipantId(Long id) {
        String sql = "select participantId from Result where participantId = (?)";
        List<Long> list = jdbc.queryForList(sql, Long.class, id);
        return list.size() > 0;
    }

    @Override
    public boolean hasDisciplineId(Long id) {
        String sql = "select disciplineId from Result where disciplineId = (?)";
        List<Long> list = jdbc.queryForList(sql, Long.class, id);
        return list.size() > 0;
    }

    private Result mapRowToResult(ResultSet rs, int rowNum) throws SQLException {
        Result r = new Result();
        r.setDisciplineId(rs.getLong("disciplineId"));
        r.setParticipantId(rs.getLong("participantId"));
        r.setPlace(rs.getInt("place"));
        return r;
    }
}
