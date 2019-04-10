package licenta.config;


public class DbaConnectorProperties {
    private String connectorName;
    private String connectorStreamId;

    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    public String getConnectorStreamId() {
        return connectorStreamId;
    }

    public void setConnectorStreamId(String connectorStreamId) {
        this.connectorStreamId = connectorStreamId;
    }
}