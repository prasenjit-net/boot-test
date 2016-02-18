/*
 * Copyright (c) 2016.
 * Prasenjit Purohit
 * prasenjit.net
 */

package net.prasenjit.micro.domain;

import lombok.Data;

/**
 * Created by pp03582 on 2/18/2016.
 */
@Data
public class AutoCloseA implements AutoCloseable {

    private final String name;

    @Override
    public void close() throws Exception {
        System.out.println(name);
    }
}
