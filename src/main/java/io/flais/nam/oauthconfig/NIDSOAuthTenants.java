package io.flais.nam.oauthconfig;

import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"top", "nidsOAuthTenants"}, base = "cn=OAThglaq2,cn=OACsuo95y,cn=SCCxmrl2b,cn=cluster,cn=nids,ou=accessManagerContainer,o=novell")
public final class NIDSOAuthTenants {

    @Id
    private Name dn;

    @Attribute(name = "nidsOAuth2CFGXML")
    private String nidsOAuth2CFGXML;

    @Attribute(name = "nidsOAuthTenantXML")
    private String nidsOAuthTenantXML;

    @Attribute(name = "nidsTenantID")
    private String nidsTenantID;
}
