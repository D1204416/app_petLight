package com.example.petlight;

import android.graphics.Bitmap;

public class PetInfo {
  private String name;
  private String breed;
  private String location;
  private String phone;
  private Bitmap image;

  public PetInfo(String name, String breed, String location, String phone, Bitmap image) {
    this.name = name;
    this.breed = breed;
    this.location = location;
    this.phone = phone;
    this.image = image;
  }

  // Getters
  public String getName() { return name; }
  public String getBreed() { return breed; }
  public String getLocation() { return location; }
  public String getPhone() { return phone; }
  public Bitmap getImage() { return image; }
}

