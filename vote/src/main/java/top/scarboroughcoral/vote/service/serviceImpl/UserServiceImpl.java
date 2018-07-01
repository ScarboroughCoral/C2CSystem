package top.scarboroughcoral.vote.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.vote.model.dto.AdminDTO;
import top.scarboroughcoral.vote.model.entity.*;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.dto.LoginDTO;
import top.scarboroughcoral.vote.model.result.UserListResult;
import top.scarboroughcoral.vote.repository.*;
import top.scarboroughcoral.vote.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static top.scarboroughcoral.vote.util.Constant.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Groups_Of_UsersRepository groups_of_usersRepository;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private Users_Of_MeetingRepository users_of_meetingRepository;
    @Autowired
    private Roles_Of_UsersRepository roles_of_usersRepository;
    @Autowired
    private VotesOfUsersRepository votesOfUsersRepository;
    @Autowired
    private TerminalOfMeetingRepository terminalOfMeetingRepository;

    @Override
    public void login(String loginName, String password, String terminalId, BaseResult<LoginDTO> result) {
        User user = userRepository.findByLogin(loginName,password);
        if (user==null){
            if (userRepository.findByLoginName(loginName) != null){
                result.setMessage(USER_PASSWORD_ERROR);
            }else {
                result.setMessage(USER_NOT_FOUND);
            }
            return ;
        }
        List<Integer> group_ids = groups_of_usersRepository.findByUser_id(user.getUser_id());
        Meeting meeting = meetingRepository.findLatestMeeting();
        Integer meeting_id = meeting.getMeeting_id();
        Terminals_Of_Meeting tom = terminalOfMeetingRepository.findTerminalByMeetingIdAndTerminalId(meeting_id,terminalId);
        if (tom == null){
            result.setMessage(TERMINAL_NOT_FOUND);
            return;
        }
        Users_Of_Meeting users_of_meeting = users_of_meetingRepository.existsByMeeting_idAndAndUser_id(meeting_id,user.getUser_id());
        LoginDTO loginDTO = new LoginDTO(user.getUsername(),group_ids);
        if (users_of_meeting!=null&&meeting.getMeeting_status().equals(MEETING_STATUS_START)){
            loginDTO.setMeeting_id(meeting_id);
            loginDTO.setMeetingName(meeting.getMeeting_name());
            loginDTO.setUserId(user.getUser_id());
            result.setMessage(USER_LOGIN_SUCCESS);
            result.setSuccess(true);
        }else if (users_of_meeting==null){
            result.setMessage(USER_MEETING_NOT_ACESS);
        }
        else{
            switch (meeting.getMeeting_status()){
                case MEETING_STATUS_END:
                    result.setMessage(MEETING_ENDED);
                    break;
                default:
                    result.setMessage(MEETING_NOT_START);
            }
        }
        result.setData(loginDTO);
    }

    @Override
    public void admin(String loginName, String password, BaseResult<AdminDTO> result) {
        User user = userRepository.findByAdmin(loginName,password);
        if (user!=null){
            AdminDTO adminDTO = new AdminDTO(user.getUsername());
            result.setData(adminDTO);
            result.setSuccess(true);
            result.setMessage("登陆成功！");
        }else {
            result.setMessage("请检查用户名和密码！");
        }
    }

    @Override
    public void addUser(String loginName, String username, String password, Integer userPriority, BaseResult<Object> result, Integer role) {
        User user = new User(loginName, username, password, userPriority);
        userRepository.save(user);
        Roles_Of_Users roles_of_users = new Roles_Of_Users(user.getUser_id(),role);
        roles_of_usersRepository.save(roles_of_users);
        result.construct(RESULT_SAVE_USER_SUCCESS_MESSAGE, true, null);
    }

    @Override
    public void listUser(Integer role, Integer meetingId, Integer voteId, BaseResult<List<UserListResult>> result) {
        List<Object> objList = userRepository.findByRole(role,meetingId, voteId);
        List<UserListResult> userList = objList.stream().map(UserListResult::new)
                .collect(Collectors.toList());
        result.construct(RESULT_BASE_QUERY_SUCCESS_MESSAGE, true, userList);
    }

    @Override
    public void changeUserMeeting(List<Integer> userIdList, Integer meetingId, BaseResult<Object> result) {
        users_of_meetingRepository.deleteAllByMeetingId(meetingId);
        List<Users_Of_Meeting> users_of_meetingList = new ArrayList<>();
        for (Integer id:userIdList){
            Users_Of_Meeting users_of_meeting = new Users_Of_Meeting(meetingId,id);
            users_of_meetingList.add(users_of_meeting);
        }
        users_of_meetingRepository.saveAll(users_of_meetingList);
        result.construct(RESULT_CHANGE_USER_MEETING_SUCCESS_MESSAGE, true, null);
    }

    @Override
    public void changeUserVote(List<Integer> userIdList, Integer voteId, BaseResult<Object> result) {
        votesOfUsersRepository.deleteAllByVoteId(voteId);
        List<Votes_Of_Users> votesOfUsersList = new ArrayList<>();
        for (Integer id:userIdList){
            Votes_Of_Users votesOfUsers = new Votes_Of_Users(voteId,id);
            votesOfUsersList.add(votesOfUsers);
        }
        votesOfUsersRepository.saveAll(votesOfUsersList);
        result.construct(RESULT_CHANGE_USER_VOTE_SUCCESS_MESSAGE, true, null);
    }
}
