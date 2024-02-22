package com.sn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringK8sConfig1Application {
	
	@Value("${logging.level.com.sn}")
	static String logValue;
	
	@Value("${spring.jmx.enabled}")
	static String jmxValue;
	

	public static void main(String[] args) {

		log.info(" ***** JMX Enabled ** {} ", System.getProperty("spring.jmx.enabled"));
		log.info(" ***** JMX Enabled Property {} ", jmxValue);
		
		log.info(" ***** Log Enabled ** {} ", System.getProperty("logging.level.com.sn"));
		log.info(" ***** JMX Enabled Property {} ", logValue);

		SpringApplication.run(SpringK8sConfig1Application.class, args);
		
	
	}

}
