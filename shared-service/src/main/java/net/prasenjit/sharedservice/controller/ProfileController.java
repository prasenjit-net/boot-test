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

package net.prasenjit.sharedservice.controller;

import net.prasenjit.sharedservice.domain.ItemData;
import net.prasenjit.sharedservice.repository.ProfileItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pp03582 on 2/19/2016.
 */
@RestController
@RequestMapping(value = "profile", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    @Autowired
    private ProfileItemRepository itemRepository;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Map<String, ItemData> listAll(@PathVariable(value = "id") String id) {
        Map<String, ItemData> result = new HashMap<>();
        itemRepository.findAll().stream().forEach(pi -> {
            result.put(pi.getProfileId() + "$$" + pi.getItemKey(), pi.getItemValue());
        });
        return result;
    }
}
