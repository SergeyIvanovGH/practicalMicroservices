package com.practicalmicroservices.eventproducer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableBinding
@RestController
public class EventproducerApplication {

	private final String Queue = "crispyBunOrder";
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public static void main(String[] args) {
		SpringApplication.run(EventproducerApplication.class, args);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/orders/{orderId}")
	public void placeOrder(@PathVariable("orderId") UUID orderId,
	                       @RequestParam("itemId") Integer itemId,
	                       @RequestParam("userName") String userName) {

		CrispyBunOrder orderObject = createOrder(orderId, itemId, userName);
		rabbitTemplate.convertAndSend(Queue, orderObject);
	}

	private CrispyBunOrder createOrder(UUID orderId, Integer itemId, String userName) {

		CrispyBunOrder order = new CrispyBunOrder();

		order.setItemId(itemId);
		order.setOrderId(orderId);
		order.setUserName(userName);
		order.setOrderPlacedTime(new Date());

		return order;
	}
}
