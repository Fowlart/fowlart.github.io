package data_base.jdbc;

import com.fasterxml.jackson.databind.ObjectMapper;
import data_base.WordTranslateRepository;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * There aren’t any statements or connections being created. And, after the method is
 * finished, there isn’t any cleanup of those objects. Finally, there isn’t any handling of
 * exceptions that can’t properly be handled in a catch block. What’s left is code that’s
 * focused solely on performing a query (the call to JdbcTemplate’s queryForObject()
 * method) and mapping the results to an Ingredient object (in the mapRowToWord method).
 */
@Repository
/**As you can see, Jdbc_WordTranslateRepository is annotated with @Repository. This
 annotation is one of a handful of stereotype annotations that Spring defines, including
 @Controller and @Component. By annotating this with
 @Repository, you declare that it should be automatically discovered by Spring component
 scanning and instantiated as a bean in the Spring application context.*/
@Slf4j
public class Jdbc_WordTranslateRepository implements WordTranslateRepository {
    /**
     * Spring JDBC support is rooted in the JdbcTemplate class.
     */
    private JdbcTemplate jdbc;

    private  SimpleJdbcInsert wordTranslateInsert;

    @Autowired
    public Jdbc_WordTranslateRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;

        /** But rather than use the cumbersome Prepared-
         StatementCreator, allow me to introduce you to SimpleJdbcInsert, an object that
         wraps JdbcTemplate to make it easier to insert data into a table. */
        wordTranslateInsert = (new SimpleJdbcInsert(jdbc)).withTableName("WordTranslate").
                usingGeneratedKeyColumns(new String[]{"id"});
    }


    private WordTranslate mapRowToWord(ResultSet rs, int rowNum) throws SQLException {
        WordTranslate returned_word = new WordTranslate();
        returned_word.setId(Long.valueOf(rs.getString("id")));
        returned_word.setUkrword(rs.getString("ukrword"));
        returned_word.setEngword(rs.getString("engword"));
        returned_word.setPoints(Integer.valueOf(rs.getString("points")));
        return returned_word;
    }

    @Override
    public Iterable<WordTranslate> findAll() {
        return jdbc.query("select id, engword, ukrword, points from WordTranslate", this::mapRowToWord);
    }

    @Override
    public WordTranslate findById(Long id) {
        return jdbc.queryForObject("select id, engword, ukrword, points from WordTranslate where id=?", this::mapRowToWord, id);
    }


    @Override
    public Long save(WordTranslate word) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(word, Map.class);

        long id = (long) wordTranslateInsert.executeAndReturnKey(map);

        return new Long(id);
    }
}