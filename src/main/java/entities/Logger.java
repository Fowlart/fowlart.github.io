package entities;

public class Logger {

    public String getError(String message) {
        return "<p class='pError'>" + message +".</p>";
    }

    public String getInfo(String message) {
        return "<p class='pInfo'>" + message +".</p>";
    }

    public String getWarning(String message) {
        return "<p class='pWarn'>" + message +".</p>";
    }
}
