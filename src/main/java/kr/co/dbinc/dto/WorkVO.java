package kr.co.dbinc.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;


@Data
public class WorkVO {

	private Integer workID;
	private Integer parentID;
	private String workTitle;
	private String workContent;
	private Integer workState;
	private Long workerID;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date finishDate;	
}



