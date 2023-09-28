package com.example.myapplication.entity;

public class OneLearn {
    private int id;
    private String word;
    private String translation;

    public OneLearn(){

    }

    public OneLearn(int id,String word, String translation){
        this.id = id;
        this.translation = translation;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String toString(){
        return "OneLearn [id=" + id + ", word=" + word + ", translation=" + translation + "]";
    }

}
