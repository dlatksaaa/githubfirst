package com.professionalandroid.apps.githubfirst;

public class Music {
    String name;
    String category;

    public Music(String n, String c){
        name = n;
        category = c;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

}
