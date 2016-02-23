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

package net.prasenjit.sharedservice;

import net.prasenjit.sharedservice.domain.DataType;
import net.prasenjit.sharedservice.domain.ItemData;
import net.prasenjit.sharedservice.domain.ProfileItem;
import net.prasenjit.sharedservice.repository.ProfileItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * Created by pp03582 on 2/23/2016.
 */
@Component
public class DataMaker implements CommandLineRunner {

    @Autowired
    private ProfileItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        ProfileItem pi = new ProfileItem();
        pi.setProfileId("profile1");
        pi.setItemKey("key1");
        pi.setItemValue(ItemData.builder().dataType(DataType.STRING).data("value1").build());
        itemRepository.saveAndFlush(pi);

        pi = new ProfileItem();
        pi.setProfileId("profile1");
        pi.setItemKey("key2");
        pi.setItemValue(ItemData.builder().dataType(DataType.STRING).data("value2").build());
        itemRepository.saveAndFlush(pi);

        pi = new ProfileItem();
        pi.setProfileId("profile1");
        pi.setItemKey("key-list");
        pi.setItemValue(ItemData.builder().dataType(DataType.LIST).data(Collections.singletonList("value1")).build());
        itemRepository.saveAndFlush(pi);

        pi = new ProfileItem();
        pi.setProfileId("profile1");
        pi.setItemKey("key-map");
        pi.setItemValue(ItemData.builder().dataType(DataType.HASH).data(Collections.singletonMap("key1", "value1")).build());
        itemRepository.saveAndFlush(pi);
    }
}
