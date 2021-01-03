package entities;

import com.google.inject.internal.util.Lists;
import data_base.mongo.WordMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionDictionary {

    private String userEmail;

    private List<Word> dictionary;

    @Autowired
    private WordMongoRepository wordMongoRepository;

    public List<Word> getDictionary() {
        if (Objects.nonNull(dictionary)) return dictionary;
        return Collections.emptyList();
    }

    public void createTestWord(String eMail){
        Word testWord = new Word();
        testWord.setPoints(0);
        testWord.setUsers(Lists.newArrayList(eMail));
        testWord.setEngword("test translation");
        testWord.setUkrword("тестовий переклад");
        setDictionary(Lists.newArrayList(testWord));
        wordMongoRepository.save(testWord);

    }

    public void setDictionary(List<Word> dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isDictionaryDownloaded() {
        return Objects.nonNull(dictionary);
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public String toString() {
        return "dictionary[" + userEmail + "]";
    }
}
