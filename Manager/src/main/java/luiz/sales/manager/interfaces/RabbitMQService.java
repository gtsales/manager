package luiz.sales.manager.interfaces;

public interface RabbitMQService {

	void sendMessage(String nameQueue, Object profile);
}
