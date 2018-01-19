package br.com.jms.producer;

import static org.assertj.core.api.Assertions.assertThat;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.activemq.junit.EmbeddedActiveMQBroker;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsProducerApplicationTests {

	private static ApplicationContext applicationContext;

	@Autowired
	void setContext(ApplicationContext applicationContext) {
		JmsProducerApplicationTests.applicationContext = applicationContext;
	}

	@AfterClass
	public static void afterClass() {
		((ConfigurableApplicationContext) applicationContext).close();
	}

//	@ClassRule
//	public static EmbeddedActiveMQBroker broker = new EmbeddedActiveMQBroker();

	@Autowired
	private Sender sender;

	@Autowired
	private ListenerService receiver;

	private static final String MESSAGE = "Hello SQS2";

	@Test
	public void testReceive() throws Exception {
		sender.send("Test-Queue", MESSAGE);
//		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

	@Test
	public void testReceiveListener1() throws Exception {
		sender.sendListener1("Test-Queue", MESSAGE);

//		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

	@Test
	public void testReceiveListener2() throws Exception {
		sender.sendListener2("Test-Queue", MESSAGE);
//		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

}
