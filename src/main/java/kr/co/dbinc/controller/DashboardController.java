package kr.co.dbinc.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import kr.co.dbinc.dto.IssueVO;
import kr.co.dbinc.dto.ResponseDTO;
import kr.co.dbinc.dto.ResponseDTO_receive;
import kr.co.dbinc.dto.WorkVO;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final Logger logger = LogManager.getFormatterLogger(this.getClass());
	
	@Value("${API_GATEWAY_URL}")
	private String url;
	
	/**
	 * 전체 작업 진행 현황 확인
	 */
	@GetMapping("/totalwork")
	//ResponseEntity<ResponseDTO>
	public ResponseEntity<ResponseDTO> getTotalWork() {
		
		logger.info("getTotalWork 호출");
		
		ResponseDTO response = new ResponseDTO();
		System.out.println(url);
		
		ResponseDTO_receive response_receive = restTemplate.getForObject(url + "/work", ResponseDTO_receive.class);
		
		//{"message":"조회 결과입니다.","success":true,"workinfos":[{"workID":1,"parentID":null,"workTitle":"test_title","workContent":"test_content","workState":1,"startDate":1682035200000,"finishDate":1706140800000}]}

		/*
		 * 0 : 할일
		 * 1 : 진행 중
		 * 2 : 완료
		 */
		
		//System.out.println(response);
		
		int totalWorkCount = response_receive.getWorkinfos().size();
		
		int doneWorkCount = (int) response_receive.getWorkinfos().stream()
	            .filter(workInfo -> workInfo.getWorkState() == 2)
	            .count();
		
		if(totalWorkCount != 0) {
			response.message = "순서대로 전체작업과 완료된 작업입니다.";
			response.setTotalWork(totalWorkCount);
	        response.setDoneWork(doneWorkCount);		
	        return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.message = "작업이 존재하지 않습니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	/**
	 * 작업자 단위 작업 진행 현황 확인
	 */
	@GetMapping("/totalwork/worker/{workerID}")
	public ResponseEntity<ResponseDTO> getTotalWorkByWorker(@PathVariable Long workerID) {
		
		logger.info("getTotalWorkByWorker 호출");
		
		ResponseDTO response = new ResponseDTO();
		
		ResponseDTO_receive response_receive = restTemplate.getForObject(url + "/work", ResponseDTO_receive.class);
		
		List<WorkVO> filteredWorkInfos = response_receive.getWorkinfos().stream()
		        .filter(workInfo -> workerID.equals(workInfo.getWorkerID()))
		        .collect(Collectors.toList());
		
		// 전체 작업 개수 계산
	    int totalWorkCount = filteredWorkInfos.size();

	    // 완료된 작업 (workstate = 2) 개수 계산
	    int doneWorkCount = (int) filteredWorkInfos.stream()
	        .filter(workInfo -> workInfo.getWorkState() == 2)
	        .count();		

	    if(totalWorkCount != 0) {
			response.message = "순서대로 전체작업과 완료된 작업입니다.";
			response.setTotalWork(totalWorkCount);
	        response.setDoneWork(doneWorkCount);		
	        return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.message = "작업이 존재하지 않습니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
		
		
	
	
	/**
	 * 전체 이슈 진행 현황 확인
	 */
	@GetMapping("/totalissue")
	public ResponseEntity<ResponseDTO> getTotalIssue() {
		
		logger.info("getTotalIssue 호출");
		
		ResponseDTO response = new ResponseDTO();
		System.out.println(url);
		
		ResponseDTO_receive response_receive = restTemplate.getForObject(url + "/work/issue", ResponseDTO_receive.class);
		
		//{"message":"조회 결과입니다.","success":true,"workinfos":[{"workID":1,"parentID":null,"workTitle":"test_title","workContent":"test_content","workState":1,"startDate":1682035200000,"finishDate":1706140800000}]}

		/*
		 * 0 : 할일
		 * 1 : 진행 중
		 * 2 : 완료
		 */
		
		//System.out.println(response);
		
		int totalIssueCount = response_receive.getIssueinfos().size();
		
		int doneIssueCount = (int) response_receive.getIssueinfos().stream()
	            .filter(issueInfo -> issueInfo.getIssueState() == 2)
	            .count();
		
		if(totalIssueCount != 0) {
			response.message = "순서대로 전체이슈와 완료된 이슈입니다.";
			response.setTotalIssue(totalIssueCount);
	        response.setDoneIssue(doneIssueCount);		
	        return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.message = "이슈가 존재하지 않습니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	
	/**
	 * 작업자 단위 이슈 진행 현황 확인
	 */
	@GetMapping("/totalissue/worker/{workerID}")
	public ResponseEntity<ResponseDTO> getTotalIssueByWorker(@PathVariable Long workerID) {
		
		logger.info("getTotalIssueByWorker 호출");
		
		ResponseDTO response = new ResponseDTO();
		
		ResponseDTO_receive response_receive = restTemplate.getForObject(url + "/work/issue", ResponseDTO_receive.class);
		
		List<IssueVO> filteredIssueInfos = response_receive.getIssueinfos().stream()
		        .filter(issueInfo -> workerID.equals(issueInfo.getWorkerID()))
		        .collect(Collectors.toList());
		
		// 전체 이슈 개수 계산
	    int totalIssueCount = filteredIssueInfos.size();

	    // 완료된 이슈 (issuestate = 2) 개수 계산
	    int doneIssueCount = (int) filteredIssueInfos.stream()
	        .filter(issueInfo -> issueInfo.getIssueState() == 2)
	        .count();		

	    if(totalIssueCount != 0) {
			response.message = "순서대로 전체이슈와 완료된 이슈입니다.";
			response.setTotalIssue(totalIssueCount);
	        response.setDoneIssue(doneIssueCount);		
	        return new ResponseEntity<>(response,HttpStatus.OK);
		}
		else {
			response.message = "이슈가 존재하지 않습니다.";
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}		
		
}
