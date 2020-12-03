package br.com.thec.sqs.service;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.google.gson.Gson;

import br.com.thec.cartao.event.CartaoCriadoEvent;
import br.com.thec.sqs.response.MessageResponse;

public class QueueService {
	
	public static MessageResponse send(final CartaoCriadoEvent cartaoCriadoEvent) {
		
		String queueUrl = loadConfiguration().getQueueUrl("cartao-queue").getQueueUrl();
		
		Gson gson = new Gson();
		String message = gson.toJson(cartaoCriadoEvent.getRecurso());
		
		SendMessageRequest request = new SendMessageRequest().withQueueUrl(queueUrl)
				.withMessageBody(gson.toJson(message))
				.withDelaySeconds(5);
		
		SendMessageResult response = loadConfiguration().sendMessage(request);
		
		return MessageResponse.builder()
				.id(response.getMessageId()).build();
	}
	
	public static AmazonSQS loadConfiguration() {
		
		AWSCredentialsProvider provider = new DefaultAWSCredentialsProviderChain();
		provider.getCredentials().getAWSAccessKeyId();
		provider.getCredentials().getAWSSecretKey();
		return AmazonSQSClientBuilder.standard()
				 .withRegion(Regions.EU_WEST_1)
				 .withCredentials(provider)
				 .build();
	}
	
	public static MessageResponse sendToDeadLetter(final CartaoCriadoEvent cartaoCriadoEvent) {
		
		String queueUrl = loadConfiguration().getQueueUrl("cartao-dead-letter").getQueueUrl();
		
		Gson gson = new Gson();
		String message = gson.toJson(cartaoCriadoEvent.getRecurso());
		
		SendMessageRequest request = new SendMessageRequest().withQueueUrl(queueUrl)
				.withMessageBody(gson.toJson(message))
				.withDelaySeconds(5);
		
		SendMessageResult response = loadConfiguration().sendMessage(request);
		
		return MessageResponse.builder()
				.id(response.getMessageId()).build();
	}
	
}