package dev.sgsg.spring.gcp.quickstart.config;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import javax.annotation.PreDestroy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  private final EnvConfig envConfig;
  private final SecretManagerServiceClient secretManagerServiceClient;

  public AppConfig(EnvConfig envConfig) throws IOException {
    this.envConfig = envConfig;
    this.secretManagerServiceClient = SecretManagerServiceClient.create();
  }

  @Bean
  public Storage getStorage() {
    return StorageOptions.getDefaultInstance().getService();
  }

  @Bean
  public Datastore getDatastore() {
    return DatastoreOptions.getDefaultInstance().getService();
  }

  @Bean
  public SecretManagerServiceClient getSecretManagerServiceClient() {
    return secretManagerServiceClient;
  }

  @PreDestroy
  private void preDestroy() {
    if(secretManagerServiceClient != null) {
      secretManagerServiceClient.close();
    }
  }
}
