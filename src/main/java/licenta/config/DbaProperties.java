package licenta.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "dba", ignoreUnknownFields = true)
public class DbaProperties {

    private List<DbaConnectorProperties> connectors = new ArrayList<>();

    private List<String> chartTypes;

    private List<String> roles;

    public List<DbaConnectorProperties> getConnectors() {
        return connectors;
    }

    public void setConnectors(List<DbaConnectorProperties> dbaConnectorProperties) {
        this.connectors = dbaConnectorProperties;
    }

    public List<String> getChartTypes() { return chartTypes; }

    public void setChartTypes(List<String> chartTypes) { this.chartTypes = chartTypes; }

    public List<String> getRoles() { return roles; }

    public void setRoles(List<String> roles) { this.roles = roles; }
}