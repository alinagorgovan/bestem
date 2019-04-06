package licenta.auth;

import licenta.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private RestTemplate localRestTemplate;

    @Autowired
    public void setLocalRestTemplate(RestTemplate localRestTemplate) {
        this.localRestTemplate = localRestTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        URI uri = UriComponentsBuilder.fromUriString(Constants.DBA_CONNECTOR_PATH)
                .path(Constants.GET_USER_BY_USERNAME).pathSegment(username).build().toUri();
        UserDTO userDTO = Optional.ofNullable(localRestTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()), UserDTO.class).getBody()).orElseThrow(() ->
                new RuntimeException("UserDTO not found!"));
        return UserPrincipal.create(userDTO);
    }
}