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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Created by pp03582 on 4/4/2016.
 */
@Data
@Entity
@Table(name = "CLIENTS")
public class Client implements ClientDetails{
    @Id
    @Column(name = "CLIENT_ID", length = 100, nullable = false)
    private String clientId;
    @Column(name = "CLIENT_SECRET", length = 100, nullable = true)
    private String clientSecret;
    @ManyToOne
    @JoinColumn(name = "USERNAME")
    private User user;
    @Convert(converter = BooleanStringConverter.class)
    @Column(name = "SECRET_REQUIRED", nullable = false)
    private boolean secretRequired;
    @Column(name = "GRANT_TYPES", length = 100, nullable = false)
    private String grantTypes;
    @Column(name = "REDIRECT_URI", length = 2000, nullable = false)
    private String redirectUri;
    @Column(name = "ACCESS_VALIDITY_SECOND", nullable = true)
    private Integer accessTokenValiditySeconds;
    @Column(name = "REFRESH_VALIDITY_SECOND", nullable = true)
    private Integer refreshTokenValiditySeconds;

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return StringUtils.commaDelimitedListToSet(grantTypes);
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return StringUtils.commaDelimitedListToSet(redirectUri);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAutoApprove(String s) {
        return false;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
