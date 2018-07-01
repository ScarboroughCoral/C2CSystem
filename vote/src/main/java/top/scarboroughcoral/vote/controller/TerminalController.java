package top.scarboroughcoral.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.vote.model.entity.Terminal;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.result.TerminalResult;
import top.scarboroughcoral.vote.repository.TerminalRepository;
import top.scarboroughcoral.vote.service.TerminalService;

import java.util.List;

@RestController
public class TerminalController {

    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private TerminalService terminalService;

    @GetMapping(value = "/terminals")
    public BaseResult<List<TerminalResult>> getTerminals(@RequestParam("meetingId") Integer meetingId){
        BaseResult<List<TerminalResult>> result = new BaseResult<>();
        terminalService.getTerminals(meetingId,result);
        return result;
    }

    @PostMapping(value = "/addTerminal")
        public BaseResult<Object> addTerminal(@RequestParam("terminalId") String terminalId,
                                              @RequestParam("terminalSequence") String terminalSequence){
        BaseResult<Object> result = new BaseResult<>();
        terminalService.addTerminal(terminalId, terminalSequence,result);
        return result;
    }

    @PostMapping(value = "/changeTerminalMeeting")
    public BaseResult<Object> changeTerminalMeeting(@RequestBody List<String> terminalIdList, @RequestParam Integer meetingId){
        BaseResult<Object> result = new BaseResult<>();
        terminalService.changeTerminalMeeting(terminalIdList,meetingId,result);
        return result;
    }
}
