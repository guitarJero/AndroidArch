package com.sagar.android_projects.androidarch.pojo;


public class Data {
    private String result;

    public static final String NOTHING_TO_SHOW = "Nothing to show.";

    public Data() {
    }

    public Data(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
