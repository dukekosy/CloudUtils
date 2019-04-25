package org.cloudutils.queues.config;

import java.util.Optional;

public class ConfigDev implements Config {
    //singleton used to maintain one configuration instance application wide
    private ConfigDev() {
    }

    private static final ConfigDev instance = new ConfigDev();

    public static ConfigDev getInstance() {
        return instance;
    }

    Optional<ServiceBusConfig> serviceBusConfig = Optional.empty();

    //used instead of property files so multiple organisations can configure accordingly
    @Override
    public Optional<ServiceBusConfig> getServiceBusConfig() {
        return serviceBusConfig;
    }

    //have to use a setter here to maintain singleton status and allow editing configuration settings
    public void setServiceBusConnectionString(String serviceBusConnectionString) {
        this.serviceBusConfig = Optional.of(ServiceBusConfig.builder().connectionString(serviceBusConnectionString).build());
    }
}
