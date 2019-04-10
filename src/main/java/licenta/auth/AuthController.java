package licenta.auth;

import licenta.dto.UserDTO;
import licenta.entity.User;
import licenta.mail.EmailService;
import licenta.mail.Mail;
import licenta.mail.Properties;
import licenta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final EmailService emailService;
    private final UserService userService;
    private final Properties properties;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService,
                          PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider,
                          EmailService emailService, Properties properties) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userService = userService;
        this.emailService = emailService;
        this.properties = properties;
    }

    @PostMapping("/loginUser")
    public ResponseEntity authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("{ \"Bearer\" : \"" + tokenProvider.generateToken(authentication) + "\"}");
    }


    @PostMapping("/registerUser")
    public ResponseEntity register(@RequestBody UserDTO user) {
        if (userService.getUserFromDb(user.getUserName()) != null)
            return ResponseEntity.badRequest().build();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("User");
        userService.saveUser(user);

        return ResponseEntity.ok().build();
    }

    public void sendMail(List<String> admins, UserDTO user) {
        Objects.requireNonNull(admins).forEach(adminEmail -> {
            Mail mail = new Mail();
            mail.setFrom(properties.getFromEmailAddress());
            mail.setTo(adminEmail);
            mail.setSubject(properties.getSubject());

            Map<String, Object> model = new HashMap<>();
            model.put(properties.getEmail(), user.getUserName());
            model.put(properties.getUserGroup(), user.getRole());
            model.put(properties.getRedirectURL(), properties.getAssignGroupURL());

            mail.setModel(model);

            try {
                emailService.sendSimpleMessage(mail);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}
