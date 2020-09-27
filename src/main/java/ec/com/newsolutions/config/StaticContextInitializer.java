package ec.com.newsolutions.config;

import ec.com.newsolutions.web.rest.util.HeaderUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StaticContextInitializer {

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public StaticContextInitializer() {
    }

    @PostConstruct
    public void init() {
        HeaderUtil.setApplicationName(this.applicationName);
    }


}

