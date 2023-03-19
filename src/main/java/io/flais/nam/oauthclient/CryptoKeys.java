package io.flais.nam.oauthclient;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class CryptoKeys {
    private Object idTokenResponseAlgorithms;
}
