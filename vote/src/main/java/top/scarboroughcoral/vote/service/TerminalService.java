package top.scarboroughcoral.vote.service;

import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.result.TerminalResult;

import java.util.List;

/**
 * Created by Hw Du on 2018/6/21.
 */
public interface TerminalService {
    public BaseResult<Object> addTerminal(String terminalId, String terminalSequence, BaseResult<Object> result);

    void changeTerminalMeeting(List<String> terminalIdList, Integer meetingId, BaseResult<Object> result);

    void getTerminals(Integer meetingId, BaseResult<List<TerminalResult>> result);
}
