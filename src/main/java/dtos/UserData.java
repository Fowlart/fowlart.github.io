package dtos;

public class UserData {
    private String name;
    private String maxUserPoints;
    private String allUserPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxUserPoints() {
        return maxUserPoints;
    }

    public void setMaxUserPoints(String maxUserPoints) {
        this.maxUserPoints = maxUserPoints;
    }

    public String getAllUserPoints() {
        return allUserPoints;
    }

    public void setAllUserPoints(String allUserPoints) {
        this.allUserPoints = allUserPoints;
    }
}
