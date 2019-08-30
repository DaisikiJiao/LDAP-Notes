package pers.lxx.ldap.ldapclientapi.thread;


import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.ldap.client.api.search.FilterBuilder;
import org.apache.directory.ldap.client.template.EntryMapper;
import org.apache.directory.ldap.client.template.LdapConnectionTemplate;
import pers.lxx.ldap.ldapclientapi.entity.ServiceResource;

import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class SearchThread implements Runnable {

    private LdapConnectionTemplate ldapConnectionTemplate;
    private CyclicBarrier cyclicBarrier;

    public SearchThread(LdapConnectionTemplate ldapConnectionTemplate) {
        this.ldapConnectionTemplate = ldapConnectionTemplate;
    }

    public SearchThread(LdapConnectionTemplate ldapConnectionTemplate,CyclicBarrier cyclicBarrier) {
        this.ldapConnectionTemplate = ldapConnectionTemplate;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        EntryMapper<ServiceResource> entryMapper = new EntryMapper<ServiceResource>() {
            @Override
            public ServiceResource map(Entry entry) throws LdapException {
                return new ServiceResource.Builder()
                        .setDn(entry.getDn().toString())
                        .setServiceId(entry.get("serviceId").getString())
                        .setServiceCode(entry.get("serviceCode").getString())
                        .setServiceName(entry.get("serviceName").getString())
                        .setServiceNumber(entry.get("serviceNumber").getString())
                        .setServiceNature(entry.get("serviceNature").getString())
                        .setServiceShortName(entry.get("serviceShortName").getString())
                        .setServiceType(entry.get("serviceType").getString())
                        .setServiceUrl(entry.get("serviceUrl").getString())
                        .setStandardVersion(entry.get("standardVersion").getString())
                        .setBusCode(entry.get("busCode").getString())
                        .setNodeId(entry.get("nodeId").getString())
                        .setTechFrame(entry.get("techFrame").getString())
                        .setResourceType(entry.get("resourceType").getString())
                        .setResourceDetailType(entry.get("resourceDetailType").getString())
                        .setProtocolType(entry.get("protocolType").getString())
                        .setWorkStatus(entry.get("workStatus").getString())
                        .setAdminStop(entry.get("isAdminStop").getString())
                        .setDel(entry.get("isDel").getString())
                        .build();
            }
        };
        String filter = FilterBuilder.equal("serviceName", "cxf_rpc查询查询服务308").toString();
        try {
            cyclicBarrier.await();
            System.out.println("****************************************************************");
            long startTime = System.currentTimeMillis();
            System.out.println("Thread:" + Thread.currentThread().getName() +"开始执行："+startTime);
            List<ServiceResource> result = ldapConnectionTemplate.search("ou=serviceResource,dc=dragonsoft,dc=com", filter, SearchScope.SUBTREE, entryMapper);
            long endTime = System.currentTimeMillis();
            System.out.println("Thread:" + Thread.currentThread().getName() +"执行完成，耗时："+(endTime-startTime)+ " ms");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
