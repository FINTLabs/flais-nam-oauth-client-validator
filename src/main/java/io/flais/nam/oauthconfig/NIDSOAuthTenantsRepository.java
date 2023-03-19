package io.flais.nam.oauthconfig;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NIDSOAuthTenantsRepository extends LdapRepository<NIDSOAuthTenants> {


}