package com.inventale.platform.localstack;

import cloud.localstack.awssdkv2.TestUtils;
import cloud.localstack.docker.LocalstackDockerExtension;
import cloud.localstack.docker.annotation.LocalstackDockerProperties;
import com.inventale.platform.utils.SystemEnvHostNameResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;
import software.amazon.awssdk.services.s3.model.CreateBucketResponse;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


// Annotations below are required for local run and to initiate all  necessary params
// @RunWith(LocalstackTestRunner) is used for junit4
// @ExtendWith(LocalstackDockerExtension) is used for junit5
@ExtendWith(LocalstackDockerExtension.class)
@LocalstackDockerProperties(
        services = "s3",
        hostNameResolver = SystemEnvHostNameResolver.class
)
class S3ExampleIT {
    private static final String BUCKET_TEST_NAME = "localstack-test";

    @Test
    void testS3() throws ExecutionException, InterruptedException {
        S3AsyncClient s3Async = TestUtils.getClientS3AsyncV2();

        // check buckets
        ListBucketsResponse listBucketsResponse = s3Async.listBuckets().get();
        assertEquals(0, listBucketsResponse.buckets().size());

        // create bucket and check it
        CreateBucketResponse bucketResponse = s3Async.createBucket(CreateBucketRequest
                .builder()
                .bucket(BUCKET_TEST_NAME)
                .build()
        ).get();
        assertEquals(200, bucketResponse.sdkHttpResponse().statusCode());

        listBucketsResponse = s3Async.listBuckets().get();
        assertEquals(1, listBucketsResponse.buckets().size());
        assertNotNull(
                listBucketsResponse
                        .buckets()
                        .stream()
                        .filter(b -> b.name().equals(BUCKET_TEST_NAME))
                        .findFirst()
                        .orElse(null)
        );
    }

}
