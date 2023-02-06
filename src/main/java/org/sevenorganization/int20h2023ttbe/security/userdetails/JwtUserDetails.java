package org.sevenorganization.int20h2023ttbe.security.userdetails;

import org.sevenorganization.int20h2023ttbe.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class JwtUserDetails extends org.springframework.security.core.userdetails.User {

    public JwtUserDetails(String username,
                          String password,
                          boolean enabled,
                          boolean accountNonExpired,
                          boolean credentialsNonExpired,
                          boolean accountNonLocked,
                          Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public static JwtUserDetails fromUser(User user) {
        return new JwtUserDetails(
                user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                Collections.singleton(user.getRole())
        );
    }
}
