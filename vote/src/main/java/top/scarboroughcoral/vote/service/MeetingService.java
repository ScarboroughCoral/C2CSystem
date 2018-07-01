package top.scarboroughcoral.vote.service;

import top.scarboroughcoral.vote.model.entity.Meeting;
import top.scarboroughcoral.vote.model.result.BaseResult;

public interface MeetingService {
    public void createMeeting(Meeting meeting, BaseResult<Meeting> result);
    public Meeting findLatestMeeting();
    public void modifyMeetingStatus(Integer meetingId, String meetingStatus, BaseResult<Meeting> result);

    void getLatestMeeting(BaseResult<Meeting> result);
}
