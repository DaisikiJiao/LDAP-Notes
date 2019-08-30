package pers.lxx.ldap.ldapclientapi;

import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.exception.LdapInvalidDnException;
import org.apache.directory.api.ldap.model.message.*;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.LdapConnectionPool;
import org.apache.directory.ldap.client.api.search.FilterBuilder;
import org.apache.directory.ldap.client.template.EntryMapper;
import org.apache.directory.ldap.client.template.LdapConnectionTemplate;
import org.apache.directory.ldap.client.template.RequestBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.lxx.ldap.ldapclientapi.entity.ServiceResource;
import pers.lxx.ldap.ldapclientapi.loader.OrganizationLoader;
import pers.lxx.ldap.ldapclientapi.util.TimerUtil;

import java.util.List;
import java.util.concurrent.CountDownLatch;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ServiceResourceDirectoryTests {

    @Autowired
    private LdapConnectionPool ldapConnectionPool;
    @Autowired
    private LdapConnectionTemplate ldapConnectionTemplate;

    //数据键值对与对象属性映射
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

    @Test
    public void fun01_initDirectory() {
        //创建一个serviceResource目录
        ldapConnectionTemplate.add(
                ldapConnectionTemplate.newDn("ou=serviceResource,dc=dragonsoft,dc=com"),
                ldapConnectionTemplate.newAttribute("objectClass", "top", "organizationalUnit"),
                ldapConnectionTemplate.newAttribute("ou", "service_resource"));
        System.out.println("********************** 开始创建目录 **********************");
        TimerUtil.start();
        for (String[] item : OrganizationLoader.organizationList) {
            String dn;
            if (item[4].equals("3")) {
                dn = "ou=" + item[1] + ",ou=" + OrganizationLoader.organization_bm.get(item[5]) + ",ou=serviceResource,dc=dragonsoft,dc=com";
            } else {
                dn = "ou=" + item[1] + ",ou=serviceResource,dc=dragonsoft,dc=com";
            }
            AddResponse response = ldapConnectionTemplate.add(
                    ldapConnectionTemplate.newDn(dn),
                    ldapConnectionTemplate.newAttribute("objectClass", "top", "organizationalUnit"),
                    ldapConnectionTemplate.newAttribute("localityName", item[2]),
                    ldapConnectionTemplate.newAttribute("postalCode", item[0]),
                    ldapConnectionTemplate.newAttribute("ou", item[1]));
            System.out.println(response.toString());
        }
        TimerUtil.end();
        System.out.println("********************** 目录创建完成 **********************");
        System.out.println("********************** 单条目平均创建耗时：" + TimerUtil.TOTAL_TIME / OrganizationLoader.organizationList.size() + " ms **********************");
        System.out.println(TimerUtil.executeTime());

    }

    @Test
    public void fun02_addServiceResource() {
        int index = 0;
        System.out.println("********************** 开始创建资源 **********************");
        TimerUtil.start();
        for (String[] item : OrganizationLoader.organizationList) {
            String dn;
            if (item[4].equals("3")) {
                dn = "ou=" + item[1] + ",ou=" + OrganizationLoader.organization_bm.get(item[5]) + ",ou=serviceResource,dc=dragonsoft,dc=com";
            } else {
                dn = "ou=" + item[1] + ",ou=serviceResource,dc=dragonsoft,dc=com";
            }
            for (int i = index; i < index+30; i++) {
                AddResponse response = ldapConnectionTemplate.add(
                        ldapConnectionTemplate.newDn("serviceCode=S10-"+(10000000+i) + "," + dn),
                        ldapConnectionTemplate.newAttribute("objectClass", "top", "serviceResource"),
                        ldapConnectionTemplate.newAttribute("busCode", "B0100009001"),
                        ldapConnectionTemplate.newAttribute("isAdminStop", "FALSE"),
                        ldapConnectionTemplate.newAttribute("isDel", "FALSE"),
                        ldapConnectionTemplate.newAttribute("protocolType", "SOAP"),
                        ldapConnectionTemplate.newAttribute("serviceId", "ff80808166675c6a016667633afb0043"),
                        ldapConnectionTemplate.newAttribute("serviceName", "公安部cxf_rpc查询查询服务"+index),
                        ldapConnectionTemplate.newAttribute("serviceNature", "1"),
                        ldapConnectionTemplate.newAttribute("serviceNumber", "01"),
                        ldapConnectionTemplate.newAttribute("serviceShortName", "cxf_rpc查询"),
                        ldapConnectionTemplate.newAttribute("serviceUrl", "http://192.168.5.2:8880/cxf_services/services/QueryServiceRPC"),
                        ldapConnectionTemplate.newAttribute("resourceType", "人"),
                        ldapConnectionTemplate.newAttribute("resourceDetailType", "基本信息"),
                        ldapConnectionTemplate.newAttribute("serviceType", "查询"),
                        ldapConnectionTemplate.newAttribute("standardVersion", "5.5"),
                        ldapConnectionTemplate.newAttribute("workStatus", "FALSE"),
                        ldapConnectionTemplate.newAttribute("nodeId", "ff8080816665c1a2016665d6fa8f0008"),
                        ldapConnectionTemplate.newAttribute("techFrame", "cxf_rpc"));
                System.out.println(response.toString());
            }
            index+=30;
        }
        TimerUtil.end();
        System.out.println("********************** 资源创建完成 **********************");
        System.out.println("********************** 单条目平均创建耗时：" + TimerUtil.TOTAL_TIME / OrganizationLoader.organizationList.size() + " ms **********************");
        System.out.println(TimerUtil.executeTime());

    }

    @Test
    public void fun03_modifyServiceResource() {
        ModifyResponse response=ldapConnectionTemplate.modify(
                ldapConnectionTemplate.newDn("serviceName=cxf_rpc查询查询服务85,ou=上海市,ou=serviceResource,dc=dragonsoft,dc=com"),
                new RequestBuilder<ModifyRequest>() {
                    //配置请求对象，来修改的属性和值
                    @Override
                    public void buildRequest(ModifyRequest modifyRequest) throws LdapException {
                        modifyRequest.add("entranceNode", "http://192.168.23.32:8080/bus/")
                                    .replace("techFrame","cxf_doc");
                    }
                });
        System.out.println(response.toString());
    }

    @Test
    public void fun04_searchReturnObject() {
        //用FilterBuilder构造搜索条件
        String filter = FilterBuilder.equal("serviceName", "cxf_rpc查询查询服务85").toString();
        //搜索参数中的SearchScope，有三种搜索范围：OBJECT、ONELEVEL和SUBTREE，分别表示对应dn的条目、对应dn的下一层条目和对应dn下所有有关联的条目
        ServiceResource result = ldapConnectionTemplate.searchFirst("ou=serviceResource,dc=dragonsoft,dc=com", filter, SearchScope.SUBTREE, entryMapper);
        ldapConnectionTemplate.newSearchRequest("ou=serviceResource,dc=dragonsoft,dc=com", filter, SearchScope.SUBTREE).getResultResponse();
        System.out.println(result);
    }

    @Test
    public void fun05_searchReturnList() {
        String filter = FilterBuilder.contains("serviceName", "cxf_rpc查询查询服务").toString();
        List<ServiceResource> resourceList = ldapConnectionTemplate.search("ou=serviceResource,dc=dragonsoft,dc=com", filter, SearchScope.SUBTREE, entryMapper);
        resourceList.forEach(serviceResource -> System.out.println(serviceResource));
    }

    @Test
    public void fun06_deleteObject() {
        for (int i = 0; i < 10000; i++) {
            try {
                //Apache DS中删除条目时，必须保证条目下没有子条目，否则删除会失败
                DeleteResponse response = ldapConnectionTemplate.delete(new Dn("serviceId=S10-"+(10000000+i)+",dc=example,dc=com"));
                System.out.println(response.toString());
            } catch (LdapInvalidDnException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void fun07_searchPerformance() {
        int threadCount = 50;

        final CountDownLatch latch = new CountDownLatch(50);
        long s = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String filter = FilterBuilder.equal("serviceCode", "S10-10000000").toString();
                List<ServiceResource> resourceList = ldapConnectionTemplate.search("ou=serviceResource,dc=dragonsoft,dc=com", filter, SearchScope.SUBTREE, entryMapper);
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long e = System.currentTimeMillis();
        System.out.println("all time：" + (e - s));

        CyclicBarrier cyclicBarrier = new CyclicBarrier(threadCount);
        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < threadCount; i++) {
            SearchThread searchThread = new SearchThread(ldapConnectionTemplate,cyclicBarrier);
            pool.submit(searchThread);
        }
        pool.shutdown();
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(pool.isTerminated()){
                break;
            }
        }
    }

    @Test
    public void fun08_searchPerformance() {
        int count = 500;
        TimerUtil.start();
        for (int i = 0; i < count; i++) {
            String filter = FilterBuilder.equal("serviceName", "测试服务503").toString();
            List<ServiceResource> resourceList = ldapConnectionTemplate.search("serviceId=S20-10000503,dc=dragonsoft,dc=com", filter, SearchScope.OBJECT, entryMapper);
            resourceList.forEach(serviceResource -> System.out.println(serviceResource));
        }
        TimerUtil.end();
        System.out.println("********************** 单条目查询耗时：" + TimerUtil.TOTAL_TIME / 500 + " ms **********************");
    }

    @Test
    public void fun10() {
        //创建一个serviceResource目录
//        ldapConnectionTemplate.add(
//                ldapConnectionTemplate.newDn("ou=serviceResource,dc=example,dc=com"),
//                ldapConnectionTemplate.newAttribute("objectClass", "top", "organizationalUnit"),
//                ldapConnectionTemplate.newAttribute("ou", "service_resource"));
//        System.out.println("********************** 开始创建目录 **********************");
//        TimerUtil.start();
//        for (String[] item : OrganizationLoader.organizationList) {
//            String dn;
//            if (item[4].equals("3")) {
//                dn = "ou=" + item[1] + ",ou=" + OrganizationLoader.organization_bm.get(item[5]) + ",ou=serviceResource,dc=example,dc=com";
//            } else {
//                dn = "ou=" + item[1] + ",ou=serviceResource,dc=example,dc=com";
//            }
//            AddResponse response = ldapConnectionTemplate.add(
//                    ldapConnectionTemplate.newDn(dn),
//                    ldapConnectionTemplate.newAttribute("objectClass", "top", "organizationalUnit"),
//                    ldapConnectionTemplate.newAttribute("localityName", item[2]),
//                    ldapConnectionTemplate.newAttribute("postalCode", item[0]),
//                    ldapConnectionTemplate.newAttribute("ou", item[1]));
//            System.out.println(response.toString());
//        }
//        TimerUtil.end();
//        System.out.println("********************** 目录创建完成 **********************");
//        System.out.println("********************** 单条目平均创建耗时：" + TimerUtil.TOTAL_TIME / OrganizationLoader.organizationList.size() + " ms **********************");
//        System.out.println(TimerUtil.executeTime());

        int index = 0;
        System.out.println("********************** 开始创建资源 **********************");
        TimerUtil.start();
//        for (String[] item : OrganizationLoader.organizationList) {
//            String dn;
//            if (item[4].equals("3")) {
//                dn = "ou=" + item[1] + ",ou=" + OrganizationLoader.organization_bm.get(item[5]) + ",ou=serviceResource,dc=example,dc=com";
//            } else {
//                dn = "ou=" + item[1] + ",ou=serviceResource,dc=example,dc=com";
//            }
//            for (int i = index; i < index+30; i++) {
//                AddResponse response = ldapConnectionTemplate.add(
//                        ldapConnectionTemplate.newDn("serviceId=S10-"+(10000000+i) + "," + dn),
//                        ldapConnectionTemplate.newAttribute("objectClass", "top", "serviceResource"),
//                        ldapConnectionTemplate.newAttribute("serviceId", "S10-"+(10000000+i)),
//                        ldapConnectionTemplate.newAttribute("serviceName", "公安部cxf_rpc查询查询服务"+i),
//                        ldapConnectionTemplate.newAttribute("busCode", "B0"+(10000000+i)),
//                        ldapConnectionTemplate.newAttribute("isAdminStop", "FALSE"),
//                        ldapConnectionTemplate.newAttribute("isDel", "FALSE"),
//                        ldapConnectionTemplate.newAttribute("protocolType", "SOAP"),
//                        ldapConnectionTemplate.newAttribute("serviceCode", "S10-"+(10000000+i)),
//                        ldapConnectionTemplate.newAttribute("serviceNature", "1"),
//                        ldapConnectionTemplate.newAttribute("serviceNumber", "01"),
//                        ldapConnectionTemplate.newAttribute("serviceShortName", "cxf_rpc查询"),
//                        ldapConnectionTemplate.newAttribute("serviceUrl", "http://192.168.5.2:8880/cxf_services/services/QueryServiceRPC"),
//                        ldapConnectionTemplate.newAttribute("resourceType", "人"),
//                        ldapConnectionTemplate.newAttribute("resourceDetailType", "基本信息"),
//                        ldapConnectionTemplate.newAttribute("serviceType", "查询"),
//                        ldapConnectionTemplate.newAttribute("standardVersion", "5.5"),
//                        ldapConnectionTemplate.newAttribute("workStatus", "FALSE"),
//                        ldapConnectionTemplate.newAttribute("nodeId", "ff8080816665c1a2016665d6fa8f0008"),
//                        ldapConnectionTemplate.newAttribute("techFrame", "cxf_rpc")
//                );
//                System.out.println(response.toString());
//            }
//            index+=30;
//        }
        for (int i = 0; i < 10000; i++) {
            String dn = "dc=dragonsoft,dc=com";
            AddResponse response = ldapConnectionTemplate.add(
                    ldapConnectionTemplate.newDn("serviceId=S20-"+(10000000+i) + "," + dn),
                    ldapConnectionTemplate.newAttribute("objectClass", "top", "serviceResource"),
                    ldapConnectionTemplate.newAttribute("serviceId", "S20-"+(10000000+i)),
                    ldapConnectionTemplate.newAttribute("serviceName", "测试服务"+i),
                    ldapConnectionTemplate.newAttribute("busCode", "B0"+(10000000+i)),
                    ldapConnectionTemplate.newAttribute("isAdminStop", "FALSE"),
                    ldapConnectionTemplate.newAttribute("isDel", "FALSE"),
                    ldapConnectionTemplate.newAttribute("protocolType", "SOAP"),
                    ldapConnectionTemplate.newAttribute("serviceCode", "S20-"+(10000000+i)),
                    ldapConnectionTemplate.newAttribute("serviceNature", "1"),
                    ldapConnectionTemplate.newAttribute("serviceNumber", "01"),
                    ldapConnectionTemplate.newAttribute("serviceShortName", "cxf_rpc查询"),
                    ldapConnectionTemplate.newAttribute("serviceUrl", "http://192.168.5.2:8880/cxf_services/services/QueryServiceRPC"),
                    ldapConnectionTemplate.newAttribute("resourceType", "人"),
                    ldapConnectionTemplate.newAttribute("resourceDetailType", "基本信息"),
                    ldapConnectionTemplate.newAttribute("serviceType", "查询"),
                    ldapConnectionTemplate.newAttribute("standardVersion", "5.5"),
                    ldapConnectionTemplate.newAttribute("workStatus", "FALSE"),
                    ldapConnectionTemplate.newAttribute("nodeId", "ff8080816665c1a2016665d6fa8f0008"),
                    ldapConnectionTemplate.newAttribute("techFrame", "cxf_rpc")
            );
            System.out.println(response.toString());
        }
        TimerUtil.end();
        System.out.println("********************** 资源创建完成 **********************");
        System.out.println("********************** 单条目平均创建耗时：" + TimerUtil.TOTAL_TIME / OrganizationLoader.organizationList.size() + " ms **********************");
        System.out.println(TimerUtil.executeTime());

    }

//    @Test
//    public void getConfig() {
//        String filter = FilterBuilder.equal("cn", "{3}inetorgperson").toString();
//        LdapConnection connection;
//        try {
//            connection = ldapConnectionPool.getConnection();
//            EntryCursor cursor = connection.search(new Dn("cn=schema,cn=config"), filter, SearchScope.SUBTREE);
//            for (Entry entry : cursor) {
//                System.out.println(entry);
//            }
//        } catch (LdapException e) {
//            e.printStackTrace();
//        }
//    }

    //    @Test
//    public void fun05modfiySchema() {
//        DefaultSchemaManager schemaManager = null;
//        try {
//            schemaManager = new DefaultSchemaManager(new DefaultSchemaLoader(ldapConnectionPool.getConnection()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Schema serviceSchema = schemaManager.getLoadedSchema("java");
//        Set<SchemaObjectWrapper> set = serviceSchema.getContent();
//        AddResponse response = ldapConnectionTemplate.add(
//                ldapConnectionTemplate.newDn("m-oid=1.1.2.1.26,ou=attributetypes,cn=service,ou=schema"),
//                ldapConnectionTemplate.newAttribute("objectClass", "top", "metaTop","metaAttributeType"),
//                ldapConnectionTemplate.newAttribute("m-oid", "1.1.2.1.26"),
//                ldapConnectionTemplate.newAttribute("m-name", "abc"),
//                ldapConnectionTemplate.newAttribute("m-description", "a"),
//                ldapConnectionTemplate.newAttribute("m-equality", "caseIgnoreMatch"),
//                ldapConnectionTemplate.newAttribute("m-length", "50"),
//                ldapConnectionTemplate.newAttribute("m-singlevalue", "TRUE"),
//                ldapConnectionTemplate.newAttribute("m-syntax", "1.3.6.1.4.1.1466.115.121.1.15")
//        );
//
//        try {
//            LdapConnection ldapConnection = ldapConnectionPool.getConnection();
//            ldapConnection.loadSchema();
//        } catch (LdapException e) {
//            e.printStackTrace();
//        }

//        ModifyResponse response1 = ldapConnectionTemplate.modify(ldapConnectionTemplate.newDn("m-oid=1.1.2.2.1,ou=objectclasses,cn=service,ou=schema"), new RequestBuilder<ModifyRequest>() {
//            @Override
//            public void buildRequest(ModifyRequest modifyRequest) throws LdapException {
//                modifyRequest.add("m-may", "1.1.2.1.27");
//            }
//        });
//    }
}
