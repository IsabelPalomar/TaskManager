package io.androidblog.simpletodo.models;

import java.util.HashMap;

public class Item {

    String item;
    String description;
    int category;
    int priority;
    boolean completed;
    HashMap<String, Object> dueDate;
    HashMap<String, Object> reminder;

    public Item(){ }

    public Item(String item, String description, int category, int priority) {
        this.item = item;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.completed = false;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
