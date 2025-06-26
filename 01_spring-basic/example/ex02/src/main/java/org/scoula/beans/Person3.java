package org.scoula.beans;


import org.springframework.stereotype.Component;

@Component
public class Person3 {
    private String name = "Ella";
    private final Parrot2 parrot;

    public Person3(Parrot2 parrot) {
        this.parrot = parrot;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Parrot2 getParrot() {
        return parrot;
    }
}
