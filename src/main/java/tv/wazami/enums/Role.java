package tv.wazami.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    NORMAL,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}