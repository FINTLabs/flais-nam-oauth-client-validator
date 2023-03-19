package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class AttributeSet {
    private String name;
    private String dn;
}
