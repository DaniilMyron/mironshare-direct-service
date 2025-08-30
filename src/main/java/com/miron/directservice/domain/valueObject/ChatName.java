package com.miron.directservice.domain.valueObject;

public class ChatName implements ValueObject<String> {
    private String name;

    public ChatName(String name) {
        if(!name.isBlank() && !name.isEmpty())
            this.name = name;
        else
            throw new NullPointerException("name is blank or empty");
    }

    @Override
    public String getValue() {
        return name;
    }
}
