package top.scarboroughcoral.vote.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.vote.model.entity.Meeting;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.repository.MeetingRepository;
import top.scarboroughcoral.vote.service.MeetingService;

import java.util.Optional;

import static top.scarboroughcoral.vote.util.Constant.*;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Value(value = "${ResourcePath}")
    private String resourcePath;

    @Override
    public void createMeeting(Meeting meeting, BaseResult<Meeting> result) {
        Meeting saveMeeting = meetingRepository.save(meeting);
        String sourcePath = resourcePath + saveMeeting.getMeeting_id();

        java.io.File file = new java.io.File(sourcePath);
        if (!file.exists()){
            file.mkdirs();
        }
        result.construct(RESULT_SAVE_MEETING_SUCCESS_MESSAGE,RESULT_SUCCESS,saveMeeting);
    }

    @Override
    public Meeting findLatestMeeting() {
        return meetingRepository.findLatestMeeting();
    }

    @Override
    public void getLatestMeeting(BaseResult<Meeting> result) {
        Meeting meeting = meetingRepository.findLatestMeeting();
        result.construct(GET_LATEST_MEETING_SUCCESS,true,meeting);
    }

    @Override
    public void modifyMeetingStatus(Integer meetingId, String meetingStatus, BaseResult<Meeting> result) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingId);
        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            meeting.setMeeting_status(meetingStatus);
            meetingRepository.save(meeting);
            result.construct(RESULT_CHANGE_MEETING_SUCCESS_MESSAGE,RESULT_SUCCESS,meeting);
        } else {
            result.construct(RESULT_CHANGE_MEETING_FAILED_MESSAGE,RESULT_FAILED,null);
        }
    }
}
