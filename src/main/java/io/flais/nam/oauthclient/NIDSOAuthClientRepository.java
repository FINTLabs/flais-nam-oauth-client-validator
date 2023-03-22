package io.flais.nam.oauthclient;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NIDSOAuthClientRepository extends LdapRepository<NIDSOAuthClient> {


}