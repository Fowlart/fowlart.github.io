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
public class JdbcUserRepository implements UserRepository {

    private JdbcTemplate jdbc;


    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        User returnedUser = new User();
        returnedUser.setId(Long.valueOf(rs.getString("id")));
        returnedUser.setName(rs.getString("name"));
        returnedUser.setPassword(rs.getString("password"));
        return returnedUser;
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
        SimpleJdbcInsert userInsert = (new SimpleJdbcInsert(jdbc)).withTableName("User").usingGeneratedKeyColumns("id");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(user, Map.class);
        return (long) userInsert.executeAndReturnKey(map);

    }
}


