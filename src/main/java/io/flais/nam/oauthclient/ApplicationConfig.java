package io.flais.nam.oauthclient;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ApplicationConfig {
    private RedirectUrlList redirectUrlList;
    private ResponseTypesList responseTypesList;
    private GrantTypesList grantTypesList;
    private CorsdomainNames corsdomainNames;
    private String applicationType;
    private CryptoKeys cryptoKeys;
    private ScopeList scopeList;
    private String id;
    private String clientSecret;
    private String clientName;
    private boolean requireAuthTime;
    private boolean supportsAuthorizationCode;
    private boolean supportsImplicit;
    private boolean supportsROCredentials;
    private boolean supportsClientCredentials;
    private boolean supportsSAML2Assertion;
    private boolean supportsRefreshTokens;
    private String configurationEndpointUrl;
    private double clientIdIssuedAt;
    private double clientSecretExpiresAt;
    private int accessTokenTTL;
    private int refreshTokenTTL;
    private int authzCodeTTL;
    private String developerDn;
    private boolean alwaysIssueNewRefreshToken;
    private boolean enableNativeSSO;
    private String ns5;
    private String ns2;
    private String ns4;
    private String ns3;
    private String text;
}
