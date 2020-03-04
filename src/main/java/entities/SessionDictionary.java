package entities;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionDictionary {

    private static int counter;
    private final int id = counter++;

    private List<WordTranslate> dictionary;

    public List<WordTranslate> getDictionary() {
        if (Objects.nonNull(dictionary)) return dictionary;
        return Collections.emptyList();
    }

    public void setDictionary(List<WordTranslate> dictionary) {
        this.dictionary = dictionary;
    }

    public boolean isDictionaryDownloaded() {
        return Objects.nonNull(dictionary);
    }

    @Override
    public String toString() {
        return "SessionDictionary[" +id +"]"+new Date().toString().replaceAll(" ","");
    }
}
