package licenta.auth;

import licenta.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    private final RestTemplate localRestTemplate = new RestTemplate();

    @PostMapping("/loginUser")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse("Bearer " + jwt));
    }


    @GetMapping("/registerUser")
    public String register() {
        return "redirect:/loggedIn/home";
//        User userInDb ;
//        try {
//            userInDb = localRestTemplate.exchange(
//                    getUriSendToDatabase(Constants.GET_USER_BY_USERNAME,
//                            Collections.singletonList(user.getUsername()), Collections.emptyMap
//                            ()),
//                    HttpMethod.GET, new HttpEntity<>(new HttpHeaders()), User.class).getBody();
//        } catch (RestClientException e) {
//            throw new RuntimeException("UserDTO already exists!");
//        }
//
//        if (userInDb != null)
//            throw new RuntimeException("UserDTO already exists!");
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        localRestTemplate.exchange(getUriSendToDatabase(Constants.REGISTER_USER,
//                Collections.emptyList(), Collections.emptyMap()),
//                HttpMethod.POST, new HttpEntity<>(user, new HttpHeaders()), String.class)
//                .getBody();
//
//        return ResponseEntity.ok().build();
    }

    private URI getUriSendToDatabase(String destination, List<String> pathSegments, Map<String,
            String> querySegments) {
        UriComponentsBuilder uri =
                UriComponentsBuilder.fromUriString(Constants.DBA_CONNECTOR_PATH).path(destination);
        pathSegments.forEach(uri::pathSegment);
        querySegments.forEach(uri::queryParam);
        return uri.build().toUri();
    }
}
