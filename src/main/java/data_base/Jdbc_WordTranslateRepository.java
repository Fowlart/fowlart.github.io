package data_base;

import entities.WordTranslate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
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

    private WordTranslate find(String ukrword, String engword) {
        return jdbc.queryForObject("select id, engword, ukrword, points from WordTranslate where ( (ukrword LIKE ?) AND " +
                "(engword LIKE ?))", this::mapRowToIngredient, ukrword, engword);
    }

    @Override
    public WordTranslate save(WordTranslate word) {
        jdbc.update(
                "insert into WordTranslate (engword, ukrword, points) values (?,?,?)",
                // word.getId(),
                word.getEngword(),
                word.getUkrword(),
                word.getPoints());
        return find(word.getUkrword(), word.getEngword());
    }
}