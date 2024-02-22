package com.sn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sn.domain.Book;
import com.sn.domain.LibraryEvent;
import com.sn.producer.LibraryEventProducer;

@RestController
public class LibraryEventConroller {
	
	
	private static Logger log= LoggerFactory.getLogger(LibraryEventConroller.class);
	
	//@Value("${mydata.name}")
	String str;
	
//	@Value("${logging.level.com.sn}")
	String loglevel;
	
	
	
	@Value("${spring.jmx.enabled}")
	String jmxEnabled;
	
	@Autowired
	private LibraryEventProducer libraryEventProducer;
	
	@PostMapping("v1/libraryevent")
	public ResponseEntity<LibraryEvent> postLibrarEvent(@RequestBody LibraryEvent libraryEvent){
		
		try {
			libraryEventProducer.sendLibraryEventSync(libraryEvent);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
		
	}

	@GetMapping("v1/test")
	public LibraryEvent testEvent() {
		log.debug("******* Testing DEBUG *****");
		log.info("******* Testing INFO ***** str {} ",str);
		log.info("******* Current Log Level ***** loglevel {} ",loglevel);
		
		log.info(" ***** JMX Enabled ** {} ", System.getProperty("spring.jmx.enabled"));
		log.info(" ***** JMX Enabled ******* {} ", System.getenv("spring.jmx.enabled"));
		
		log.info(" ***** JMX Enabled *2222* {} ", jmxEnabled);
		
		log.info(" ***** Log Enabled ** {} ", System.getProperty("logging.level.com.sn"));
		
		log.info("***************** END *******************");
		Integer id=111;
		int bkid=2;
		Book bk=new Book(bkid,"Sei Samay", "Sunil");
		LibraryEvent libraryEvent= new LibraryEvent();
		libraryEvent.setLibrarryEventId(id);
		libraryEvent.setBook(bk);
		
		
		return libraryEvent;
	}
}
