package licenta.config;

import licenta.entity.Role;
import licenta.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DatabaseInitializer {
    private final RoleRepository roleRepository;
    private final DbaProperties dbaProperties;

    @Autowired
    public DatabaseInitializer(RoleRepository roleRepository, DbaProperties dbaProperties) {
        this.roleRepository = roleRepository;
        this.dbaProperties = dbaProperties;
    }

    @PostConstruct
    public void init() {
        insertRoles();
    }

    private void insertRoles() {
        dbaProperties.getRoles().forEach(role -> roleRepository.findByRole(role).orElseGet(() ->
                roleRepository.save(new Role(role))));
    }
}