package br.com.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

//@EnableJms
@SpringBootApplication
public class JmsProducerApplication {

	public static void main(String[] args) {
		System.out.println("Ihuuuuuuuuuuuuuuuuuuuuuu");
		SpringApplication.run(JmsProducerApplication.class, args);
		System.out.println("Ihaaaaaaaaaaaaaaaaaaaaaa");
	}

}
