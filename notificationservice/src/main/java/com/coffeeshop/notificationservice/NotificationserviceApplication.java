package com.coffeeshop.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import com.coffeeshop.notificationservice.dto.Order;

@SpringBootApplication
public class NotificationserviceApplication {

	public static final String ACCOUNT_SID = "AC5671700de3eed420df9394580af9be57";
	public static final String AUTH_TOKEN = "04b658d7c54287eb32d99c472f3b8e95";

	public static void main(String[] args) {
		SpringApplication.run(NotificationserviceApplication.class, args);
		sendMessage();
	}

	public static void sendMessage() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		Message message = Message.creator(
						new com.twilio.type.PhoneNumber("+918330962183"),
						new com.twilio.type.PhoneNumber("+13184985204"),
						"you are under surveillance and every action will be traced. Good luck!")
				.create();

		System.out.println("Message sent");
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(String msg) throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		Order order = mapper.readValue(msg, Order.class);
//
//		System.out.println("Order has placed : Details "+ msg);
//		System.out.println("Order Price is : "+ order.getOrderPrice());
	}
}
