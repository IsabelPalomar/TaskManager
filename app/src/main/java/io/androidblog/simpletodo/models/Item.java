package io.androidblog.simpletodo.models;

import java.util.HashMap;

public class Item {

    String item;
    boolean completed;

    String description;
    String type;
    HashMap<String, Object> dueDate;
    HashMap<String, Object> reminder;


    public Item(){ }

    public Item(String item) {
        this.item = item;
        this.completed = false;
    }

    public Item(String item, String description) {
        this.item = item;
        this.description = description;
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


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public HashMap<String, Object> getDueDate() {
        return dueDate;
    }

    public void setDueDate(HashMap<String, Object> dueDate) {
        this.dueDate = dueDate;
    }

    public HashMap<String, Object> getReminder() {
        return reminder;
    }

    public void setReminder(HashMap<String, Object> reminder) {
        this.reminder = reminder;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String notes) {
        this.description = description;
    }


}
