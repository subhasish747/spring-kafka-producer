package com.sn.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sn.domain.LibraryEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LibraryEventProducer {
	
//	private static Logger log= LoggerFactory.getLogger(LibraryEventProducer.class);

	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;

	@Autowired
	ObjectMapper objectMapper;

	String topicName="library-events";
			
	public void sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {

		String resultJson = objectMapper.writeValueAsString(libraryEvent);
		log.info(" sending message to kafka {} ", resultJson);
		kafkaTemplate.sendDefault(libraryEvent.getLibrarryEventId(), resultJson);

	}
	
	public void sendLibraryEventSync(LibraryEvent libraryEvent) throws JsonProcessingException {

		String resultJson = objectMapper.writeValueAsString(libraryEvent);
		log.info(" sending message to kafka {} ", resultJson);
		kafkaTemplate.send(buildProducerRecored(libraryEvent.getLibrarryEventId(), resultJson));

	}

	public ProducerRecord<Integer,String> buildProducerRecored(Integer eventId, String resultJson) {
		return new ProducerRecord<>(topicName, null,eventId, resultJson, null);
	}
	
}
