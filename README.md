# CloudUtils
This project's goal is to provide a core packages for use with both Azure and AWS.
Initial consideration are to support Azure Service Bus along with other queuing platforms,
such as Rabbit.
This will then follow onto providing wrapper classes for use with caching and no sql platform.

Current progress with ASB:
Bus configuration settings.
Sending while reusing a single topic
Note for integration tests you will need to create your own ASB and use it's connection string.

Current progress with RabbitMQ:
The bare bone design pattern has been created for the implementation of both AWS and Azure for RabbitMQ.

Next up on ASB:
CU001 - retry settings
retry primary and secondary connection strings
set retry policy using retry patterns

CU002 - subscriber


