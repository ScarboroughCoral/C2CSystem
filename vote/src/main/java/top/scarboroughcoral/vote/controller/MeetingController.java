package top.scarboroughcoral.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import top.scarboroughcoral.vote.model.entity.Meeting;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.service.MeetingService;

import java.util.Date;

import static top.scarboroughcoral.vote.util.Constant.MEETING_STATUS_END;
import static top.scarboroughcoral.vote.util.Constant.MEETING_STATUS_START;

@RestController
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping("/getLatestMeeting")
    public BaseResult<Meeting> getLatestMeeting(){
        BaseResult<Meeting> result = new BaseResult<>();
        meetingService.getLatestMeeting(result);
        return result;
    }

    @PostMapping("/createMeeting")
    public BaseResult<Meeting> createMeeting(@RequestParam("meetingName") String meetingName,
                                             @RequestParam("meetingAddr") String meetingAddr,
                                             @RequestParam("meetingTime") Long meetingTime){
        Meeting meeting = new Meeting(meetingName,meetingAddr,meetingTime);
        BaseResult<Meeting> result = new BaseResult<>();
        meetingService.createMeeting(meeting,result);
        return result;
    }

    @PostMapping("/startMeeting")
    public BaseResult<Meeting> startMeeting(@RequestParam("meetingId") Integer meetingId){
        BaseResult<Meeting> result = new BaseResult<>();
        meetingService.modifyMeetingStatus(meetingId,MEETING_STATUS_START,result);
        return result;
    }

    @PostMapping("/endMeeting")
    public BaseResult<Meeting> endMeeting(@RequestParam("meetingId") Integer meetingId){
        BaseResult<Meeting> result = new BaseResult<>();
        meetingService.modifyMeetingStatus(meetingId,MEETING_STATUS_END,result);
        return result;
    }
}
