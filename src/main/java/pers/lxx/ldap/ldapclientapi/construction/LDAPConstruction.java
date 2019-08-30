package pers.lxx.ldap.ldapclientapi.construction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class LDAPConstruction {



    @PostConstruct
    public void construction() {

    }

    public void constructionMachine() {}

    public void constructionCatalog() {}

    public void constructionSchema() {}

}
