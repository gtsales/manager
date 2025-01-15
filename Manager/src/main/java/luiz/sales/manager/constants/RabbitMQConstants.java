package luiz.sales.manager.constants;

public class RabbitMQConstants {

	public static final String MANAGER_DIRECT_EXCHANGE_AUDIT = "manager_direct_exchange_audit";
	public static final String QUEUE_NAME_AUDIT  = "manager_audit";
	public static final String ROUTING_KEY_AUDIT  = "manager_key_audit";
	
	public static final String MANAGER_DIRECT_EXCHANGE = "manager_direct_exchange_register";
	public static final String QUEUE_NAME = "manager_register";
	public static final String ROUTING_KEY = "manager_register_key";
}
