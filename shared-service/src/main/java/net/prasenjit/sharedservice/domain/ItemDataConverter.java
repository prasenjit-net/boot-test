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

package net.prasenjit.sharedservice.domain;

import org.springframework.util.Assert;

import javax.persistence.AttributeConverter;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

/**
 * Created by pp03582 on 2/22/2016.
 */
public class ItemDataConverter implements AttributeConverter<ItemData, String> {
    @Override
    public String convertToDatabaseColumn(ItemData attribute) {
        Assert.notNull(attribute.getDataType(), "data type must not be null");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(stream);
        writer.println(attribute.getDataType().name());
        switch (attribute.getDataType()) {
            case STRING:
                writer.print(attribute.getData());
                break;
        }
        return null;
    }

    @Override
    public ItemData convertToEntityAttribute(String dbData) {
        return null;
    }
}
