package com.example.MQ;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import zipkin2.Call;

import zipkin2.codec.Encoding;
import zipkin2.reporter.AsyncReporter;
import zipkin2.reporter.Reporter;
import zipkin2.reporter.Sender;
import zipkin2.reporter.amqp.RabbitMQSender;
import org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration;

@Configuration
public class TracingReportToRabbitConfiguration {
/*
	@Bean(ZipkinAutoConfiguration.REPORTER_BEAN_NAME)
	    Reporter<zipkin2.Span> myReporter() {
	        return AsyncReporter.create(mySender());
	    }

	    @Bean(ZipkinAutoConfiguration.SENDER_BEAN_NAME)
	    MySender mySender() {
	        return new MySender();
	    }
	    
	    static class MySender extends Sender {

	        private boolean spanSent = false;

	        boolean isSpanSent() {
	            return this.spanSent;
	        }

	        @Override
	        public Encoding encoding() {
	            return Encoding.JSON;
	        }

	        @Override
	        public int messageMaxBytes() {
	            return Integer.MAX_VALUE;
	        }

	        @Override
	        public int messageSizeInBytes(List<byte[]> encodedSpans) {
	            return encoding().listSizeInBytes(encodedSpans);
	        }

	        @Override
	        public Call<Void> sendSpans(List<byte[]> encodedSpans) {
	            this.spanSent = true;
	            return Call.create(null);
	        }
	    }*/
}
