package com.jaeyeonling.lombok;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LombokMember {

    private String name;
    private int age;

    public boolean isSameAge(final LombokMember other) {
        return age == other.age;
    }
}
