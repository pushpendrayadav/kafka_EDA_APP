package com.tech.ps.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "product")
public class KafkaProperties {

    private String createTopicName;

    public String getCreateTopicName() {
        return createTopicName;
    }

    public void setCreateTopicName(String createTopicName) {
        this.createTopicName = createTopicName;
    }
}
