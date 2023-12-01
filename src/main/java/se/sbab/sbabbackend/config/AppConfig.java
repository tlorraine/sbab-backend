package se.sbab.sbabbackend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@RequiredArgsConstructor
@Configuration
public class AppConfig {

	@Bean
	public RestTemplate restTemplate() {
		// add default header accept-enconding: gzip
		RestTemplate restTemplate = new RestTemplateBuilder()
				.defaultHeader("Accept-Encoding", "gzip, deflate")
				.build();

		return restTemplate;
	}


}
