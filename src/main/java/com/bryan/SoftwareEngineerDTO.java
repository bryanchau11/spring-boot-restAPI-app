package com.bryan;
// params are the field that're exposed to the cusomter, in this case I omit techStack

public class SoftwareEngineerDTO {

    private Integer id;
    private String name;
    private String techStack;

    // Entity mapping constructor
    public SoftwareEngineerDTO(SoftwareEngineer entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.techStack = entity.getTechStack();
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getTechStack() { return techStack; }
}
