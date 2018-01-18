package br.com.jms.producer;

import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ReceiverSQS {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReceiverSQS.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @JmsListener(destination = "Test-Queue")
    public void createThumbnail(String message) {
        LOGGER.info("received message='{}'", message);
        latch.countDown();
    }

}