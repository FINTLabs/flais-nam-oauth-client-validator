package io.flais

import com.netiq.applicationconfig.ApplicationConfig
import com.netiq.auth2config.ApplicationConfigReferenceType
import com.netiq.auth2config.OAuth2ConfigType
import spock.lang.Specification

class XMLUnmarshallerFactorySpec extends Specification {

    def "should unmarshall object successfully"() {
        given:
        def xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<ApplicationConfig id=\"00000000-0000-0000-0000-000000000000\" clientSecret=\"topsecret\" clientName=\"name\" requireAuthTime=\"false\" supportsAuthorizationCode=\"false\" supportsImplicit=\"false\" supportsROCredentials=\"true\" supportsClientCredentials=\"false\" supportsSAML2Assertion=\"false\" supportsRefreshTokens=\"false\" configurationEndpointUrl=\"https://idp.felleskomponent.no/nidp/oauth/nam/clients//00000000-0000-0000-0000-000000000000\" clientIdIssuedAt=\"1536234593568\" clientSecretExpiresAt=\"1536320993568\" accessTokenTTL=\"0\" refreshTokenTTL=\"0\" authzCodeTTL=\"0\" developerDn=\"developer\" alwaysIssueNewRefreshToken=\"false\" enableNativeSSO=\"false\" xmlns:ns5=\"http://www.netiq.com/assertionissuer\" xmlns:ns2=\"http://www.netiq.com/tenantcfg\" xmlns:ns4=\"http://www.netiq.com/oauth2types\" xmlns:ns3=\"http://www.netiq.com/OAuth2Config\">\n" +
                "    <RedirectUrlList>\n" +
                "        <Url>https://dummy.com</Url>\n" +
                "    </RedirectUrlList>\n" +
                "    <ResponseTypesList>\n" +
                "        <responseType>token</responseType>\n" +
                "    </ResponseTypesList>\n" +
                "    <GrantTypesList>\n" +
                "        <grantType>password</grantType>\n" +
                "    </GrantTypesList>\n" +
                "    <corsdomainNames>\n" +
                "        <ns4:domainName>beta.felleskomponent.no</ns4:domainName>\n" +
                "        <ns4:domainName>api.felleskomponent.no</ns4:domainName>\n" +
                "    </corsdomainNames>\n" +
                "    <ApplicationType>web</ApplicationType>\n" +
                "    <CryptoKeys>\n" +
                "        <IdTokenResponseAlgorithms/>\n" +
                "    </CryptoKeys>\n" +
                "    <ScopeList>\n" +
                "        <names>__ALL__</names>\n" +
                "    </ScopeList>\n" +
                "</ApplicationConfig>"

        when:
        def applicationConfig = XMLUnmarshallerFactory.unmarshallObject(xml, ApplicationConfig.class)

        then:
        applicationConfig instanceof ApplicationConfig
        applicationConfig.getId() == "00000000-0000-0000-0000-000000000000"

    }

    def "should throw RuntimeException when unmarshalling fails"() {
        given:
        def xml = "<ApplicationConfig id=1/>"

        when:
        XMLUnmarshallerFactory.unmarshallObject(xml, ApplicationConfig.class)


        then:
        thrown(RuntimeException.class)
    }

    def "should marshall object successfully"() {

        given:
        def oauth2ConfigType = new OAuth2ConfigType(applicationConfigReference: [new ApplicationConfigReferenceType(refId: "123")])

        when:
        def xml = XMLUnmarshallerFactory.marshallObject(oauth2ConfigType, OAuth2ConfigType.class)

        then:
        xml.startsWith("<?xml")
    }
}
