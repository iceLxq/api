package com.api.controller;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 李显琪 on 2020/9/19.
 */
public final class Name {
    public static final Set<String> nameSet = new HashSet<>();

    public Name() {
        nameSet.add("11");
    }

    public static final boolean containName(String name){
        return name.contains(name);
    }
}
