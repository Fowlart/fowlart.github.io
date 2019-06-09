package data_base;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

@Slf4j
@Repository
public class Jdbc_UserRepository implements UserRepository {

    private JdbcTemplate jdbc;
    private WordTranslateRepository words_repo;

    @Autowired
    public Jdbc_UserRepository(JdbcTemplate jdbc, WordTranslateRepository words_repo) {
        this.jdbc = jdbc;
        this.words_repo = words_repo;
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        User returned_user = new User();
        returned_user.setId(Long.valueOf(rs.getString("id")));
        returned_user.setName(rs.getString("name"));
        returned_user.setPassword(rs.getString("password"));
        return returned_user;
    }

    @Override
    public Iterable<User> findAll() {
        return jdbc.query("select id, name, password from User", this::mapRowToUser);
    }

    @Override
    public User findById(long id) {
        return jdbc.queryForObject("select id, name, password from User where id=?", this::mapRowToUser, id);
    }


    @Override
    public Long save(User user) {
        SimpleJdbcInsert userInsert = (new SimpleJdbcInsert(jdbc)).withTableName("User").usingGeneratedKeyColumns(new String[]{"id"});
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(user, Map.class);
        long id = (long) userInsert.executeAndReturnKey(map);
        return id;
    }
}


