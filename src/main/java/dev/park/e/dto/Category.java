package dev.park.e.dto;

public class Category {
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (this.id == null) this.name = "미분류";
        else this.name = name;
    }
}
