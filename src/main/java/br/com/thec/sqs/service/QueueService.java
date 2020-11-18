package br.com.thec.sqs.service;


import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.google.gson.Gson;

import br.com.thec.cartao.request.CartaoRequest;
import br.com.thec.sqs.response.MessageResponse;

public class QueueService {
	
	public static MessageResponse send(final CartaoRequest cartaoRequest) {
		
		String queueUrl = loadConfiguration().getQueueUrl("cartao-queue").getQueueUrl();
		
		Gson gson = new Gson();
		
		SendMessageRequest request = new SendMessageRequest().withQueueUrl(queueUrl)
				.withMessageBody(gson.toJson(cartaoRequest))
				.withDelaySeconds(5);
		
		SendMessageResult response = loadConfiguration().sendMessage(request);
		
		return MessageResponse.builder()
				.id(response.getMessageId()).build();
	}
	
	public static AmazonSQS loadConfiguration() {
		
		AWSCredentialsProvider provider = new DefaultAWSCredentialsProviderChain();
		provider.getCredentials().getAWSAccessKeyId();
		provider.getCredentials().getAWSSecretKey();
		System.out.println(provider.getCredentials().getAWSAccessKeyId());
		System.out.println(provider.getCredentials().getAWSSecretKey());
		return AmazonSQSClientBuilder.standard()
				 .withRegion(Regions.EU_WEST_1)
				 .withCredentials(provider)
				 .build();
	}
}