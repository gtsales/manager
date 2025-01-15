package luiz.sales.manager.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import luiz.sales.manager.constants.RabbitMQConstants;

@Configuration
public class RabbitMQConfig {

	@Bean(name = "managerConectionFactory")
	@ConfigurationProperties(prefix = "spring.rabbitmq")
	ConnectionFactory managerConectionFactory() {
		
		return new CachingConnectionFactory();
	}
	
	@Bean
	Declarables directExchange() {
		
		Queue auditQueue = new Queue(RabbitMQConstants.QUEUE_NAME, true);
		DirectExchange directExchange = new DirectExchange(RabbitMQConstants.MANAGER_DIRECT_EXCHANGE, true, false);
		
		return new Declarables(
				auditQueue,
				directExchange,
				BindingBuilder.bind(auditQueue).to(directExchange).with(RabbitMQConstants.ROUTING_KEY));
	}
	
	@Bean
	SimpleMessageListenerContainer container(@Qualifier("managerConectionFactory") ConnectionFactory connectionFactory) {

		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);

		return container;
	}
}
