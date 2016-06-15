package io.androidblog.simpletodo.models;

public class Item {

    String item;
    boolean completed;

    public Item(){ }

    public Item(String item) {
        this.item = item;
        this.completed = false;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
