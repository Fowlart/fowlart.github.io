package entities;

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

    private String id;

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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        if (isDictionaryDownloaded()) return "dictionary[" +id +"]";
        else return "[not downloaded]";
    }
}
