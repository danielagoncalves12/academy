package com.ctw.workstation.team.entity;

public class TeamDTO {

    private Long id;
    private String name;
    private String product;
    private String defaultLocation;

    public void setId(Long id) { this.id = id; }

    public Long getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    @Override
    public String toString() {

        StringBuilder name = new StringBuilder();

        name.append("TeamDTO - " + "\n");
        name.append("Name: " + getName() + " \n");
        name.append("Product: " + getProduct() + "\n");
        name.append("Default location: " + getDefaultLocation());

        return name.toString();
    }
}
