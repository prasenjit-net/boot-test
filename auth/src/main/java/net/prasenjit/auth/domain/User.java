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

package net.prasenjit.auth.domain;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by PRASEN on 4/2/2016.
 */

@Data
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @Column(name = "USERNAME", length = 100, nullable = false)
    private String username;
    @Column(name = "NAME", length = 255, nullable = false)
    private String name;
    @Column(name = "PASSWORD", length = 100, nullable = false)
    private String password;
    @Column(name = "EXPIRED", nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    private boolean accountNonExpired;
    @Column(name = "LOCKED", nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    private boolean accountNonLocked;
    @Column(name = "ENABLED", nullable = false)
    @Convert(converter = BooleanStringConverter.class)
    private boolean enabled;
    @Column(name = "PASSWORD_CHANGE_DATE", nullable = true)
    private Date passwordChangeDate;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "SCOPES", joinColumns = @JoinColumn(name = "USERNAME"))
    private Set<Scope> authorities;
    @OneToMany(mappedBy = "user")
    private List<Client> clients;

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
