package dev.sgsg.spring.gcp.quickstart.config;

import com.google.api.client.util.Value;
import com.google.cloud.ServiceOptions;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnvConfig {

  @Value("${SECRET_NAME:#{null}")
  private String secretName;

  @Value("${TOPIC_NAME:#{null}}")
  private String topicName;

  private final String projectId = ServiceOptions.getDefaultProjectId();

  public String getProjectId() {
    return projectId;
  }

  public String getSecretName() {
    return secretName;
  }
}
