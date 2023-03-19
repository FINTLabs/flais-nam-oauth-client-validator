package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class JWTAccessTokenEncryptionAlgo {
    private String encryptionAlg;
    private String encryptionEnc;
}
