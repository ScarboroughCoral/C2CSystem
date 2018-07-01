package top.scarboroughcoral.vote.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.vote.model.entity.Terminal;
import top.scarboroughcoral.vote.model.entity.Terminals_Of_Meeting;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.result.TerminalResult;
import top.scarboroughcoral.vote.model.result.UserListResult;
import top.scarboroughcoral.vote.repository.TerminalOfMeetingRepository;
import top.scarboroughcoral.vote.repository.TerminalRepository;
import top.scarboroughcoral.vote.service.TerminalService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static top.scarboroughcoral.vote.util.Constant.RESULT_BASE_QUERY_SUCCESS_MESSAGE;
import static top.scarboroughcoral.vote.util.Constant.RESULT_CHANGE_TERMINAL_VOTE_SUCCESS_MESSAGE;
import static top.scarboroughcoral.vote.util.Constant.RESULT_SAVE_TERMINAL_SUCCESS_MESSAGE;

/**
 * Created by Hw Du on 2018/6/21.
 */
@Service
public class TerminalServiceImpl implements TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private TerminalOfMeetingRepository terminalOfMeetingRepository;

    @Override
    public BaseResult<Object> addTerminal(String terminalId, String terminalSequence, BaseResult<Object> result) {
        Terminal terminal = new Terminal(terminalId, terminalSequence);
        terminalRepository.save(terminal);
        result.construct(RESULT_SAVE_TERMINAL_SUCCESS_MESSAGE,true,terminal);
        return result;
    }

    @Override
    public void changeTerminalMeeting(List<String> terminalIdList, Integer meetingId, BaseResult<Object> result) {
        terminalOfMeetingRepository.deleteAllByMeetingId(meetingId);
        List<Terminals_Of_Meeting> terminalsOfMeetingList = new ArrayList<>();
        for (String id:terminalIdList){
            Terminals_Of_Meeting terminalsOfMeeting = new Terminals_Of_Meeting(meetingId,id);
            terminalsOfMeetingList.add(terminalsOfMeeting);
        }
        terminalOfMeetingRepository.saveAll(terminalsOfMeetingList);
        result.construct(RESULT_CHANGE_TERMINAL_VOTE_SUCCESS_MESSAGE, true, null);
    }

    @Override
    public void getTerminals(Integer meetingId, BaseResult<List<TerminalResult>> result) {
        List<Object> objList = terminalOfMeetingRepository.findTerminalStatusByMeetingId(meetingId);
        List<TerminalResult> terminalResults = objList.stream().map(TerminalResult::new)
                .collect(Collectors.toList());
        result.construct(RESULT_BASE_QUERY_SUCCESS_MESSAGE, true, terminalResults);
    }
}
