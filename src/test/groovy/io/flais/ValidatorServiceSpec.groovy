package io.flais

import com.netiq.applicationconfig.ApplicationConfig
import com.netiq.auth2config.ApplicationConfigReferenceType
import com.netiq.auth2config.OAuth2ConfigType
import io.flais.nam.oauthclient.NIDSOAuthClient
import io.flais.nam.oauthclient.NIDSOAuthClientRepository
import io.flais.nam.oauthconfig.NIDSOAuthTenants
import io.flais.nam.oauthconfig.NIDSOAuthTenantsRepository
import spock.lang.Specification

class ValidatorServiceSpec extends Specification {

    def nidsOauth2CFGXML
    def clientObjects

    def nidsoAuthTenantsRepository = Mock(NIDSOAuthTenantsRepository.class)
    def nidsoAuthClientRepository = Mock(NIDSOAuthClientRepository.class)
    def validatorService = new ValidatorService(nidsoAuthTenantsRepository, nidsoAuthClientRepository)

    void setup() {
        nidsOauth2CFGXML = [
                new NIDSOAuthTenants(
                        nidsOAuth2CFGXML: XMLUnmarshallerFactory.marshallObject(
                                new OAuth2ConfigType(applicationConfigReference: [
                                        new ApplicationConfigReferenceType(refId: "1"),
                                        new ApplicationConfigReferenceType(refId: "2"),
                                        new ApplicationConfigReferenceType(refId: "3"),
                                        new ApplicationConfigReferenceType(refId: "4"),
                                ]),
                                OAuth2ConfigType.class
                        )
                )
        ]
        clientObjects = [
                new NIDSOAuthClient(nidsDisplayName: "1", applicationConfig: new ApplicationConfig(id: "1"), cn: "1"),
                new NIDSOAuthClient(nidsDisplayName: "2", applicationConfig: new ApplicationConfig(id: "2"), cn: "2"),
                new NIDSOAuthClient(nidsDisplayName: "3", applicationConfig: new ApplicationConfig(id: "3"), cn: "3"),
                new NIDSOAuthClient(nidsDisplayName: "5", applicationConfig: new ApplicationConfig(id: "5"), cn: "5"),
        ]
    }

    def "Summary should return an object with the difference of clients in object and clients in XML"() {
        given:
        nidsoAuthTenantsRepository.findAll() >> nidsOauth2CFGXML
        nidsoAuthClientRepository.findAll() >> clientObjects

        when:
        def summary = validatorService.getSummary()

        then:
        summary.getClientsNotInXML().size() == 1
        summary.getClientsNotInXML().get(0).getNidsDisplayName() == "5"
        summary.getClientsNotInContainer().size() == 1
        summary.getClientsNotInContainer().get(0).getRefId() == "4"

    }

    def "findClientsNotInNidsOauth2CFGXML should exclude internal OAuth client with CN OACa53en6"() {
        given:
        clientObjects.add(
                new NIDSOAuthClient(
                        nidsDisplayName: "100",
                        applicationConfig: new ApplicationConfig(id: "100"),
                        cn: ValidatorService.EXCLUDE_INTERNAL_OAUTH_CLIENT_CN),
        )
        nidsoAuthTenantsRepository.findAll() >> nidsOauth2CFGXML
        nidsoAuthClientRepository.findAll() >> clientObjects

        validatorService.refresh()


        when:
        def clientsNotInXML = validatorService.findClientsNotInNidsOauth2CFGXML()

        then:
        clientsNotInXML.size() == 1
        clientsNotInXML[0].nidsDisplayName == "5"
    }
}
