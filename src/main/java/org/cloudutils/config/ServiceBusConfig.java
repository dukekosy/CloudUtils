package org.cloudutils.config;

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

    public static ServiceBusConfig.Builder testBuilder() {
        return builder()
                .connectionString(
                        "Endpoint=sb://<NameOfServiceBusNamespace>.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=<AccessKey>");
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder connectionString(String connectionString);

        public abstract ServiceBusConfig build();
    }
}
