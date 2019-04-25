package org.cloudutils.config;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class RabbitMQConfig {

    public abstract String connectionString();

    public abstract String moreConfigDetails();

    public static RabbitMQConfig create(String connectionString, String moreConfigDetails) {
        return builder()
                .connectionString(connectionString)
                .moreConfigDetails(moreConfigDetails)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_RabbitMQConfig.Builder();
    }


    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder connectionString(String connectionString);

        public abstract Builder moreConfigDetails(String moreConfigDetails);

        public abstract RabbitMQConfig build();
    }
}
