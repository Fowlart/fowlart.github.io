package data_base;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Repository
@Slf4j
public class Jdbc_WordTranslateRepository implements WordTranslateRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public Jdbc_WordTranslateRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private WordTranslate mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        WordTranslate returned_word = new WordTranslate();
        returned_word.setId(Long.valueOf(rs.getString("id")));
        returned_word.setUkrword(rs.getString("ukrword"));
        returned_word.setEngword(rs.getString("engword"));
        returned_word.setPoints(Integer.valueOf(rs.getString("points")));
        return returned_word;
    }

    @Override
    public Iterable<WordTranslate> findAll() {
        return jdbc.query("select id, engword, ukrword, points from WordTranslate", this::mapRowToIngredient);
    }

    @Override
    public WordTranslate findById(Long id) {
        return jdbc.queryForObject("select id, engword, ukrword, points from WordTranslate where id=?", this::mapRowToIngredient, id);
    }


    @Override
    public Long save(WordTranslate word) {

        SimpleJdbcInsert wordTranslateInsert = (new SimpleJdbcInsert(jdbc)).withTableName("WordTranslate").
                usingGeneratedKeyColumns(new String[]{"id"});

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(word, Map.class);

        long id = (long) wordTranslateInsert.executeAndReturnKey(map);

        return new Long(id);
    }

}