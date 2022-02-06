package com.crash.sqs.sqs;


import com.crash.sqs.domain.Pessoa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/consumer")
public class SqsController {


    private static final String QUEU_PREFIX = "MyAWSPlanetSQS";
    AwsBasicCredentials aws = AwsBasicCredentials.create(
            "teste",
            "teste"
    );
    SqsClient SQS_CLIENT = SqsClient.builder()
            .region(Region.SA_EAST_1)
            .credentialsProvider(StaticCredentialsProvider.create(aws))
            .endpointOverride(URI.create("http://localhost:4566"))
            .build();

    @PostMapping("/send")
    public void sendMessage(@RequestBody Pessoa pessoaRequest) throws JsonProcessingException {
        String pessoaBody = new ObjectMapper().writeValueAsString(pessoaRequest);
        SendMessageRequest messageRequest = SendMessageRequest.builder()
                .queueUrl("http://localhost:4566/000000000000/fila-local")
                .messageBody(pessoaBody)
                .build();
        SQS_CLIENT.sendMessage(messageRequest);
    }

    @GetMapping("/receive/{numberMessage}")
    public ResponseEntity<List<Pessoa>> receiveMessagesWithoutDelete(@PathVariable(required = false) Integer numberMessage) throws JsonProcessingException {
        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl("http://localhost:4566/000000000000/fila-local")
                .maxNumberOfMessages(numberMessage)
                .build();
        List<Message> receivedMessages = SQS_CLIENT.receiveMessage(receiveMessageRequest).messages();

        for (Message messageRequest : receivedMessages) {
            DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder()
                    .queueUrl("http://localhost:4566/000000000000/fila-local")
                    .receiptHandle(messageRequest.receiptHandle())
                    .build();
            SQS_CLIENT.deleteMessage(deleteMessageRequest);
        }
        List<String> messageBody = receivedMessages.stream().map(obj -> obj.body()).collect(Collectors.toList());
        List<Pessoa> pessoas = new ObjectMapper().readValue(messageBody.toString(), new TypeReference<>(){});
        return ResponseEntity.ok().body(pessoas);
    }
}
