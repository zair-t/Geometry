package com.zair.geometry;

public class User {
    public String id, nameT, description, imageUri;

    public User() {

    }

    public User(String id, String nameT, String description, String imageUri) {
        this.imageUri = imageUri;
        this.id = id;
        this.nameT = nameT;
        this.description = description;
    }
}

