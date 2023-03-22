package io.flais;

import com.netiq.auth2config.ApplicationConfigReferenceType;
import io.flais.nam.oauthclient.NIDSOAuthClient;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Data
@Jacksonized
@Builder
public class Summary {

    private List<NIDSOAuthClient> clientsNotInXML;
    private List<ApplicationConfigReferenceType> clientsNotInContainer;
}
