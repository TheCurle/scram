package uk.gemwire.scram.util;

import org.springframework.security.core.Authentication;

/**
 * Can be used in a Bean to grant access to authentication information anywhere.
 */
public interface AuthFacade {
    Authentication getAuth();
}
