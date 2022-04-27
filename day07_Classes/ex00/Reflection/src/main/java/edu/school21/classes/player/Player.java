package edu.school21.classes.player;

public class Player {
    private String login;
    private String status;
    private Integer level;
    private Boolean darkMode;

    public Player() {
        this.login = "default_player";
        this.status = "новобранец";
        this.level = 0;
        this.darkMode = false;
    }

    public Player(String login, String status, Integer level, Boolean darkModeOn) {
        this.login = login;
        this.status = status;
        this.level = level;
        this.darkMode = darkModeOn;
    }

    public int levelUp() {
        return ++this.level;
    }

    public void darkModeOn() {
        this.darkMode = true;
    }

    public void darkModeOff() {
        this.darkMode = false;
    }

    @Override
    public String toString() {
        return "Player {" +
                "login='" + login + '\'' +
                ", status='" + status + '\'' +
                ", level=" + level +
                ", darkMode=" + darkMode +
                '}';
    }
}
