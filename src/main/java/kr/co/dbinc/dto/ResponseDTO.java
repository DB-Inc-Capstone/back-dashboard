package kr.co.dbinc.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
	public String message;
	public Integer totalWork;
	public Integer doneWork;
	public Integer totalIssue;
	public Integer doneIssue;
}
