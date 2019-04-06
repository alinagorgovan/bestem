package licenta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserDTO {

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

    @JsonProperty("roles")
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String locale, List<String> roles) {
        this.username = username;
        this.password = password;
        this.locale = locale;
        this.roles = roles;
    }

    public String getUserName() { return username; }

    public void setUserName(String userName) { this.username = userName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getLocale() { return locale; }

    public void setLocale(String local) { this.locale = local; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public List<String> getRoles() { return roles; }

    public void setRoles(List<String> roles) { this.roles = roles; }
}

