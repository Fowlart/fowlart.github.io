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

import java.util.List;

@Slf4j
public class UserService {

    private List<User> list;

    private UserRepository userRepository;
    private WordTranslateRepository wordTranslateRepository;
    private JdbcTemplate jdbc;

    @Autowired
    public UserService(UserRepository userRepository, WordTranslateRepository wordTranslateRepository, JdbcTemplate jdbcTemplate) {
        this.wordTranslateRepository = wordTranslateRepository;
        this.userRepository = userRepository;
        this.jdbc = jdbcTemplate;

        // this is testing logic, must be removed later-----------------------------------------------------------------
        // reading info from csv file and saving data base
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
        List<WordTranslate> wordTranslates = Lists.newArrayList(wordTranslateRepository.findAll());
        userRepository.save(admin);
        userRepository.save(vasya);

        list = Lists.newArrayList(userRepository.findAll());
        //set list of words for each user from DB
        List<WordTranslate> words_list1 = Lists.newArrayList(wordTranslateRepository.findAll());
        List<WordTranslate> word_list2 = words_list1.subList(10, 20);

        updateWordsList(admin, words_list1);
        updateWordsList(vasya, word_list2);
        //end of test data forming logic--------------------------------------------------------------------------------
    }

    private void refresh() {
        for (User user : list) user.setList(this.userRepository.getWords(user));
    }

    public boolean addWord(User user, WordTranslate new_word) {
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


    public boolean updateWordsList(User user, List<WordTranslate> new_words_list) {

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


    //return refreshed POJO
    public List<User> getList() {
        refresh();
        return list;
    }
}
