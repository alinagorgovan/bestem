package licenta.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "email", ignoreUnknownFields = true)
public class Properties {

    private String email;
    private String description;
    private String userGroup;
    private String subject;
    private String fromEmailAddress;
    private String assignGroupURL;
    private String redirectURL;

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getUserGroup() { return userGroup; }

    public void setUserGroup(String userGroup) { this.userGroup = userGroup; }

    public String getSubject() { return subject; }

    public void setSubject(String subject) { this.subject = subject; }

    public String getFromEmailAddress() { return fromEmailAddress; }

    public void setFromEmailAddress(String fromEmailAddress) {
        this.fromEmailAddress = fromEmailAddress;
    }

    public String getAssignGroupURL() { return assignGroupURL; }

    public void setAssignGroupURL(String assignGroupURL) { this.assignGroupURL = assignGroupURL; }

    public String getRedirectURL() { return redirectURL; }

    public void setRedirectURL(String redirectURL) { this.redirectURL = redirectURL; }
}