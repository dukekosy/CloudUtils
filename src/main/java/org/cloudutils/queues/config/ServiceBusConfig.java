package org.cloudutils.queues.config;

import com.google.auto.value.AutoValue;

import java.util.Optional;

@AutoValue
public abstract class ServiceBusConfig {

    public abstract Optional<String> connectionString();

    public static ServiceBusConfig create(Optional<String> connectionString) {
        return builder()
                .connectionString(connectionString)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_ServiceBusConfig.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder connectionString(Optional<String> connectionString);

        public abstract ServiceBusConfig build();
    }
}
