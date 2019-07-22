package MVC_package;

import com.google.inject.internal.util.Lists;
import data_base.UserRepository;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import io_data_module.CsvWordsReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private WordTranslateRepository wordTranslateRepository;
    private JdbcTemplate jdbc;
    private static long counter = 0;
    private final long id = counter++;
    private boolean switcher = false;

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

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return testDataCreation();
    }


    private User testDataCreation() {
        CsvWordsReader csvWordsReader = new CsvWordsReader();
        csvWordsReader.getItemList("db.csv").stream().forEach(wordTranslateRepository::save);
        // adding new users for tests

        // saving users to DB

        User user = userRepository.findById(0);

        //set user_list of words for each user from DB
        List<WordTranslate> dictionary1 = Lists.newArrayList(wordTranslateRepository.findAll());
        if (!switcher) updateDictionary(user.getId(), dictionary1);
        switcher = true;

        return user;
    }

    public boolean addWordToUserDictionary(long userId, WordTranslate newWord) {
        try {
            long wordId = wordTranslateRepository.save(newWord);
            jdbc.update("insert into User_WordTranslate (user, wordtranslate) values (?,?)", userId,
                    wordId);
            return true;
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return false;
        }
    }

    public boolean updateDictionary(long userId, List<WordTranslate> newWordsList) {
        try {
            for (WordTranslate wordTranslate : newWordsList) {
                addWordToUserDictionary(userId, wordTranslate);
            }
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
                stream().map(wordId -> wordTranslateRepository.findById(wordId)).collect(Collectors.toList());
    }

    //return user_list of refreshed User POJOs with actual dictionaries
    public List<User> getUsersList() {
        return Lists.newArrayList(userRepository.findAll());
    }

    public void deleteWordFromUserDictionary(User user, WordTranslate wordTranslate) {
        final Long word_id = wordTranslate.getId();
        final Long user_id = user.getId();
        try {
            jdbc.update("DELETE FROM User_WordTranslate WHERE ( (user=?) AND (wordtranslate=?) )", user_id, word_id);
        } catch (Exception exeption) {
            log.error(">>> Some think went wrong during removing: ");
            log.error(exeption.getMessage());
        }
        try {
            jdbc.update("DELETE FROM WordTranslate WHERE id =?", word_id);
        } catch (Exception ex) {
            log.info(">>> The current word is used elsewhere. Removing from the common table was rejected.");
        }
    }

}
