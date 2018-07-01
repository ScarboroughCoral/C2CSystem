package top.scarboroughcoral.vote.service.serviceImpl;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.scarboroughcoral.vote.model.dto.VoteListItemBriefDTO;
import top.scarboroughcoral.vote.model.dto.VoteListItemDTO;
import top.scarboroughcoral.vote.model.dto.Vote_TableDTO;
import top.scarboroughcoral.vote.model.entity.*;
import top.scarboroughcoral.vote.model.result.BaseResult;
import top.scarboroughcoral.vote.model.result.VoteLineResult;
import top.scarboroughcoral.vote.model.result.VoteResult;
import top.scarboroughcoral.vote.repository.*;
import top.scarboroughcoral.vote.service.VoteService;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static top.scarboroughcoral.vote.util.Constant.*;


@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private Table_Row_Repository tableRowRepository;

    @Autowired
    private Table_Row_ColumnRepository table_row_columnRepository;

    @Autowired
    private User_Vote_ResultRepository user_vote_resultRepository;

    @Autowired
    private Vote_TableRepository vote_tableRepository;

    @Autowired
    private VotesOfUsersRepository votesOfUsersRepository;
    
    @Autowired
    private MeetingRepository meetingRepository;


    @Value(value = "${ResourcePath}")
    private String resourcePath;

    @Value("${getResourcePath}")
    private String getResourcePath;

    @Override
    public void getVoteList(Integer meeting_id, Integer user_id, BaseResult<List<VoteListItemBriefDTO>> result) {
        List<VoteListItemBriefDTO> votes = voteRepository.getVoteListByUser_idAndMeeting_id(user_id,meeting_id);
        if (votes.isEmpty()){
            result.setMessage("暂无选票记录");
        }else {
            result.setMessage("查询");
            result.setData(votes);
            result.setSuccess(true);
        }
    }

    @Override
    public void fetchList(Integer meeting_id, BaseResult<List<VoteListItemDTO>> result) {
        List<VoteListItemDTO> votes = voteRepository.fetchListByMeeting_id(meeting_id);
        if (votes.isEmpty()){
            result.setMessage("暂无选票记录");
        }else {
            result.setMessage("查询");
            result.setData(votes);
            result.setSuccess(true);
        }
    }

    @Override
    public void getVote(Integer vote_id, BaseResult<Vote> result) {

        Vote vote = voteRepository.getByVote_id(vote_id);

        if (vote!=null){
            result.setSuccess(true);
            result.setData(vote);
            result.setMessage("查询成功");
        }else {
            result.setMessage("暂无表格数据");
        }
    }

    @Override
    public void voteResult(Integer voteId, List<User_Vote_Result> user_vote_results, BaseResult<Object> result) {
        user_vote_resultRepository.saveAll(user_vote_results);
        Votes_Of_Users votes_of_users = votesOfUsersRepository.findByUserIdAndVoteId(user_vote_results.get(0).getUser_id(),voteId);
        votes_of_users.setVote_status(USER_VOTE_STATUS_VOTED);
        votesOfUsersRepository.save(votes_of_users);
        result.setMessage("提交成功！");
        result.setSuccess(true);
    }

    @Override
    public void addVote(Vote_TableDTO voteTable, BaseResult<Object> result) {
        Vote vote = new Vote(voteTable);
        voteRepository.save(vote);
        Vote_Table vote_table = new Vote_Table(voteTable.getTable(),vote.getVote_id());
        vote_tableRepository.save(vote_table);
        List<Table_Row_Column> table_row_columns = new ArrayList<>();
        List<Table_Row> table_rows = new ArrayList<>();
        Table_Row tableRow = new Table_Row(vote_table.getVote_table_id(),0);
        tableRowRepository.save(tableRow);
        //首先特殊处理表头
        List<String> headerList = voteTable.getTable().getHeader();
        for (int i = 0; i < headerList.size(); i++){
            Table_Row_Column tableRowColumn =
                    new Table_Row_Column(tableRow.getRow_id(), headerList.get(i), i, i == headerList.size() - 1);
            table_row_columns.add(tableRowColumn);
        }
        //然后再处理每一行的数据
        List<Map<String,String>> rows = voteTable.getTable().getRows();
        for (int i=0;i<rows.size();i++){
            tableRow = new Table_Row(vote_table.getVote_table_id(),i+1);
            table_rows.add(tableRow);
        }
        tableRowRepository.saveAll(table_rows);
        List<Map<String, String>> stringStringMap = voteTable.getTable().getRows();
        for (int i=0;i<table_rows.size();i++) {
            Map<String, String> stringMap = stringStringMap.get(i);
            for (int j = 0; j < voteTable.getTable().getHeader().size(); j++) {
                String key = voteTable.getTable().getHeader().get(j);
                Table_Row_Column tableRowColumn =
                        new Table_Row_Column(table_rows.get(i).getRow_id(), stringMap.get(key), j, j == voteTable.getTable().getHeader().size() - 1);
                table_row_columns.add(tableRowColumn);
            }
        }
        table_row_columnRepository.saveAll(table_row_columns);
        result.construct(RESULT_SAVE_VOTE_SUCCESS_MESSAGE,true,vote);
    }

    @Override
    public void changeVoteStatus(Integer voteId, String status, BaseResult<Object> result) {
        voteRepository.updateVoteStatus(voteId,status);
        result.construct(RESULT_CHANGE_VOTE_STATUS_SUCCESS_MESSAGE,true,null);
    }

    @Override
    public void getVoteResult(Integer voteId, BaseResult<VoteResult> result) {
        List<Object> objList = user_vote_resultRepository.getVoteResult(voteId);
        Object voteBrief = voteRepository.getByVoteBrief(voteId);
        List<String> userVoted = user_vote_resultRepository.getVotedUsers(voteId);
        VoteResult voteResult = new VoteResult(objList,voteBrief,userVoted);
        result.construct(RESULT_BASE_QUERY_SUCCESS_MESSAGE, true, voteResult);
    }

    @Override
    @Transactional
    public void exportExcel(Integer voteId, BaseResult<String> result) {

        //1.获取数据
        Vote vote = voteRepository.getOne(voteId);
        Meeting meeting = meetingRepository.getOne(vote.getMeeting_id());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat(" HH:mm:ss");

        BaseResult<VoteResult> voteResultBaseResult = new BaseResult<>();
        getVoteResult(voteId,voteResultBaseResult);
        VoteResult voteResult = voteResultBaseResult.getData();

        int nowRowNumber = 0;
        //创建workbook
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建sheet页
        HSSFSheet sheet = workbook.createSheet(VOTE_RESULT_EXCEL_SHEET_NAME);
        sheet.setDefaultRowHeight((short) 500);
        //创建第一行标题
        HSSFRow row = sheet.createRow(nowRowNumber);

        nowRowNumber++;
        row.setHeight((short) 1000);
        HSSFCell c00 = row.createCell(0);
        c00.setCellValue(new HSSFRichTextString(meeting.getMeeting_name()));
        //创建第二行投票内容
        HSSFRow row1 = sheet.createRow(nowRowNumber);
        nowRowNumber++;
        HSSFCell c10 = row1.createCell(0);
        c10.setCellValue(new HSSFRichTextString("投票内容："+vote.getTitle()));

        //第三行会议日期时间
        HSSFRow row2 = sheet.createRow(nowRowNumber);
        nowRowNumber++;
        HSSFCell c20 = row2.createCell(0);
        c20.setCellValue(new HSSFRichTextString("会议日期"));
        HSSFCell c21 = row2.createCell(1);
        c21.setCellValue(new HSSFRichTextString(dateFormat.format(meeting.getMeeting_time())));
        CellRangeAddress region212 = new CellRangeAddress(2,2,1,2);
        sheet.addMergedRegion(region212);
        HSSFCell c23 = row2.createCell(3);
        c23.setCellValue(new HSSFRichTextString("会议时间"));
        HSSFCell c24 = row2.createCell(4);
        c24.setCellValue(new HSSFRichTextString(timeFormat.format(meeting.getMeeting_time())));
        CellRangeAddress region245 = new CellRangeAddress(2,2,4,5);
        sheet.addMergedRegion(region245);

        //第四行会议地点
        HSSFRow row3 = sheet.createRow(nowRowNumber);
        nowRowNumber++;
        HSSFCell c30 = row3.createCell(0);
        c30.setCellValue(new HSSFRichTextString("会议地点"));
        HSSFCell c31 = row3.createCell(1);
        c31.setCellValue(new HSSFRichTextString(meeting.getMeeting_addr()));
        CellRangeAddress region315 = new CellRangeAddress(3,3,1,5);
        sheet.addMergedRegion(region315);

        //第五行评委人数、投票人数

        HSSFRow row4 = sheet.createRow(nowRowNumber);
        nowRowNumber++;
        HSSFCell c40 = row4.createCell(0);
        c40.setCellValue(new HSSFRichTextString("评委人数"));
        HSSFCell c41 = row4.createCell(1);
        c41.setCellValue(new HSSFRichTextString(vote.getPass_rate_mother().toString()));
        CellRangeAddress region412 = new CellRangeAddress(4,4,1,2);
        sheet.addMergedRegion(region412);
        HSSFCell c43 = row4.createCell(3);
        c43.setCellValue(new HSSFRichTextString("投票人数"));
        HSSFCell c44 = row4.createCell(4);
        c44.setCellValue(new HSSFRichTextString(voteResult.getReceivedNum().toString()));
        CellRangeAddress region445 = new CellRangeAddress(4,4,4,5);
        sheet.addMergedRegion(region445);

        //第六行投票规则
        HSSFRow row5 = sheet.createRow(nowRowNumber);
        nowRowNumber++;
        HSSFCell c50 = row5.createCell(0);
        c50.setCellValue(new HSSFRichTextString("投票规则"));
        HSSFCell c51 = row5.createCell(1);
        c51.setCellValue(new HSSFRichTextString(vote.getSub_title()));
        CellRangeAddress region515 = new CellRangeAddress(5,5,1,5);
        sheet.addMergedRegion(region515);

        //第7、8行序号同意反对
        //当前列变量
        int nowColumn = 0;

        HSSFRow row6 = sheet.createRow(nowRowNumber);
        nowRowNumber++;
        HSSFRow row7 = sheet.createRow(nowRowNumber);
        nowRowNumber++;

        List<String> headers = voteResult.getHeaders();

        for ( nowColumn = 0; nowColumn < headers.size(); nowColumn++) {

            if (nowColumn!=headers.size()-1){

                HSSFCell c60 = row6.createCell(nowColumn);
                c60.setCellValue(new HSSFRichTextString(headers.get(nowColumn)));
                CellRangeAddress region = new CellRangeAddress(6,7,nowColumn,nowColumn);
                sheet.addMergedRegion(region);
            }else {
                break;
            }
        }
        int beforeAdviceColumn = nowColumn;
        //意见列
        String[] advices = headers.get(headers.size()-1).split("\\s+");
        HSSFCell c72 = row6.createCell(nowColumn);
        c72.setCellValue(new HSSFRichTextString("意见"));
        CellRangeAddress region623 = new CellRangeAddress(6,6,nowColumn,nowColumn+advices.length-1);
        sheet.addMergedRegion(region623);
        for (int i = 0; i < advices.length; i++,nowColumn++) {
            HSSFCell c82 = row7.createCell(nowColumn);
            c82.setCellValue(new HSSFRichTextString(advices[i]));
        }
        int beforeResultColumn = nowColumn;
        //结果列
        HSSFCell c74 = row6.createCell(nowColumn);
        c74.setCellValue(new HSSFRichTextString("结果"));
        CellRangeAddress region78c4 = new CellRangeAddress(6,7,nowColumn,nowColumn);
        sheet.addMergedRegion(region78c4);
        nowColumn++;

        CellRangeAddress region1 = new CellRangeAddress(0, 0,  0,  5);
        CellRangeAddress region2 = new CellRangeAddress(1, 1,  0,  5);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);
        
        List<VoteLineResult> lineResults = voteResult.getLine();
        
        for (int i = 0; i < lineResults.size(); i++,nowRowNumber++) {
            HSSFRow nowRow = sheet.createRow(nowRowNumber);
            VoteLineResult nowLine = lineResults.get(i);
            for (int j = 0; j < nowColumn; j++) {
                HSSFCell cell = nowRow.createCell(j);
                if (j<beforeAdviceColumn){
                    cell.setCellValue(new HSSFRichTextString(nowLine.getHeaders().get(j)));
                }else if (j<beforeResultColumn){
                    if (j==beforeAdviceColumn){
                        cell.setCellValue(new HSSFRichTextString(nowLine.getResultDetailMap().get(1).toString()));

                    }else if (j==beforeAdviceColumn+1){
                        cell.setCellValue(new HSSFRichTextString(nowLine.getResultDetailMap().get(-1).toString()));
                    }else if (j==beforeAdviceColumn+2){
                        cell.setCellValue(new HSSFRichTextString(nowLine.getResultDetailMap().get(0).toString()));
                    }
                }else {
                    cell.setCellValue(new HSSFRichTextString(nowLine.getResult()));
                }

            }
        }

        FileOutputStream stream = null;
        try {

            String targetPath = resourcePath + voteId+".xls";
            String outPath = "file/" + voteId+".xls";
            stream = new FileOutputStream(targetPath);
            workbook.write(stream);
            result.setData(outPath);
            result.setMessage("获取结果成功！");
            result.setSuccess(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
