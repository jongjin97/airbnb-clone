package com.example.airbnb.config.auth;

import com.example.airbnb.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


public class UserDetailsImpl implements UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 사용자의 권한을 설정합니다. 예시로 "ROLE_USER"를 부여합니다.
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }
    public Long getId() {
        return user.getId();
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되지 않았는지 여부를 반환합니다.
        return true; // 예시로 항상 true를 반환하도록 설정했습니다.
    }

    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠겨있지 않은지 여부를 반환합니다.
        return true; // 예시로 항상 true를 반환하도록 설정했습니다.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 자격 증명(비밀번호)이 만료되지 않았는지 여부를 반환합니다.
        return true; // 예시로 항상 true를 반환하도록 설정했습니다.
    }

    @Override
    public boolean isEnabled() {
        // 계정이 활성화되었는지 여부를 반환합니다.
        return true;  // 예시로 항상 true를 반환하도록 설정했습니다.
    }
}
