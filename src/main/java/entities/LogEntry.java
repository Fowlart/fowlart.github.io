package entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "log_entry")
public class LogEntry {

    private Date creationTime;

    private String json;

    private String eventType;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}
