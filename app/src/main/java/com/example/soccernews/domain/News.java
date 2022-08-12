package com.example.soccernews.domain;

public class News {
    private String title;

    public String getTitle() {
        return title;
    }

    public News(String title, String dscription) {
        this.title = title;
        this.dscription = dscription;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDscription() {
        return dscription;
    }

    public void setDscription(String dscription) {
        this.dscription = dscription;
    }

    private String dscription;
}
