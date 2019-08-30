package pers.lxx.ldap.ldapclientapi.entity;

public class ServiceResource {

    private String dn;
    private String serviceId;
    private String serviceCode;
    private String serviceName;
    private String serviceNumber;
    private String serviceNature;
    private String serviceShortName;
    private String serviceType;
    private String serviceUrl;
    private String standardVersion;
    private String busCode;
    private String nodeId;
    private String techFrame;
    private String resourceType;
    private String resourceDetailType;
    private String protocolType;
    private boolean workStatus;
    private boolean isAdminStop;
    private boolean isDel;

    public String getDn() {
        return dn;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public String getServiceNature() {
        return serviceNature;
    }

    public String getServiceShortName() {
        return serviceShortName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public String getStandardVersion() {
        return standardVersion;
    }

    public String getBusCode() {
        return busCode;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getTechFrame() {
        return techFrame;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getResourceDetailType() {
        return resourceDetailType;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public boolean isWorkStatus() {
        return workStatus;
    }

    public boolean isAdminStop() {
        return isAdminStop;
    }

    public boolean isDel() {
        return isDel;
    }

    public static class Builder {
        private String dn;
        private String serviceId;
        private String serviceCode;
        private String serviceName;
        private String serviceNumber;
        private String serviceNature;
        private String serviceShortName;
        private String serviceType;
        private String serviceUrl;
        private String standardVersion;
        private String busCode;
        private String nodeId;
        private String techFrame;
        private String resourceType;
        private String resourceDetailType;
        private String protocolType;
        private boolean workStatus;
        private boolean isAdminStop;
        private boolean isDel;

        public Builder setDn(String dn) {
            this.dn = dn;
            return this;
        }

        public Builder setServiceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public Builder setServiceCode(String serviceCode) {
            this.serviceCode = serviceCode;
            return this;
        }

        public Builder setServiceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public Builder setServiceNumber(String serviceNumber) {
            this.serviceNumber = serviceNumber;
            return this;
        }

        public Builder setServiceNature(String serviceNature) {
            this.serviceNature = serviceNature;
            return this;
        }

        public Builder setServiceShortName(String serviceShortName) {
            this.serviceShortName = serviceShortName;
            return this;
        }

        public Builder setServiceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        public Builder setServiceUrl(String serviceUrl) {
            this.serviceUrl = serviceUrl;
            return this;
        }

        public Builder setStandardVersion(String standardVersion) {
            this.standardVersion = standardVersion;
            return this;
        }

        public Builder setBusCode(String busCode) {
            this.busCode = busCode;
            return this;
        }

        public Builder setNodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public Builder setTechFrame(String techFrame) {
            this.techFrame = techFrame;
            return this;
        }

        public Builder setResourceType(String resourceType) {
            this.resourceType = resourceType;
            return this;
        }

        public Builder setResourceDetailType(String resourceDetailType) {
            this.resourceDetailType = resourceDetailType;
            return this;
        }

        public Builder setProtocolType(String protocolType) {
            this.protocolType = protocolType;
            return this;
        }

        public Builder setWorkStatus(String workStatus) {
            this.workStatus = Boolean.parseBoolean(workStatus);
            return this;
        }

        public Builder setAdminStop(String adminStop) {
            isAdminStop = Boolean.parseBoolean(adminStop);
            return this;
        }

        public Builder setDel(String del) {
            isDel = Boolean.parseBoolean(del);
            return this;
        }

        public ServiceResource build() {
            ServiceResource serviceResource = new ServiceResource();
            serviceResource.dn = this.dn;
            serviceResource.serviceId = this.serviceId;
            serviceResource.serviceCode = this.serviceCode;
            serviceResource.serviceName = this.serviceName;
            serviceResource.serviceNumber = this.serviceNumber;
            serviceResource.serviceNature = this.serviceNature;
            serviceResource.serviceShortName = this.serviceShortName;
            serviceResource.serviceType = this.serviceType;
            serviceResource.serviceUrl = this.serviceUrl;
            serviceResource.standardVersion = this.standardVersion;
            serviceResource.busCode = this.busCode;
            serviceResource.nodeId = this.nodeId;
            serviceResource.techFrame = this.techFrame;
            serviceResource.resourceType = this.resourceType;
            serviceResource.resourceDetailType = this.resourceDetailType;
            serviceResource.protocolType = this.protocolType;
            serviceResource.workStatus = this.workStatus;
            serviceResource.isAdminStop = this.isAdminStop;
            serviceResource.isDel = this.isDel;

            return serviceResource;
        }

    }

    @Override
    public String toString() {
        return "DN:"+this.dn;
    }
}
