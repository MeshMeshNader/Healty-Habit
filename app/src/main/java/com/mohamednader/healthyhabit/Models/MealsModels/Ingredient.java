package com.mohamednader.healthyhabit.Models.MealsModels;

public class Ingredient {

    String title;
    String subtitle;
    String photo;

    public Ingredient(String title, String subtitle, String photo) {
        this.title = title;
        this.subtitle = subtitle;
        this.photo = photo;
    }

    public Ingredient() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
