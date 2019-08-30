package pers.lxx.ldap.ldapclientapi.model;

public class UniqueKeyModel {

    private String collectionId;
    private String isPrimaryKey;
    private UkFieldModel ukField;

    public UkFieldModel getUkField() {
        return ukField;
    }

    public void setUkField(UkFieldModel ukField) {
        this.ukField = ukField;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getIsPrimaryKey() {
        return isPrimaryKey;
    }

    public void setIsPrimaryKey(String isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }
}
