package services;

import com.google.inject.internal.util.Lists;
import data_base.UserRepository;
import data_base.WordTranslateRepository;
import entities.User;
import entities.WordTranslate;
import io_data_module.CsvWordsReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class UserService {

    private List<User> list;
    private UserRepository userRepository;
    private WordTranslateRepository wordTranslateRepository;

    @Autowired
    public UserService(UserRepository userRepository, WordTranslateRepository wordTranslateRepository) {
        this.userRepository = userRepository;
        this.wordTranslateRepository = wordTranslateRepository;

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
        //end of test data forming logic--------------------------------------------------------------------------------
        refresh();
    }

    public void refresh() {
        //get list of all Users from DB
        list = Lists.newArrayList(userRepository.findAll());
        //set list of words for each user from DB
        log.info("size of user list " + list.size());
        log.info("users:"+list);
        for (User user: list) {
            log.info("count of words "+userRepository.getWords(user));
            user.setList(this.userRepository.getWords(user));
        }
    }

    //return refreshed POJO
    public List<User> getList() {
        return list;
    }
}
