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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by pp03582 on 2/22/2016.
 */
public class ItemDataConverter implements AttributeConverter<ItemData, String> {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ItemData attribute) {
        StringBuilder sb = new StringBuilder();
        sb.append(attribute.getDataType().name()).append('|');
        try {
            sb.append(objectMapper.writeValueAsString(attribute.getData()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    @Override
    public ItemData convertToEntityAttribute(String dbData) {
        int separator = dbData.indexOf('|');
        String enumName = dbData.substring(0, separator);
        DataType dataType = DataType.valueOf(enumName);
        ItemData.ItemDataBuilder itemDataBuilder = ItemData.builder();
        itemDataBuilder.dataType(dataType);
        try {
            switch (dataType) {
                case STRING:
                    itemDataBuilder.data(dbData.substring(separator + 1));
                    break;
                case LIST:
                    itemDataBuilder.data(objectMapper.readValue(dbData.substring(separator + 1), List.class));
                    break;
                case HASH:
                    itemDataBuilder.data(objectMapper.readValue(dbData.substring(separator + 1), Map.class));
                    break;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return itemDataBuilder.build();
    }
}
