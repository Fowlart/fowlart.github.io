package data_base;

import entities.User;
import entities.WordTranslate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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
    public User findById(User user) {
        long id = user.getId();
        return jdbc.queryForObject("select id, name, password from User where id=?", this::mapRowToUser, id);
    }


    @Override
    public User save(User user) {
        jdbc.update("insert into User (id, name, password) values (?,?,?)", user.getId(), user.getName(), user.getPassword());
        return user;
    }

    @Override
    public List<WordTranslate> getWords(User user) {
        final Long user_id = user.getId();
        //Todo: figure out how to fix that mess
        return jdbc.query("select user, wordtranslate from  User_WordTranslate", this::mapToEntry_User_Word).
                stream().
                filter(entry_user_word -> (entry_user_word.getUser_id() == user_id)).
                map(entry_user_word -> words_repo.findById(entry_user_word.word_id)).
                collect(Collectors.toList());
    }

    //Todo: figure out how to fix that mess
    private Entry_User_Word mapToEntry_User_Word(ResultSet rs, int rowNum) throws SQLException {
        Entry_User_Word rez = new Entry_User_Word();
        rez.setUser_id(Long.valueOf(rs.getString("user")));
        rez.setWord_id(Long.valueOf(rs.getString("wordtranslate")));
        return rez;
    }

    //Todo: figure out how to fix that mess
    class Entry_User_Word {
        private Entry_User_Word() {
        }

        private long user_id;

        private long word_id;

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public void setWord_id(long word_id) {
            this.word_id = word_id;
        }

        public long getUser_id() {
            return user_id;
        }

        public long getWord_id() {
            return word_id;
        }
    }

}


