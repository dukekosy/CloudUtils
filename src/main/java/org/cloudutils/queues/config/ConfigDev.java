package org.cloudutils.queues.config;

import java.util.Optional;

public class ConfigDev implements Config {
//todo turn this into builder, perhaps
    private ConfigDev() {
    }

    private static final ConfigDev instance = new ConfigDev();

    public static ConfigDev getInstance() {
        return instance;
    }

    Optional<String> serviceBusConnectionString;

    @Override
    public ServiceBusConfig getServiceBusConfig() {
        return ServiceBusConfig.builder().connectionString(serviceBusConnectionString).build();
    }

    public void setServiceBusConnectionString(Optional<String> serviceBusConnectionString) {
        this.serviceBusConnectionString = serviceBusConnectionString;
    }
}
