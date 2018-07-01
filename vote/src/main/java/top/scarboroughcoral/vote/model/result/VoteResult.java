package top.scarboroughcoral.vote.model.result;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Hw Du on 2018/6/20.
 */
public class VoteResult {
    private Integer voteId;
    private List<String> usersNotVote;
    private Integer receivedNum;
    private List<String> headers;
    private List<VoteLineResult> line;

    public VoteResult(Integer voteId, List<String> usersNotVote, Integer receivedNum, List<VoteLineResult> line) {
        this.voteId = voteId;
        this.usersNotVote = usersNotVote;
        this.receivedNum = receivedNum;
        this.line = line;
    }

    /**
     *
     * @param objList
     * @param voteBrief
     * @param userVoted
     */
    public VoteResult(List<Object> objList, Object voteBrief, List<String> userVoted) {
        Object[] objArr = (Object[]) voteBrief;
        this.voteId = (Integer) objArr[0];
        this.usersNotVote = userVoted;
        Boolean isAbstain = (Boolean) objArr[2];        //是否可以弃权
        Integer pass_rate_parent = (Integer) objArr[4]; //通过率的分母
        Integer pass_rate_child = (Integer) objArr[5];  //通过率的分子 这三个属性在这获取，然后传到每个行里，
        Integer row_number = (Integer) objArr[6];
        Integer is_contain = (Integer) objArr[7];
        List<String> tempHeaders = new ArrayList<>();
        Object[] tempObj;
        this.line = new ArrayList<>();

        this.receivedNum = userVoted.size();

        Map<Integer,Integer> resultMap = new HashMap<>();
        Object[] firstRow = ((Object[])objList.get(0));
        Integer startIndex = 0;                         //寻找第一个不是表头的列
        for (int i=0;i<objList.size();i++) {
            tempObj = (Object[]) objList.get(i);
            if (firstRow[1].equals(tempObj[1])){                    //由于表头列的result一定为null，所以以此为判断
                startIndex += 1;
                tempHeaders.add((String)tempObj[6]);
            } else{
                break;
            }
        }
        this.headers = tempHeaders;
        tempHeaders = new ArrayList<>();
        tempObj = (Object[]) objList.get(startIndex);   //startIndex之前为表头，不处理，所以从startIndex开始
        VoteLineResult voteLineResult;                  //每行的结果存到一个VoteLineResult中
        for (int i=startIndex;i<objList.size();i++){
            Object obj = objList.get(i);
            objArr = (Object[]) obj;
            //因为结果ID不同或行ID不同，所以说明起了新的一组表头
            if (! objArr[1].equals(tempObj[1])){        //因为行ID不同，所以说明起了新的一行
                voteLineResult = new VoteLineResult(resultMap, (Integer)tempObj[2], tempHeaders, this.receivedNum,
                        pass_rate_parent, pass_rate_child, isAbstain, is_contain);
                line.add(voteLineResult);
                resultMap = new HashMap<>();
            }
            if ((objArr[4] != null && ! objArr[4].equals(tempObj[4])) || ! objArr[1].equals(tempObj[1])){
                tempHeaders = new ArrayList<>();
            }
            tempHeaders.add((String)objArr[6]);
            if (objArr[4] != null){
                //如果objArr[4]是空，说明这一行还没有被投过票，但是只要投票，那么所有行都要被投，那么说明没有任何一行被投过票，
                //于是这里我们不记录当前行的结果，因为没有意义
                //我们让resultMap保持空Map，在VoteLineResult构造函数中进行处理
                resultMap.put((Integer)objArr[4],((BigInteger)objArr[5]).intValue());
            } else {
                //说明之前统计的收到票数是错的，
                this.receivedNum = 0;
            }
            tempObj = objArr;
        }
        // 额外处理最后一次结果
        voteLineResult = new VoteLineResult(resultMap, (Integer) tempObj[2], tempHeaders, this.receivedNum,
                pass_rate_parent, pass_rate_child, isAbstain, is_contain);
        line.add(voteLineResult);
        //v.vote_id,v.title,v.max_pass_number,v.pass_rate_mother,v.pass_rate_child,row,column
    }

    public Integer getVoteId() {
        return voteId;
    }

    public void setVoteId(Integer voteId) {
        this.voteId = voteId;
    }

    public List<String> getUsersNotVote() {
        return usersNotVote;
    }

    public void setUsersNotVote(List<String> usersNotVote) {
        this.usersNotVote = usersNotVote;
    }

    public Integer getReceivedNum() {
        return receivedNum;
    }

    public void setReceivedNum(Integer receivedNum) {
        this.receivedNum = receivedNum;
    }

    public List<VoteLineResult> getLine() {
        return line;
    }

    public void setLine(List<VoteLineResult> line) {
        this.line = line;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }
}
