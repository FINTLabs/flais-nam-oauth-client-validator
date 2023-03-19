package io.flais;

import com.ctc.wstx.stax.WstxInputFactory;
import com.ctc.wstx.stax.WstxOutputFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import io.flais.nam.oauthconfig.NIDSOAuthTenants;
import io.flais.nam.oauthconfig.NIDSOAuthTenantsRepository;
import io.flais.nam.oauthconfig.OAuth2Config;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import java.util.List;

@Slf4j
@Service
public class ValidatorService {

    private final NIDSOAuthTenantsRepository nidsoAuthTenantsRepository;
    private final XmlMapper objectMapper;

    public ValidatorService(NIDSOAuthTenantsRepository nidsoAuthTenantsRepository) {
        this.nidsoAuthTenantsRepository = nidsoAuthTenantsRepository;
        XMLInputFactory input = new WstxInputFactory();
        input.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.FALSE);
        objectMapper = new XmlMapper(new XmlFactory(input, new WstxOutputFactory()));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @PostConstruct
    public void init() {
        List<NIDSOAuthTenants> tenantsList = nidsoAuthTenantsRepository.findAll();
        log.info("Found {} NIDS OAuth Tenants", tenantsList.size());

        try {
            OAuth2Config oAuth2Config = objectMapper.readValue(tenantsList.get(0).getNidsOAuth2CFGXML(), OAuth2Config.class);
            log.info("");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
