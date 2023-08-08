package com.api.dto;

public class CatalogDTO {
    private Long id;
    private String name;
    private String description;
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    @Override
    public String toString(){
        return "CatalogDTO: [id = " + this.id + ", name = " + this.name + ", description = " + this.description + "]";
    }
}
