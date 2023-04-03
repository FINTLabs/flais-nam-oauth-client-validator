package io.flais.nam.oauthclient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.netiq.applicationconfig.ApplicationConfig;
import io.flais.XMLUnmarshallerFactory;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@Entry(objectClasses = {"top", "nidsOAuthClients"}, base = "cn=OACCrm59qh,cn=OAThglaq2,cn=OACsuo95y,cn=SCCxmrl2b,cn=cluster,cn=nids,ou=accessManagerContainer,o=novell")
public final class NIDSOAuthClient {

    @Id
    private Name dn;
    @Attribute(name = "cn")
    private String cn;

    @Attribute(name = "nidsDisplayName")
    private String nidsDisplayName;

    @JsonIgnore
    @Attribute(name = "nidsOAuthClientXML")
    private String nidsOAuthClientXML;

    public ApplicationConfig getApplicationConfig() {
        return XMLUnmarshallerFactory.unmarshallObject(nidsOAuthClientXML, ApplicationConfig.class);
    }

    public void setApplicationConfig(ApplicationConfig applicationConfig) {
        nidsOAuthClientXML = XMLUnmarshallerFactory.marshallObject(applicationConfig, ApplicationConfig.class);
    }

    public String getDn() {
        return dn.toString();
    }

}
