package uk.gemwire.scram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uk.gemwire.scram.user.UserData;
import uk.gemwire.scram.user.UserRepository;

import java.util.List;

@Controller
public class AdminPagesController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/user/list", "/admin/user"})
    public String listUser(Model model) {
        List<UserData> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);

        return "user-list";
    }

    @GetMapping("/user/add")
    public String addUser() {
        return "user-add";
    }

}
