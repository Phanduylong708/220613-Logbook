package com.example.lab5contactdatabase;

public class Contact {
    private int id;
    private String name;
    private String dob;
    private String email;
    private int avatarResId;

    public Contact(int id, String name, String dob, String email, int avatarResId) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.avatarResId = avatarResId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public int getAvatarResId() {
        return avatarResId;
    }
}