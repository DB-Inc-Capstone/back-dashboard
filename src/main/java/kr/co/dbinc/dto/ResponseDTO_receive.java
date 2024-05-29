package kr.co.dbinc.dto;


import java.util.List;

import lombok.Data;



@Data
public class ResponseDTO_receive {
    private String message;
    private boolean success;
    private List<WorkVO> workinfos;
    private List<IssueVO> issueinfos;
}