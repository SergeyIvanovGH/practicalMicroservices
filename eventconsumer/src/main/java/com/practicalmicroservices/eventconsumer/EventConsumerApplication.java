package com.practicalmicroservices.eventproducer;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;

@SpringBootApplication
@RabbitListener(queues = "crispyBunOrder")
public class EventConsumerApplication {

	@Bean
	public Queue crispyBunOrderQueue() {
		return new Queue("crispyBunOrder");
	}

	@RabbitHandler
	public void process(@Payload CrispyBunOrder order) {
		StringBuffer sb = new StringBuffer();
		sb.append("New Order Received : \n");
		sb.append("OrderId : " + order.getOrderId());
		sb.append("\nItemId : " + order.getItemId());
		sb.append("\nUserName : " + order.getUserName());
		sb.append("\nDate : " + order.getOrderPlacedTime());

		System.out.println(sb.toString());
	}


	public static void main(String[] args) {
		SpringApplication.run(EventConsumerApplication.class, args);
	}

}
