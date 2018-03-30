package ru.colibri.colibriserver.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.colibri.colibriserver.Security.CustomUserDetailsService;

import javax.annotation.PostConstruct;
import java.security.Principal;

@RestController
@RequestMapping("/secure")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Encryte Password with BCryptPasswordEncoder
    public static String encryptePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    @PostConstruct
    public void init() {

//        User user = new User("User", "user", encryptePassword("password"), true);
//        List<Role> roles1 = new ArrayList<>();
//        roles1.add(new Role("ROLE_USER"));
//        user.setRoles(roles1);
//        customUserDetailsService.saveUser(user);
//
//        User admin = new User("Admin", "admin", encryptePassword("password"), true);
//        List<Role> roles2 = new ArrayList<>();
//        roles2.add(new Role("ROLE_ADMIN"));
//        admin.setRoles(roles2);
//        customUserDetailsService.saveUser(admin);
//
//        User participant = new User("Participant", "participant", encryptePassword("password"), true);
//        List<Role> roles3 = new ArrayList<>();
//        roles3.add(new Role("ROLE_PARTICIPANT"));
//        participant.setRoles(roles3);
//        customUserDetailsService.saveUser(participant);
    }

    @GetMapping("/user")
    public @ResponseBody Principal getUser(Principal user) {
        return user;
    }


    @GetMapping("/admin")
    public @ResponseBody Principal getAdmin(Principal admin) {
        return admin;
    }

    @PreAuthorize("hasRole('PARTICIPANT')")
    @GetMapping("/participant")
    public @ResponseBody Principal getParticipant(Principal participant) {
        return participant;
    }
}