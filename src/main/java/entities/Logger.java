package entities;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Logger {

    private List<String> fullLog;

    private SimpleDateFormat simpleDateFormat;

    public Logger() {
        this.fullLog = new LinkedList<>();
        this.simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    }

    private String getError(String message) {
        return "<p class='pError'>" + simpleDateFormat.format(new Date()) + ": " + message + "</p>";
    }

    private String getInfo(String message) {
        return "<p class='pInfo'>" + simpleDateFormat.format(new Date()) + ": " + message + "</p>";
    }

    private String getWarning(String message) {
        return "<p class='pWarn'>" + simpleDateFormat.format(new Date()) + ": " + message + "</p>";
    }

    public void writeError(String message) {
        fullLog.add(getError(message));
    }

    public void writeInfo(String message) {
        fullLog.add(getInfo(message));
    }

    public void writeWarning(String message) {
        fullLog.add(getWarning(message));
    }

    public List<String> getFullLog() {
        return fullLog;
    }
}

