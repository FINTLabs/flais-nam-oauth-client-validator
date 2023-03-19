package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CryptoKeys {
    private JWTAccessTokenEncryptionAlgo jwtAccessTokenEncryptionAlgo;
    private Object jwksUri;
    private Object jwks;
}
