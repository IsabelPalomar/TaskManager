package io.androidblog.simpletodo.models;

import com.google.firebase.database.Exclude;

public class Category {

    @Exclude
    String id;
    String name;
    String image;

    public Category(){}

    public Category(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
    public Category(String name, String image) {
        this.name = name;
        this.image = image;
    }

    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String toString() {
        return this.name;
    }

}
