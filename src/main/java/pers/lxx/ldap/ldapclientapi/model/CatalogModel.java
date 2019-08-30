package pers.lxx.ldap.ldapclientapi.model;

import java.util.Map;

public class CatalogModel extends BaseModel {

    private String userName;
    private String password;
    private String parameter;
    private String isCluster;
    private String isReplica;
    private int port;
    private Map<String, SchemaModel> schemaMap;

    public Map<String, SchemaModel> getSchemaMap() {
        return schemaMap;
    }

    public void setSchemaMap(Map<String, SchemaModel> schemaMap) {
        this.schemaMap = schemaMap;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getIsCluster() {
        return isCluster;
    }

    public void setIsCluster(String isCluster) {
        this.isCluster = isCluster;
    }

    public String getIsReplica() {
        return isReplica;
    }

    public void setIsReplica(String isReplica) {
        this.isReplica = isReplica;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
