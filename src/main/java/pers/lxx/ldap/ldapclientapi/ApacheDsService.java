package pers.lxx.ldap.ldapclientapi;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.directory.api.ldap.model.cursor.CursorException;
import org.apache.directory.api.ldap.model.entry.DefaultEntry;
import org.apache.directory.api.ldap.model.entry.Entry;
import org.apache.directory.api.ldap.model.exception.LdapException;
import org.apache.directory.api.ldap.model.message.AddRequest;
import org.apache.directory.api.ldap.model.message.AddRequestImpl;
import org.apache.directory.api.ldap.model.message.AddResponse;
import org.apache.directory.api.ldap.model.message.SearchScope;
import org.apache.directory.api.ldap.model.message.controls.ManageDsaITImpl;
import org.apache.directory.api.ldap.model.name.Dn;
import org.apache.directory.ldap.client.api.*;
import org.apache.directory.ldap.client.api.search.FilterBuilder;
import org.apache.directory.ldap.client.template.EntryMapper;
import org.apache.directory.ldap.client.template.LdapConnectionTemplate;
import org.apache.directory.ldap.client.template.RequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.lxx.ldap.ldapclientapi.entity.ServiceResource;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by soberlevi on 2019-01-31
 */
public class ApacheDsService {

    public static void main(String[] args) throws LdapException, CursorException, InterruptedException, IOException, BrokenBarrierException {
//        template();
//        add();
        Logger log = LoggerFactory.getLogger(ApacheDsService.class);
        search(log);
    }

    private static EntryMapper getEntryMapper () {
        return (EntryMapper<ServiceResource>) entry -> new ServiceResource.Builder()
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

    public static void search(Logger log) throws LdapException, IOException, BrokenBarrierException, InterruptedException {
//        LdapConnection connection = new LdapNetworkConnection("localhost", 10389);

        LdapConnectionConfig ldapConnectionConfig = new LdapConnectionConfig();
        ldapConnectionConfig.setLdapHost("192.168.150.66");
        ldapConnectionConfig.setLdapPort(10389);
        ldapConnectionConfig.setName("uid=admin,ou=system");
        ldapConnectionConfig.setCredentials("secret");

        DefaultLdapConnectionFactory defaultLdapConnectionFactory = new DefaultLdapConnectionFactory(ldapConnectionConfig);

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setLifo(true);
        genericObjectPoolConfig.setMaxIdle(8);
        genericObjectPoolConfig.setMaxTotal(8);
        genericObjectPoolConfig.setMaxWaitMillis(-1L);
        genericObjectPoolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 30L);
        genericObjectPoolConfig.setMinIdle(0);
        genericObjectPoolConfig.setNumTestsPerEvictionRun(3);
        genericObjectPoolConfig.setSoftMinEvictableIdleTimeMillis(-1L);
        genericObjectPoolConfig.setTestOnBorrow(false);
        genericObjectPoolConfig.setTestOnReturn(false);
        genericObjectPoolConfig.setTestWhileIdle(false);
        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(-1L);
        genericObjectPoolConfig.setBlockWhenExhausted(false);

        LdapConnectionTemplate ldapConnectionTemplate = new LdapConnectionTemplate(new LdapConnectionPool(
                new ValidatingPoolableLdapConnectionFactory(defaultLdapConnectionFactory), genericObjectPoolConfig)
        );

        String filter = FilterBuilder.equal("serviceName", "cxf_rpc查询查询服务85").toString();

        long s = System.currentTimeMillis();

/*        List<ServiceResource> resourceList = ldapConnectionTemplate.search("ou=serviceResource,dc=dragonsoft,dc=com", filter, SearchScope.SUBTREE, getEntryMapper());
        System.out.println(resourceList.get(0));*/

        final CountDownLatch latch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                /*try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }*/
                log.error("dddd==========================================");
                log.debug("cc==========================================");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<ServiceResource> resourceList = ldapConnectionTemplate.search("ou=serviceResource,dc=dragonsoft,dc=com", filter, SearchScope.SUBTREE, getEntryMapper());
                latch.countDown();
            }).start();
        }
        latch.await();
        long e = System.currentTimeMillis();
        System.out.println("all time：" + (e - s));
    }

    public static void add() throws LdapException, InterruptedException, IOException {
        LdapConnection connection = new LdapNetworkConnection("localhost", 10389);
/*        connection.add(new DefaultEntry("cn=testadd,ou=system",
                "ObjectClass: top",
                "ObjectClass: person",
                "cn: testadd_cn",
                "sn: testadd_sn"));*/
        Dn dn = new Dn("dc=dragonsoft,dc=com,dc=cn");
        Entry entry = new DefaultEntry("uid=dragonsoft111,dc=dragonsoft,dc=com,dc=cn",
                "objectclass: account",
                "objectclass: top",
                "l: a",
                "l: b");
        AddRequest request = new AddRequestImpl();
        request.setEntryDn(dn);
        request.setEntry(entry);
        request.addControl(new ManageDsaITImpl());

        AddResponse addResponse = connection.add(request);
//        AddFuture addFuture = ((LdapNetworkConnection) connection).addAsync(request);
//        AddResponse addResponse = addFuture.get(100, TimeUnit.SECONDS);
        System.out.println("xxxxxx:" + addResponse.getLdapResult().getResultCode());
//        System.out.println(connection.exists("cn=levi,ou=system"));
        connection.close();
    }

    public static void template() {
        LdapConnectionConfig ldapConnectionConfig = new LdapConnectionConfig();
        ldapConnectionConfig.setLdapHost("localhost");
        ldapConnectionConfig.setLdapPort(10389);

//        poolConfig.lifo = true;
//        poolConfig.maxActive = 8;
//        poolConfig.maxIdle = 8;
//        poolConfig.maxWait = -1L;
//        poolConfig.minEvictableIdleTimeMillis = 1000L * 60L * 30L;
//        poolConfig.minIdle = 0;
//        poolConfig.numTestsPerEvictionRun = 3;
//        poolConfig.softMinEvictableIdleTimeMillis = -1L;
//        poolConfig.testOnBorrow = false;
//        poolConfig.testOnReturn = false;
//        poolConfig.testWhileIdle = false;
//        poolConfig.timeBetweenEvictionRunsMillis = -1L;
//        poolConfig.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_BLOCK;

        DefaultLdapConnectionFactory defaultLdapConnectionFactory = new DefaultLdapConnectionFactory(ldapConnectionConfig);

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setLifo(true);
        genericObjectPoolConfig.setMaxIdle(8);
        genericObjectPoolConfig.setMaxTotal(8);
        genericObjectPoolConfig.setMaxWaitMillis(-1L);
        genericObjectPoolConfig.setMinEvictableIdleTimeMillis(1000L * 60L * 30L);
        genericObjectPoolConfig.setMinIdle(0);
        genericObjectPoolConfig.setNumTestsPerEvictionRun(3);
        genericObjectPoolConfig.setSoftMinEvictableIdleTimeMillis(-1L);
        genericObjectPoolConfig.setTestOnBorrow(false);
        genericObjectPoolConfig.setTestOnReturn(false);
        genericObjectPoolConfig.setTestWhileIdle(false);
        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(-1L);
        genericObjectPoolConfig.setBlockWhenExhausted(false);

        LdapConnectionTemplate ldapConnectionTemplate = new LdapConnectionTemplate(new LdapConnectionPool(
                new ValidatingPoolableLdapConnectionFactory(defaultLdapConnectionFactory), genericObjectPoolConfig)
        );

        ldapConnectionTemplate.add(ldapConnectionTemplate.newDn("cn=testaddlevi,ou=system"),
                new RequestBuilder<AddRequest>() {
                    @Override
                    public void buildRequest(AddRequest request) throws LdapException {
                        request.getEntry()
                                .add("objectClass", "top", "person", "organizationalPerson", "inetOrgPerson")
                                .add("cn", "Kermit The Frog")
                                .add("givenName", "Kermit")
                                .add("sn", "The Frog")
                                .add("mail", "kermitthefrog@muppets.org")
                                .add("uid", "kermitthefrog");
                    }
                });

    }

}
