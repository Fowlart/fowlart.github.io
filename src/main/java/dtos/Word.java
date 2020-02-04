package dtos;

import java.net.URL;

public class Word {
    private String ukrword;
    private String engword;
    private int points;
    private URL sound;

    public String getUkrword() {
        return ukrword;
    }

    public void setUkrword(String ukrword) {
        this.ukrword = ukrword;
    }

    public String getEngword() {
        return engword;
    }

    public void setEngword(String engword) {
        this.engword = engword;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public URL getSound() {
        return sound;
    }

    public void setSound(URL sound) {
        this.sound = sound;
    }
}
