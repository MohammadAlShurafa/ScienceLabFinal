package com.science.app.sciencelab;

public class Item {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(int departmentNum) {
        this.departmentNum = departmentNum;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    int id;
    String name;
    String details;
    int departmentNum;
    int imageId;

    public Item(int id, String name, String details, int departmentNum, int imageId) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.departmentNum = departmentNum;
        this.imageId = imageId;
    }
}
