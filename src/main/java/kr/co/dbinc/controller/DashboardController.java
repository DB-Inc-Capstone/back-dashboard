package kr.co.dbinc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.co.dbinc.dto.ResponseDTO;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${API_GATEWAY_URL}")
	private String url;
	
	/**
	 * 전체 작업 진행 현황 확인
	 */
	@GetMapping("/totalwork")
	//ResponseEntity<ResponseDTO>
	public String getTotalWork() {
		
		System.out.println(url);
		
		String response = restTemplate.getForObject(url + "/work", String.class);
		
		//{"message":"조회 결과입니다.","success":true,"workinfos":[{"workID":1,"parentID":null,"workTitle":"test_title","workContent":"test_content","workState":1,"startDate":1682035200000,"finishDate":1706140800000}]}

		/*
		 * 0 : 할일
		 * 1 : 진행 중
		 * 2 : 완료
		 */
		
		
		System.out.println(response);
		
		return response;
	}
	
	/**
	 * 작업자 단위 작업 진행 현황 확인
	 */
	@GetMapping("/totalwork/worker/{workerId}")
	public ResponseEntity<ResponseDTO> getTotalWorkByWorker() {
		
		
		
		return null;
	}
	
	/**
	 * 전체 이슈 진행 현황 확인
	 */
	@GetMapping("/totalissue")
	public ResponseEntity<ResponseDTO> getTotalIssue() {
		
		
		return null;
	}
	
	/**
	 * 작업자 단위 이슈 진행 현황 확인
	 */
	@GetMapping("/totalissue/worker/{workerId}")
	public ResponseEntity<ResponseDTO> getTotalIssueByWorker() {
		
		
		
		return null;
	}
}
