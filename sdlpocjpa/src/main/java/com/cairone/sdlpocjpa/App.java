package com.cairone.sdlpocjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.sdl.odata.service.ODataServiceConfiguration;

@SpringBootApplication
@Import({ ODataServiceConfiguration.class })
public class App 
{
	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(App.class);
	}
	
    public static void main( String[] args ) {
    	SpringApplication.run(App.class, args);
    }
}
