package com.example.gebol.data;

import com.example.gebol.model.Discipline;
import com.example.gebol.model.Participant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Part;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
public class JdbcParticipantRepository implements ParticipantRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcParticipantRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Participant> findAll() {
        return jdbc.query(

                "select * from Participant",
                this::mapRowToParticipant);
    }

    public Participant findOne(Long id) {
        String sql = "select * from Participant where lower(id) = lower(?)";
        return jdbc.queryForObject(
                sql,
                (rs, rowNum) ->
                        new Participant(
                                rs.getLong("id"),
                                rs.getString("name")
                        ),
                id);
    }

    public Boolean hasParticipant(String name) {
        String sql = "select * from Participant where lower(name) = lower(?)";
        try {
            jdbc.queryForObject(
                    sql,
                    (rs, rowNum) ->
                            new Participant(
                                    rs.getLong("id"),
                                    rs.getString("name")
                            ),
                    name);

            return true;

        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public String getNameById(Long id) {
        String sql = "select * from Participant where id = (?)";
        Participant p = jdbc.queryForObject(sql, this::mapRowToParticipant, id);
        return p.getName();
    }

    public Participant save(Participant participant) {
        PreparedStatementCreator creator =
                new PreparedStatementCreatorFactory(
                        "insert into Participant (id, name) values (?, ?)",
                        Types.VARCHAR, Types.VARCHAR
                ).newPreparedStatementCreator(
                        Arrays.asList(
                                participant.getId(),
                                participant.getName()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(creator, keyHolder);
        return participant;
    }

    public Participant mapRowToParticipant(ResultSet rs, int rowNum) throws SQLException {
        Participant p = new Participant();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        return p;
    }

    public void deleteById(Long id) {
        String sql = "delete from Participant where id = (?)";
        jdbc.update(sql, id);
    }
}
