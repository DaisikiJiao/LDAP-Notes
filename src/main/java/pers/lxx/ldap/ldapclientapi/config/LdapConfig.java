package pers.lxx.ldap.ldapclientapi.config;

import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.directory.ldap.client.api.DefaultLdapConnectionFactory;
import org.apache.directory.ldap.client.api.LdapConnectionConfig;
import org.apache.directory.ldap.client.api.LdapConnectionPool;
import org.apache.directory.ldap.client.api.ValidatingPoolableLdapConnectionFactory;
import org.apache.directory.ldap.client.template.LdapConnectionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LdapConfig {

    @Value("${ldap.host:172.16.0.122}")
    String ldapHost;
    @Value("${ldap.port:389}")
    int ldapPort;
    @Value("${ldap.timeout:60000}")
    int timeOut;

    @Bean
    public LdapConnectionConfig ldapConnection() {
        LdapConnectionConfig config = new LdapConnectionConfig();
        config.setLdapHost(ldapHost);
        config.setLdapPort(ldapPort);
        //TODO 更换非匿名连接
//        config.setName("uid=admin,ou=system");           //用户名
//        config.setCredentials("secret");
        config.setName("cn=admin");           //用户名
        config.setCredentials("12345678");    //密码
        return config;
    }

    @Bean
    public DefaultLdapConnectionFactory factory() {
        DefaultLdapConnectionFactory factory = new DefaultLdapConnectionFactory(ldapConnection());
        factory.setTimeOut(timeOut);
        return factory;
    }

    @Bean
    public LdapConnectionPool ldapConnectionPool(){
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setLifo(true);
        poolConfig.setMaxTotal(8);
        poolConfig.setMaxIdle(8);
        poolConfig.setMaxWaitMillis(-1L);
        poolConfig.setMinEvictableIdleTimeMillis(1000L*60L*30L);
        poolConfig.setMinIdle(0);
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setSoftMinEvictableIdleTimeMillis(-1L);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);
        poolConfig.setTestWhileIdle(false);
        poolConfig.setTimeBetweenEvictionRunsMillis(-1L);
        poolConfig.setBlockWhenExhausted(BaseObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED);
        return new LdapConnectionPool(new ValidatingPoolableLdapConnectionFactory(factory()), poolConfig);
    }

    @Bean
    public LdapConnectionTemplate ldapConnectionTemplate(){
        return new LdapConnectionTemplate(ldapConnectionPool());
    }

}
