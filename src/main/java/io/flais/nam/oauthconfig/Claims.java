package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Getter
@Builder
@Jacksonized
public class Claims {
    private List<String> claim;
}
