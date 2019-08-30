package pers.lxx.ldap.ldapclientapi.model;

public class IndexKeyFieldModel extends BaseModel {

    private String ikId;
    private String fieldId;
    private String sortNo;

    public String getIkId() {
        return ikId;
    }

    public void setIkId(String ikId) {
        this.ikId = ikId;
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getSortNo() {
        return sortNo;
    }

    public void setSortNo(String sortNo) {
        this.sortNo = sortNo;
    }
}
