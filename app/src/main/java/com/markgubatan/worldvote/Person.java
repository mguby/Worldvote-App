package com.markgubatan.worldvote;

public class Person {
    private String name;
    private int score;
    private int drawableId = 0;
    private String email;
    private String password;
    private String encodedImage;


    public Person(String name, int score, int drawableId) {
        this.name = name;
        this.score = score;
        this.drawableId = drawableId;
    }
    public Person (String name, int score, String email, String password, String encodedImage) {
        drawableId = 0;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public int getDrawableId() {
        return drawableId;
    }


}