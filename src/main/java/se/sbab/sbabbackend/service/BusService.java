package se.sbab.sbabbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import se.sbab.sbabbackend.integration.BusJourResponse;
import se.sbab.sbabbackend.integration.BusStopResponse;
import se.sbab.sbabbackend.integration.SLHttpClient;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusService {

	private final SLHttpClient slHttpClient;

	@Cacheable("buslines")
	public BusLineApiResponse getTopBusLines() {

		BusJourResponse busJourResponse = slHttpClient.getBusJour();
		BusStopResponse busStopResponse = slHttpClient.getBusStops();

		// map busstops to map with key = stopPointNumber, value = ResponseDataResult
		Map<String, BusStopResponse.ResponseDataResult> busStopMap = busStopResponse.getResponseData().getResult().stream()
				.collect(Collectors.toMap(BusStopResponse.ResponseDataResult::getStopPointNumber, row -> row));

		List<BusJourResponse.ResponseDataResult> list = busJourResponse.getResponseData().getResult().stream()
				.filter(row -> row.getDirectionCode().equals("1"))
				.collect(Collectors.toList());

		Map<String, List<String>> groupedByLineNumber = list.stream()
				.collect(
						Collectors.groupingBy(
								BusJourResponse.ResponseDataResult::getLineNumber,
								Collectors.mapping(BusJourResponse.ResponseDataResult::getJourneyPatternPointNumber, Collectors.toList())
						)
				);

		Map<String, List<String>> top10GroupedByLineNumber = groupedByLineNumber.entrySet().stream()
				.sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
				.limit(10)
				.collect(Collectors.toMap(
						Map.Entry::getKey, Map.Entry::getValue,
						(e1, e2) -> e1,
						LinkedHashMap::new
				));

		// Map response
		List<BusLineApiResponse.BusLine> busLines = new ArrayList<>();

		for (Map.Entry<String, List<String>> entry : top10GroupedByLineNumber.entrySet()) {

			List<BusLineApiResponse.BusStop> busStops = new ArrayList<>();

			for (String stopPointNumber : entry.getValue()) {

				BusStopResponse.ResponseDataResult stopPoint = busStopMap.get(stopPointNumber);

				busStops.add(BusLineApiResponse.BusStop.builder()
						.stopPointNumber(stopPointNumber)
						.stopPointName(stopPoint.getStopPointName())
						.existFromDate(stopPoint.getExistsFromDate())
						.build());

			}

			busLines.add(
					BusLineApiResponse.BusLine.builder()
							.lineNumber(entry.getKey())
							.totalBusStops(busStops.size())
							.busStops(busStops)
							.build()
			);

		}

		Collections.sort(busLines, Comparator.comparingInt(BusLineApiResponse.BusLine::getTotalBusStops).reversed());

		return BusLineApiResponse.builder()
				.busLines(busLines)
				.build();
	}

}
