package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class Scope {
    private Claims claims;
    private AttributeSet attributeSet;
    private String name;
    private String description;
    private boolean userPermissionRequired;
    private boolean isGroupOfUserAttributes;
    private boolean allowModifyInConsent;
    private boolean includeAllClaimsInJWT;
    private Object includedClaimsInJWT;
    private boolean includeAllClaimsInIDToken;
    private Object includedClaimsInIDToken;
    private String text;
    private boolean adminApprovalRequired;
}
