package net.prasenjit.auth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

/**
 * Created by PRASEN on 4/2/2016.
 */
@Data
@Embeddable
public class Scope implements GrantedAuthority {
    @Column(name = "AUTHORITY", length = 50, nullable = false)
    private String authority;
}
