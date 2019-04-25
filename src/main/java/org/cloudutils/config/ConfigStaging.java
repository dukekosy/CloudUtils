package org.cloudutils.config;

import java.util.Optional;

public final class ConfigStaging implements Config {

    //singleton used to maintain one configuration instance application wide
    private ConfigStaging() {
    }

    public static ConfigStaging getInstance() {
        return instance;
    }

    private static final ConfigStaging instance = new ConfigStaging();
    Optional<ServiceBusConfig> serviceBusConfig = Optional.empty();
    Optional<RabbitMQConfig> rabbitMQConfig = Optional.empty();

    @Override
    public Optional<ServiceBusConfig> getServiceBusConfig() {
        return serviceBusConfig;
    }

    //have to use a setter here to maintain singleton and allow editing configuration settings
    public void setServiceBusConnectionString(String serviceBusConnectionString) {
        this.serviceBusConfig = Optional.of(ServiceBusConfig.builder().connectionString(serviceBusConnectionString).build());
    }

    @Override
    public Optional<RabbitMQConfig> getRabbitMQConfig() {
        return rabbitMQConfig;
    }

    public void setRabbitMQConfig(String connectionString, String moreConfigDetails) {
        this.rabbitMQConfig = Optional.of(RabbitMQConfig.builder().connectionString(connectionString).moreConfigDetails(moreConfigDetails).build());
    }

}
