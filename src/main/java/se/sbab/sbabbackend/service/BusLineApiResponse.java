package se.sbab.sbabbackend.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusLineApiResponse {

	@Builder.Default
	private List<BusLine> busLines = new ArrayList<>();

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class BusLine {

		private String lineNumber;

		private int totalBusStops;

		@Builder.Default
		private List<BusStop> busStops = new ArrayList<>();

	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class BusStop {

		private String stopPointNumber;
		private String stopPointName;
		private LocalDateTime existFromDate;

	}

}
