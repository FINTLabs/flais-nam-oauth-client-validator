package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@Jacksonized
public class OAuth2Config {
    private EndPointList endPointList;
    private Scopes scopes;
    private String ldapAttributeName;
    private List<ApplicationConfigReference> applicationConfigReference;
    private double lastModifiedTime;
    private String id;
    private double version;
    private String ns5;
    private String ns2;
    private String ns4;
    private String ns3;
    private String text;
}
