package uk.gemwire.scram.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProxy implements AuthFacade {

    @Override
    public Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
