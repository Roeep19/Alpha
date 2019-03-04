package com.example.roee_p.alpha;

public class Item {
    public String id;
    public String city;
    public int age;
    public String description;
    public String email;
    public String conditions;


    public Item(String id, String city, int age, String description, String email, String conditions) {
        this.id = id;
        this.city = city;
        this.age = age;
        this.description = description;
        this.email = email;
        this.conditions = conditions;
    }

    public Item() {
    }

    public String getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getConditions() {
        return conditions;
    }
}
