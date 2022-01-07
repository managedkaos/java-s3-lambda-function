package com.amazonaws.lambda.demo;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LambdaFunctionHandler implements RequestHandler<S3Event, String> {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private AmazonS3 s3 = AmazonS3ClientBuilder.standard().build();

    public LambdaFunctionHandler() {}

    // Test purpose only.
    LambdaFunctionHandler(AmazonS3 s3) {
        this.s3 = s3;
    }

    @Override
    public String handleRequest(S3Event event, Context context) {
        LambdaLogger logger = context.getLogger();
        String response = "200 OK";
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass());
        return response;
        
		/*
		 * 
		 * Get the object from the event and show its content type String bucket =
		 * event.getRecords().get(0).getS3().getBucket().getName(); String key =
		 * event.getRecords().get(0).getS3().getObject().getKey(); try { S3Object
		 * response = s3.getObject(new GetObjectRequest(bucket, key)); String
		 * contentType = response.getObjectMetadata().getContentType();
		 * context.getLogger().log("CONTENT TYPE: " + contentType); return "Yes"; }
		 * catch (Exception e) { e.printStackTrace();
		 * context.getLogger().log(String.format(
		 * "Error getting object %s from bucket %s. Make sure they exist and" +
		 * " your bucket is in the same region as this function.", key, bucket)); throw
		 * e; }
		 */    }
}
