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

    private List<WordTranslate> dictionary;

    public List<WordTranslate> getDictionary() {
        if (Objects.nonNull(dictionary)) return dictionary;
        return Collections.emptyList();
    }

    public void setDictionary(List<WordTranslate> dictionary) {
        this.dictionary = dictionary;
    }
}
