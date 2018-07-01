package top.scarboroughcoral.vote.model.result;

import java.util.List;
import java.util.Map;

import static top.scarboroughcoral.vote.util.Constant.VOTE_STATUS_NOT_PASS;
import static top.scarboroughcoral.vote.util.Constant.VOTE_STATUS_PASS;

/**
 * Created by Hw Du on 2018/6/20.
 */
public class VoteLineResult {
    Integer lineNo;
    List<String> headers;
    String result;
    Map<Integer,Integer> resultDetailMap;

    public VoteLineResult(Map<Integer,Integer> resultDetailMap,Integer lineNo,List<String> headers) {
        this.lineNo = lineNo;
        this.headers = headers;
        this.resultDetailMap = resultDetailMap;
    }

    public VoteLineResult(Object obj) {

    }

    public VoteLineResult( Map<Integer, Integer> resultDetailMap, Integer lineNo, List<String> headers,
                           Integer voteNum, Integer pass_rate_mother, Integer pass_rate_child, Boolean isAbstain,
                           Integer isContain) {
        //0代表同意，1代表反对，2代表弃权
        this.lineNo = lineNo;
        this.headers = headers;
        resultDetailMap.putIfAbsent(1, 0);
        resultDetailMap.putIfAbsent(-1, 0);
        if (isAbstain){
            //如果有弃权票
            resultDetailMap.putIfAbsent(0, 0);
        }
        if (!isContain.equals(0)) {
            if (voteNum != 0 && resultDetailMap.get(1) * 1.0 / pass_rate_mother >= pass_rate_child * 1.0 / pass_rate_mother) {
                this.result = VOTE_STATUS_PASS;
            } else {
                this.result = VOTE_STATUS_NOT_PASS;
            }
        }
        if (isContain.equals(0)) {
            if (voteNum != 0 && resultDetailMap.get(1) * 1.0 / pass_rate_mother > pass_rate_child * 1.0 / pass_rate_mother) {
                this.result = VOTE_STATUS_PASS;
            } else {
                this.result = VOTE_STATUS_NOT_PASS;
            }
        }
        this.resultDetailMap = resultDetailMap;
    }

    public Integer getLineNo() {
        return lineNo;
    }

    public void setLineNo(Integer lineNo) {
        this.lineNo = lineNo;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Map<Integer, Integer> getResultDetailMap() {
        return resultDetailMap;
    }

    public void setResultDetailMap(Map<Integer, Integer> resultDetailMap) {
        this.resultDetailMap = resultDetailMap;
    }
}
