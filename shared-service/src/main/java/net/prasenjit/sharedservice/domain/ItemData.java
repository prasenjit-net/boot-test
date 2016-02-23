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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by pp03582 on 2/19/2016.
 */
@Builder
@ToString
@EqualsAndHashCode
@JsonSerialize(using = ItemData.ItemDataSerializer.class)
public class ItemData {
    @Getter
    private DataType dataType;

    @Getter
    private Object data;

    public static class ItemDataSerializer extends JsonSerializer<ItemData> {

        @Override
        public void serialize(ItemData value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeStartObject();
            gen.writeStringField("dataType", value.getDataType().name());
            switch (value.getDataType()) {
                case STRING:
                    gen.writeFieldName("data");
                    gen.writeRawValue((String) value.getData());
                    break;
                case LIST:
                    List<?> listValue = (List<?>) value.getData();
                    gen.writeArrayFieldStart("data");
                    for (Object item : listValue) {
                        gen.writeString((String) item);
                    }
                    gen.writeEndArray();
                    break;
                case HASH:
                    Map<?, ?> mapValue = (Map<?, ?>) value.getData();
                    gen.writeFieldName("data");
                    gen.writeStartObject();
                    for (Map.Entry<?, ?> item : mapValue.entrySet()) {
                        gen.writeStringField((String) item.getKey(), (String) item.getValue());
                    }
                    gen.writeEndObject();
            }
            gen.writeEndObject();
        }
    }
}
