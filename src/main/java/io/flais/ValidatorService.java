package io.flais;

import com.netiq.applicationconfig.ApplicationConfig;
import com.netiq.auth2config.ApplicationConfigReferenceType;
import com.netiq.auth2config.OAuth2ConfigType;
import io.flais.nam.oauthclient.NIDSOAuthClient;
import io.flais.nam.oauthclient.NIDSOAuthClientRepository;
import io.flais.nam.oauthconfig.NIDSOAuthTenantsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ValidatorService {

    public static final String EXCLUDE_INTERNAL_OAUTH_CLIENT_CN = "OACa53en6";
    private final NIDSOAuthTenantsRepository nidsoAuthTenantsRepository;
    private final NIDSOAuthClientRepository nidsoAuthClientRepository;

    private Map<String, ApplicationConfigReferenceType> applicationConfigReferences = new HashMap<>();
    private Map<String, NIDSOAuthClient> applicationConfigs = new HashMap<>();

    public ValidatorService(NIDSOAuthTenantsRepository nidsoAuthTenantsRepository, NIDSOAuthClientRepository nidsoAuthClientRepository) {
        this.nidsoAuthTenantsRepository = nidsoAuthTenantsRepository;
        this.nidsoAuthClientRepository = nidsoAuthClientRepository;
    }

    private void refresh() {
        refreshApplicationConfigReferences();
        log.info("Found {} OAuth clients in OAuth Config XML", applicationConfigReferences.size());

        refreshOAuthClients();
        log.info("Found {} OAuth clients", applicationConfigs.size());

        findClientsNotInNidsOauth2CFGXML();
        findNidsOauth2CFGXMLClientRefsNotInClients();

    }

    public Summary getSummary() {
        refresh();
        return Summary
                .builder()
                .clientsNotInXML(findClientsNotInNidsOauth2CFGXML())
                .clientsNotInContainer(findNidsOauth2CFGXMLClientRefsNotInClients())
                .build();
    }

    private void refreshApplicationConfigReferences() {
        nidsoAuthTenantsRepository
                .findAll()
                .stream()
                .findFirst()
                .map(nidsoAuthTenants ->
                        XMLUnmarshallerFactory.unmarshallObject(nidsoAuthTenants.getNidsOAuth2CFGXML(), OAuth2ConfigType.class).getApplicationConfigReference()
                ).stream()
                .findFirst()
                .orElse(Collections.emptyList())
                .forEach(applicationConfigReferenceType -> applicationConfigReferences.put(applicationConfigReferenceType.getRefId(), applicationConfigReferenceType));
    }

    private void refreshOAuthClients() {
        nidsoAuthClientRepository
                .findAll()
                .forEach(nidsoAuthClient -> applicationConfigs.put(nidsoAuthClient.getNidsDisplayName(), nidsoAuthClient));
    }

    private List<NIDSOAuthClient> findClientsNotInNidsOauth2CFGXML() {
        ArrayList<NIDSOAuthClient> nidsOAuthClients = new ArrayList<>();
        applicationConfigs
                .values()
                .forEach(nidsoAuthClient -> {
                    ApplicationConfig applicationConfig = nidsoAuthClient.getApplicationConfig();
                    if (!applicationConfigReferences.containsKey(applicationConfig.getId())) {
                        nidsOAuthClients.add(nidsoAuthClient);
                    }
                });
        return nidsOAuthClients
                .stream()
                .filter(nidsoAuthClient -> !nidsoAuthClient.getCn().equals(EXCLUDE_INTERNAL_OAUTH_CLIENT_CN))
                .collect(Collectors.toList());
    }

    private List<ApplicationConfigReferenceType> findNidsOauth2CFGXMLClientRefsNotInClients() {
        List<ApplicationConfigReferenceType> applicationConfigReferenceTypes = new ArrayList<>();
        applicationConfigReferences
                .keySet()
                .forEach(s -> {
                    if (!applicationConfigs.containsKey(s)) {
                        applicationConfigReferenceTypes.add(applicationConfigReferences.get(s));
                    }

                });

        return applicationConfigReferenceTypes;
    }

}
