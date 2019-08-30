package pers.lxx.ldap.ldapclientapi.model;

import java.util.Map;

public class MachineModel extends BaseModel{

    private String ipAddress;
    private String macAddress;
    private String userName;
    private String password;
    private String cpuNum;
    private String cpuCoreNum;
    private String cpuCoreSpeed;
    private String memorySize;
    private String diskSize;
    private String machineType;
    private String machimeName;
    private Map<String,CatalogModel> catalogMap;

    public Map<String, CatalogModel> getCatalogMap() {
        return catalogMap;
    }

    public void setCatalogMap(Map<String, CatalogModel> catalogMap) {
        this.catalogMap = catalogMap;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
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

    public String getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(String cpuNum) {
        this.cpuNum = cpuNum;
    }

    public String getCpuCoreNum() {
        return cpuCoreNum;
    }

    public void setCpuCoreNum(String cpuCoreNum) {
        this.cpuCoreNum = cpuCoreNum;
    }

    public String getCpuCoreSpeed() {
        return cpuCoreSpeed;
    }

    public void setCpuCoreSpeed(String cpuCoreSpeed) {
        this.cpuCoreSpeed = cpuCoreSpeed;
    }

    public String getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(String memorySize) {
        this.memorySize = memorySize;
    }

    public String getDiskSize() {
        return diskSize;
    }

    public void setDiskSize(String diskSize) {
        this.diskSize = diskSize;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getMachimeName() {
        return machimeName;
    }

    public void setMachimeName(String machimeName) {
        this.machimeName = machimeName;
    }
}
