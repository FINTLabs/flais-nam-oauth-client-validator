package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@Jacksonized
public class ResourceServer {
    private List<Scope> scope;
    private CryptoKeys cryptoKeys;
    private String name;
    private boolean disableJWTAccessTokenEncryption;
    private String text;
}
