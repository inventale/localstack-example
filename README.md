# localstack_example

[LocalStack](https://github.com/localstack/localstack) provides an easy-to-use test/mocking framework for developing Cloud applications.
It's helpful to test AWS services locally or on Build Agent because it works based on mocks and we don't have to pay for Amazon Services.

# How to use
1. You have to add dependencies on:
   1. `cloud.localstack:localstack-utils`
   2. aws sdk. Localstack supports aws sdk v1 and v2 (at this example aws sdk v2 was used)
      1. aws sdk v1 usually has packages starting with `com.amazonaws:aws-java-sdk`
      2. aws sdk v2 usually has packages starting with `software.amazon.awssdk:`
2. Localstack runs docker containers with AWS mocks locally.
3. Localstack containers can be run from tests as follows:
   1. Under junit4 with marking test with `@RunWith(LocalstackTestRunner)`
   2. Under junit5 with marking test with `@ExtendWith(LocalstackDockerExtension.class)`
4. At this project there are 2 IT tests with Localstack
   1. `S3ExampleIT`
   2. `SqsExampleIT`
       1. These tests can be run locally 
       2. Or on Gitlab runner as part of `integrationTest` task.
          1. See `it` task in `gitlab.ci`
          3. Gitlab schedule `Run IT test` -> https://gitlab.inventale.com/platform/templates/localstack-example/pipeline_schedules/55/edit 
5. Localstack tests cannot be run concurrently - that's why there is special ordering mechanism for gradle `integrationTest` tasks. See details at:
   1. `s3/build.gradle.kts:31`
   2. `sqs/build.gradle.kts:29`
   3. sorting at `build.gradle.kts:30`

# Troubleshooting
If your testcase failed then it can happen that Docker container is still alive and you can't run test again
1. In this case please find your container by typing `docker container ls` in terminal
2. Get Container ID and execute `docker container rm {CONTAINER ID} -f`, please replace `{CONTAINER ID}` with real container id