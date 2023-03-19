package io.flais.nam.oauthconfig;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class EndPoint {
	private String endPointId;
	private String endPointName;
	private String endPointURL;
}

