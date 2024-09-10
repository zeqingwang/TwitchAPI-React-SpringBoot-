package com.wzq.twitch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class TwitchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitchApplication.class, args);
	}

}
