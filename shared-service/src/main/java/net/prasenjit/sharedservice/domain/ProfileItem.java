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

import lombok.Data;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

/**
 * Created by pp03582 on 2/22/2016.
 */
@Data
@Entity
@IdClass(ProfileItem.ProfileItemKey.class)
public class ProfileItem {
    @Id
    private String profileId;

    private String itemKey;

    @Convert(converter = ItemDataConverter.class)
    private ItemData itemValue;

    @Data
    public static class ProfileItemKey implements Serializable {
        private String profileId;
        private String itemKey;
    }
}
