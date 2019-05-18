package services;

import com.google.inject.internal.util.Lists;
import data_base.UserRepository;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import io_data_module.CsvWordsReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class UserService {

    private List<User> list;
    private UserRepository userRepository;
    private WordTranslateRepository wordTranslateRepository;
    private JdbcTemplate jdbc;
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return "UserService id = " + id;
    }

    @Autowired
    public UserService(UserRepository userRepository, WordTranslateRepository wordTranslateRepository, JdbcTemplate jdbcTemplate) {
        this.wordTranslateRepository = wordTranslateRepository;
        this.userRepository = userRepository;
        this.jdbc = jdbcTemplate;

        // This is testing logic, must be removed later
        // Reading info from csv file and saving data base
        {
            CsvWordsReader csvWordsReader = new CsvWordsReader();
            csvWordsReader.getItemList("db.csv").stream().forEach(wordTranslateRepository::save);
            // adding new users for tests
            User admin = new User();
            admin.setName("Artur");
            admin.setId(1);
            admin.setPassword("admin");
            User vasya = new User();
            vasya.setName("Vasya");
            vasya.setId(2);
            vasya.setPassword("vasya");
            // saving users to DB
            userRepository.save(admin);
            userRepository.save(vasya);
            list = Lists.newArrayList(userRepository.findAll());
            //set list of words for each user from DB
            List<WordTranslate> dictionary_1 = Lists.newArrayList(wordTranslateRepository.findAll());
            List<WordTranslate> dictionary_2 = dictionary_1.subList(10, 20);
            updateDictionary(admin, dictionary_1);
            updateDictionary(vasya, dictionary_2);
        }
    }

    // retrieves words from the database for each user and put them into every User pojo
    private void refresh() {
        for (User user : list) user.setList(getDictionary(user));
    }

    public boolean addWordToUserDictionary(User user, WordTranslate new_word) {
        try {
            new_word = wordTranslateRepository.save(new_word);
            jdbc.update("insert into User_WordTranslate (user, wordtranslate) values (?,?)", user.getId(),
                    new_word.getId());
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return false;
        }
    }

    public boolean updateDictionary(User user, List<WordTranslate> new_words_list) {
        try {
            List<WordTranslate> all_words = Lists.newArrayList(wordTranslateRepository.findAll());

            if (!all_words.containsAll(new_words_list)) throw new Exception("New words must be saved at the database " +
                    "first and be correctly fulfilled by all mandatory attributes");

            for (WordTranslate wordTranslate : new_words_list)
                jdbc.update("insert into User_WordTranslate (user, wordtranslate) values (?,?)", user.getId(),
                        wordTranslate.getId());
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return false;
        }
    }

    private Long mapToWordId(ResultSet rs, int rowNum) throws SQLException {
        return rs.getLong("wordtranslate");
    }

    public List<WordTranslate> getDictionary(User user) {
        final Long user_id = user.getId();
        return jdbc.query("select * from  User_WordTranslate where user=?", this::mapToWordId, user_id).
                stream().map((word_id) -> wordTranslateRepository.findById(word_id)).collect(Collectors.toList());
    }

    //return list of refreshed User POJOs with actual dictionaries
    public List<User> getUsersList() {
        refresh();
        return list;
    }
}
