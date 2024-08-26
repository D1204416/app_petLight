package com.example.petlight;

public class Pet {
    private String petName;
    private String petBreed;
    private String imgName;
    private String location;
    private String phone;

    public Pet(String petName, String petBreed, String imgName, String location, String phone) {
        this.petName = petName;
        this.petBreed = petBreed;
        this.imgName = imgName;
        this.location = location;
        this.phone = phone;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
