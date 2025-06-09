package org.scoula.beans;

import org.springframework.stereotype.Component;

@Component
public class Parrot2 {
    private String name = "Koko";
    public String getName() {
        return name;
    }
}