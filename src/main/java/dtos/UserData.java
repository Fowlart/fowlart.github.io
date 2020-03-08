package dtos;

public class UserData {
    private String name;
    private int maxUserPoints;
    private int allUserPoints;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxUserPoints() {
        return maxUserPoints;
    }

    public void setMaxUserPoints(int maxUserPoints) {
        this.maxUserPoints = maxUserPoints;
    }

    public int getAllUserPoints() {
        return allUserPoints;
    }

    public void setAllUserPoints(int allUserPoints) {
        this.allUserPoints = allUserPoints;
    }
}
