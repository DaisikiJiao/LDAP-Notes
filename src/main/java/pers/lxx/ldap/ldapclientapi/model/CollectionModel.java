package pers.lxx.ldap.ldapclientapi.model;

import java.util.List;
import java.util.Map;

public class CollectionModel extends BaseModel{

    private int rowCount;
    private int lastUpdate;
    private String lastUpdateTime;
    private String dbType;
    private String globalCode;
    private String cacheLevel;
    private String schemaId;
    private Map<String, FieldModel> fieldMap;
    private UniqueKeyModel uniqueKey;
    private List<IndexKeyModel> indexKeys;

    public List<IndexKeyModel> getIndexKeys() {
        return indexKeys;
    }

    public void setIndexKeys(List<IndexKeyModel> indexKeys) {
        this.indexKeys = indexKeys;
    }

    public UniqueKeyModel getUniqueKey() {
        return uniqueKey;
    }

    public void setUniqueKey(UniqueKeyModel uniqueKey) {
        this.uniqueKey = uniqueKey;
    }

    public Map<String, FieldModel> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, FieldModel> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getGlobalCode() {
        return globalCode;
    }

    public void setGlobalCode(String globalCode) {
        this.globalCode = globalCode;
    }

    public String getCacheLevel() {
        return cacheLevel;
    }

    public void setCacheLevel(String cacheLevel) {
        this.cacheLevel = cacheLevel;
    }

    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(String schemaId) {
        this.schemaId = schemaId;
    }
}
