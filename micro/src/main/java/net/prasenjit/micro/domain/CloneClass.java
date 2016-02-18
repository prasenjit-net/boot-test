/*
 * Copyright (c) 2016 Prasenjit Purohit
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
