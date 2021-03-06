package com.example.gebol.data;

import com.example.gebol.model.persistent.Discipline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
public class JdbcDisciplineRepository implements DisciplineRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcDisciplineRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Discipline> findAll() {
        log.info("findAll");
        return jdbc.query(
                "select * from Discipline",
                this::mapRowToDiscipline);
    }

    public List<Discipline> findAllSortedByDate() {
        log.info("findAll");
        return jdbc.query(
                "select * from Discipline order by date",
                this::mapRowToDiscipline);
    }

    public Discipline findOne(String name) {
        log.info("findOne");

        String sql = "select * from Discipline where lower(name) = lower(?)";
        return jdbc.queryForObject(
                sql,
                (rs, rowNum) ->
                        new Discipline(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("location"),
                                rs.getTimestamp("date").toLocalDateTime(),
                                rs.getBoolean("isCup")),
                name);
    }

    public String getNameById(Long id) {
        String sql = "select * from Discipline where id = (?)";
        Discipline d = jdbc.queryForObject(sql, this::mapRowToDiscipline, id);
        return d.getName();
    }

    public Discipline findById(Long id) {
        String sql = "select * from Discipline where id = (?)";
        return jdbc.queryForObject(sql, this::mapRowToDiscipline, id);
    }

    public Discipline save(Discipline discipline) {
        log.info("save");
        PreparedStatementCreator creator =
                new PreparedStatementCreatorFactory(
                        "insert into Discipline (name, location, date, isCup) values (?, ?, ?, ?)",
                        Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP, Types.BOOLEAN
                ) {
                    {
                        setReturnGeneratedKeys(true);
                        setGeneratedKeysColumnNames("id");
                    }
                }.newPreparedStatementCreator(
                        Arrays.asList(
                                discipline.getName(),
                                discipline.getLocation(),
                                Timestamp.valueOf(discipline.getDate()),
                                discipline.isCup()));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(creator, keyHolder);

        return discipline;
    }

    private Discipline mapRowToDiscipline(ResultSet rs, int rowNum) throws SQLException {
        Discipline d = new Discipline();
        d.setId(rs.getLong("id"));
        d.setName(rs.getString("name"));
        d.setLocation(rs.getString("location"));

        Timestamp timestamp = rs.getTimestamp("date");
        LocalDateTime locDate = timestamp.toLocalDateTime();
        d.setDate(locDate);

        d.setCup(rs.getBoolean("isCup"));

        return d;
    }

    public void deleteById(Long id) {
        String sql = "delete from Discipline where id = (?)";
        jdbc.update(sql, id);
    }
}
