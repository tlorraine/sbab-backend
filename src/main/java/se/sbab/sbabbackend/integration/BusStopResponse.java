package se.sbab.sbabbackend.integration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class BusStopResponse {

	@JsonProperty("StatusCode")
	private int statusCode;

	@JsonProperty("Message")
	private String message;

	@JsonProperty("ExecutionTime")
	private int executionTime;

	@JsonProperty("ResponseData")
	private ResponseData responseData;

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ResponseData {

		@JsonProperty("Version")
		private String version;

		@JsonProperty("Type")
		private String type;

		@JsonProperty("Result")
		@Builder.Default
		private List<ResponseDataResult> result = new ArrayList<>();

	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@Builder
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ResponseDataResult {

		@JsonProperty("StopPointNumber")
		private String stopPointNumber;

		@JsonProperty("StopPointName")
		private String stopPointName;

		@JsonProperty("StopAreaNumber")
		private String stopAreaNumber;

		@JsonProperty("LocationNorthingCoordinate")
		private String locationNorthingCoordinate;

		@JsonProperty("LocationEastingCoordinate")
		private String locationEastingCoordinate;

		@JsonProperty("ZoneShortName")
		private String zoneShortName;

		@JsonProperty("StopAreaTypeCode")
		private String stopAreaTypeCode;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
		@JsonProperty("LastModifiedUtcDateTime")
		private LocalDateTime lastModifiedUtcDateTime;

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
		@JsonProperty("ExistsFromDate")
		private LocalDateTime existsFromDate;

	}

}
