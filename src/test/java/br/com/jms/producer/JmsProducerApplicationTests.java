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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JmsProducerApplicationTests {

	@Autowired
	private SenderSQS sender;

	@Autowired
	private ReceiverSQS receiver;

	private static final String MESSAGE = "Hello SQS";

	AWSCredentials credentials = null;

//	@Test
//	public void testReceive() throws Exception {
//		sender.send("Test-Queue", "Hello SQS!");
//		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//
//		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
//	}

	@Test
	public void test() {
		credentials = new ProfileCredentialsProvider().getCredentials();

		AmazonSQS sqs = AmazonSQSClientBuilder.standard()
			.withRegion(Regions.US_EAST_2)
			.build();

		String myQueueUrl = "Test-Queue";

		sqs.sendMessage(new SendMessageRequest(myQueueUrl, MESSAGE));

		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);

		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();

		Assert.assertEquals(1, messages.size());
		Assert.assertEquals(MESSAGE, messages.get(0).getBody());

		sqs.deleteMessage(new DeleteMessageRequest(myQueueUrl, messages.get(0).getReceiptHandle()));
	}
}
