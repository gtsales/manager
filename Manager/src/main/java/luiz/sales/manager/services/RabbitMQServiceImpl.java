package luiz.sales.manager.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import luiz.sales.manager.interfaces.RabbitMQService;

@Service
@Slf4j
public class RabbitMQServiceImpl implements RabbitMQService{

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Override
	public void sendMessage(String nameQueue, Object profile) {
		
		log.trace("Starting send message to queue. PROFILE {%s}", profile);
		
		rabbitTemplate.convertAndSend(nameQueue, profile);
	}
}
