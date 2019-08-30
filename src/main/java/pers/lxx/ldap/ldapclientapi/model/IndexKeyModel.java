package pers.lxx.ldap.ldapclientapi.model;

public class IndexKeyModel extends BaseModel {

    private String collectionId;
    private String implementWay;
    private String indexType;
    private IndexKeyFieldModel indexKeyField;

    public IndexKeyFieldModel getIndexKeyField() {
        return indexKeyField;
    }

    public void setIndexKeyField(IndexKeyFieldModel indexKeyField) {
        this.indexKeyField = indexKeyField;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getImplementWay() {
        return implementWay;
    }

    public void setImplementWay(String implementWay) {
        this.implementWay = implementWay;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }
}
