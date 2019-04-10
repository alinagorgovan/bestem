package licenta.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UserDTO {

    private Long id;

    @JsonProperty("username")
    @NotNull
    private String username;

    @JsonProperty("password")
    @NotNull
    private String password;

    @JsonProperty("role")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String role;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUserName() { return username; }

    public void setUserName(String userName) { this.username = userName; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}

