package se.sbab.sbabbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.sbabbackend.service.BusLineApiResponse;
import se.sbab.sbabbackend.service.BusService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/busline", produces = "application/json;charset=UTF-8")
public class BusLineController {

	private final BusService busService;

	@GetMapping("/top10")
	public ResponseEntity<BusLineApiResponse> getTopBusLines() {
		BusLineApiResponse res = busService.getTopBusLines();
		return ResponseEntity.ok(res);
	}

}
