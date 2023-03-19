package io.flais.nam.oauthclient;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@Jacksonized
public class CorsdomainNames {
    private List<String> domainName;
}
