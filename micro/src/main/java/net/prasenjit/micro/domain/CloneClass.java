/*
 * Copyright (c) 2016.
 * Prasenjit Purohit
 * prasenjit.net
 */

package net.prasenjit.micro.domain;

/**
 * Created by pp03582 on 2/17/2016.
 */
public class CloneClass implements Cloneable {
    private String name;

    public CloneClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CloneClass{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public CloneClass clone() {
        try {
            return (CloneClass) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("clone failed", e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
