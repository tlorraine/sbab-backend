package se.sbab.sbabbackend.integration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class SLHttpClient {

	@Value("${sl.api-key}")
	private String apiKey;
	private final RestTemplate restTemplate;


	public BusJourResponse getBusJour() {
		try {
			ResponseEntity<BusJourResponse> res = restTemplate.getForEntity(String.format("https://api.sl.se/api2/LineData.json?model=jour&key=%s&DefaultTransportModeCode=BUS", apiKey), BusJourResponse.class);
			BusJourResponse body = res.getBody();
			return body;
		} catch (Exception e) {
			log.error("Failed to fetch bus jour", e);
			return new BusJourResponse();
		}
	}

	public BusStopResponse getBusStops() {
		try {
			ResponseEntity<BusStopResponse> res = restTemplate.getForEntity("https://api.sl.se/api2/LineData.json?model=stop&key=04c4f00427d14ba6ba81b63419ec6efc&DefaultTransportModeCode=BUS", BusStopResponse.class);
			BusStopResponse body = res.getBody();
			return body;
		} catch (Exception e) {
			log.error("Failed to fetch bus stops", e);
			return new BusStopResponse();
		}
	}

}
