package licenta.auth;

import licenta.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {
    private Long id;

    @JsonProperty("username")
    @NotNull
    private String username;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("locale")
    @NotNull
    private String locale;

    @JsonProperty("description")
    @NotNull
    private String description;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String username, String password, String locale,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.locale = locale;
        this.description = description;
        this.authorities = authorities;
    }

    public static UserPrincipal create(UserDTO userDTO) {
        List<GrantedAuthority> authorities = userDTO.getRoles().stream()
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new UserPrincipal(userDTO.getId(), userDTO.getUserName(), userDTO.getPassword(),
                userDTO.getLocale(), authorities);
    }

    public Long getId() { return id; }

    @Override
    public String getUsername() { return username; }

    @Override
    public String getPassword() { return password; }

    public String getLocale() { return locale; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}