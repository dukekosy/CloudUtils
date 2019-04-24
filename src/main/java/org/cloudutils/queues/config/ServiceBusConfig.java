package org.cloudutils.queues.config;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ServiceBusConfig {
    //Builder pattern for all config classes, most of which will have more than one configuration property
    public abstract String connectionString();

    public static ServiceBusConfig create(String connectionString) {
        return builder()
                .connectionString(connectionString)
                .build();
    }

    public static Builder builder() {
        return new AutoValue_ServiceBusConfig.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder connectionString(String connectionString);

        public abstract ServiceBusConfig build();
    }
}
