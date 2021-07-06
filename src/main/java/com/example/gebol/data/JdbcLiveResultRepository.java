package com.example.gebol.data;

import com.example.gebol.model.persistent.LiveResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Slf4j
public class JdbcLiveResultRepository implements LiveResultRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public JdbcLiveResultRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public LiveResult save(LiveResult result) {
        jdbc.update("insert into LiveResult (disciplineId, bracketLevel, place, participantId, score) values (?, ?, ?, ?, ?)",
                result.getDisciplineId(), result.getLevel(), result.getPlace(), result.getParticipantId(), result.getScore());
        return result;
    }

    public LiveResult update(LiveResult result) {
        String update = "update LiveResult set participantId = (?), score = (?), knockedOut = (?)";
        String where = "where disciplineId = (?) and bracketLevel = (?) and place = (?)";

        jdbc.update(update + where,
                result.getParticipantId(),
                result.getScore(),
                result.getKnockedOut(),
                result.getDisciplineId(),
                result.getLevel(),
                result.getPlace()
                );
        return result;
    }

    @Override
    public List<LiveResult> saveAll(List<LiveResult> liveResults) {
        for (LiveResult result : liveResults) {
            save(result);
        }
        return liveResults;
    }

    @Override
    public List<LiveResult> updateAll(List<LiveResult> liveResults) {
        for (LiveResult result : liveResults) {
            update(result);
        }
        return liveResults;
    }

    @Override
    public List<LiveResult> findByDisciplineId(Long disciplineId) {
        String sql = "select * from LiveResult where disciplineId = (?)";
        return jdbc.query(sql, this::mapRowToResult, disciplineId);
    }

    @Override
    public Boolean hasDisciplineId(Long disciplineId) {
        String sql = "select disciplineId from LiveResult where disciplineId = (?)";
        List<Long> list = jdbc.queryForList(sql, Long.class, disciplineId);
        return list.size() > 0;
    }

    @Override
    public List<Long> findAllDisciplineIds() {
        String sql = "select distinct disciplineId from LiveResult";
        return jdbc.queryForList(sql, Long.class);
    }

    private LiveResult mapRowToResult(ResultSet rs, int rowNum) throws SQLException {
        LiveResult r = new LiveResult();
        r.setDisciplineId(rs.getLong("disciplineId"));
        r.setParticipantId(rs.getLong("participantId"));
        r.setPlace(rs.getInt("place"));
        r.setLevel(rs.getInt("bracketLevel"));
        r.setScore(rs.getInt("score"));
        return r;
    }
}
