package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class ApplicationConfigReference {
    private String refId;
    private String clientName;
    private String developer;
    private String applicationType;
    private String cn;
    private boolean enabled;
}
