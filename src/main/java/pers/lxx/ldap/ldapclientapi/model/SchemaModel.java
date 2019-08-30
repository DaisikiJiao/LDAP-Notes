package pers.lxx.ldap.ldapclientapi.model;

import java.util.Map;

public class SchemaModel extends BaseModel {

    private String catalogId;
    private String userName;
    private String password;
    private Map<String, CollectionModel> collectionMap;

    public Map<String, CollectionModel> getCollectionMap() {
        return collectionMap;
    }

    public void setCollectionMap(Map<String, CollectionModel> collectionMap) {
        this.collectionMap = collectionMap;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
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
}