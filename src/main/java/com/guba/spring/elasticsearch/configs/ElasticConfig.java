package com.guba.spring.elasticsearch.configs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchClients;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.support.HttpHeaders;
import org.springframework.lang.NonNull;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Configuration
@EnableElasticsearchRepositories(
        basePackages = "com.guba.spring.elasticsearch.databases.elasticsearch"
)
@RequiredArgsConstructor
@Log4j2
public class ElasticConfig extends ElasticsearchConfiguration {

    private final PropertiesElastic propertiesElastic;

    @Override
    @NonNull
    public ClientConfiguration clientConfiguration() {
        HttpHeaders httpHeaders = new HttpHeaders();
        propertiesElastic
                .getHeaders()
                .forEach(httpHeaders::add);

        SSLContext sslContext = null;
        try {
            SSLContextBuilder sslBuilder = SSLContexts.custom()
                    .loadTrustMaterial(null, (x509Certificates, s) -> true);
            sslContext = sslBuilder.build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            log.error("error create SSL: ", e);
        }

        assert sslContext != null;
        return ClientConfiguration
                .builder()
                .connectedTo(this.propertiesElastic.getHostAndPort())
                .usingSsl(sslContext)
                //.withProxy("localhost:8888")
                //.withPathPrefix("ela")
                .withConnectTimeout(this.propertiesElastic.getConnectionTimeout())
                .withSocketTimeout(this.propertiesElastic.getSocketTimeout())
                .withDefaultHeaders(httpHeaders)
                .withBasicAuth(this.propertiesElastic.getUsername(), this.propertiesElastic.getPassword())
                .withHeaders(() -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.add("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    return headers;
                })
                .withClientConfigurer(ElasticsearchClients.ElasticsearchHttpClientConfigurationCallback.from(clientBuilder -> clientBuilder))
                .build();
    }

    @Getter
    @Setter
    @Configuration
    @ConfigurationProperties(prefix = "elasticsearch")
    static class PropertiesElastic {
        private String hostAndPort;
        private String username;
        private String password;
        private Duration socketTimeout;
        private Duration connectionTimeout;
        private Map<String, String> headers;
    }
}
