package com.example.roee_p.alpha;

public class Item {
    public String id;
    public String name;
    public int value;

    public Item(String id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
