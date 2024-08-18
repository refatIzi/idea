package com.example.idea.helpinfo;

public class Example {
    private String keyword;

    private String description;

    private String usage;

    //keyword, description, usage
    public Example(String keyword, String description, String usage) {
        this.keyword = keyword;
        this.description = description;
        this.usage = usage;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

}
