package pers.lxx.ldap.ldapclientapi.repository;

import org.apache.directory.ldap.client.template.LdapConnectionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("ldapRepository")
public class LdapRepository{

    @Autowired
    private LdapConnectionTemplate ldapConnectionTemplate;

}
