package uk.gemwire.scram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import uk.gemwire.scram.user.UserData;
import uk.gemwire.scram.user.UserRepository;
import uk.gemwire.scram.util.AuthFacade;

/**
 * Contains things that are common to all pages.
 */
@Controller
public class RootController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthFacade authFacade;

    @ModelAttribute("currentUser")
    public UserData getUser() {
        Object principal = authFacade.getAuth().getPrincipal();

        if (principal instanceof String str)
            return userRepository.findByName(str);
        else if (principal instanceof User usr)
            return userRepository.findByName(usr.getUsername());
        else return null;
    }
}
