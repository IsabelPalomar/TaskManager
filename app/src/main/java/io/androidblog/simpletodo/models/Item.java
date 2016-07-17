package io.androidblog.simpletodo.models;

import java.util.HashMap;

public class Item {

    String item;
    String description;
    String categoryId;
    int priority;
    boolean completed;
    HashMap<String, Object> dueDate;
    HashMap<String, Object> reminder;

    public Item(){ }

    public Item(String item, String description, String categoryId, int priority) {
        this.item = item;
        this.description = description;
        this.categoryId = categoryId;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String category) {
        this.categoryId = categoryId;
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
