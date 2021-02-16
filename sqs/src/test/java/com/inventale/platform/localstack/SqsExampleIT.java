package com.inventale.platform.localstack;

import cloud.localstack.awssdkv2.TestUtils;
import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.inventale.platform.utils.SystemEnvHostNameResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// Annotations below are required for local run and to initiate all necessary params
// @RunWith(LocalstackTestRunner) is used for junit4
// @ExtendWith(LocalstackDockerExtension) is used for junit5
@ExtendWith(LocalstackDockerExtension.class)
@LocalstackDockerProperties(
        services = "sqs",
        hostNameResolver = SystemEnvHostNameResolver.class
)
class SqsExampleIT {

    private static final String LOCALSTACK_TEST_QUEUE = "localstack-test-queue";
    private static final String LOCALSTACK_TEST_MESSAGE = "Test message body";

    @Test
    void testSqs() throws ExecutionException, InterruptedException {
        SqsAsyncClient sqs = TestUtils.getClientSQSAsyncV2();

        // create queue
        CreateQueueResponse createQueueResponse = sqs.createQueue(CreateQueueRequest
                .builder()
                .queueName(LOCALSTACK_TEST_QUEUE)
                .build()
        ).get();
        assertEquals(200, createQueueResponse.sdkHttpResponse().statusCode());
        String queueUrl = createQueueResponse.queueUrl();

        // send message
        SendMessageResponse sendMessageResponse = sqs.sendMessage(SendMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .messageBody(LOCALSTACK_TEST_MESSAGE)
                .build()
        ).get();
        assertNotNull(sendMessageResponse.messageId());

        ReceiveMessageResponse receiveMessageResponse = sqs.receiveMessage(ReceiveMessageRequest
                .builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(1)
                .build()
        ).get();
        List<Message> messages = receiveMessageResponse.messages();
        assertEquals(1, messages.size());
        assertEquals(LOCALSTACK_TEST_MESSAGE, messages.get(0).body());

        // clear queue
        sqs.purgeQueue(PurgeQueueRequest
                .builder()
                .queueUrl(queueUrl)
                .build()
        );

    }

}
