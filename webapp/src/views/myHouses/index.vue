<template>
  <div class="app-container calendar-list-container">
    <!-- 这是操作栏 -->
    <div class="filters">
      <div class="filter-block vote-create">
        <el-tag>房源管理</el-tag>
        <div class="filter-container double-filter-container">
            <el-button type="primary"  icon="el-icon-tickets" @click="handleTraditional">发布房源</el-button>
        </div>
      </div>
      
    </div>
    <!-- 这是筛选栏 -->
    <div class="filter-bar">
      <el-select disabled style="width: 200px;" class="filter-item" :placeholder="searchInputPlaceholder"></el-select>
    </div>
    <!-- 这是选票列表表格 -->
    <el-table :data="responseData" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" type="selection" width="55"></el-table-column>
      <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
      <el-table-column align="center" label="房源类型">
        <template slot-scope="scope">
          <span>{{scope.row.vote_type_name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源简介">
        <template slot-scope="scope">
          <span>{{scope.row.vote_type_name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源所在地">
        <template slot-scope="scope">
          <span>{{scope.row.vote_type_name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="phone">
        <template slot-scope="scope">
          <span>{{scope.row.vote_type_name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="zujin">
        <template slot-scope="scope">
          <span>{{scope.row.vote_type_name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="fabushijian">
        <template slot-scope="scope">
          <span>{{scope.row.vote_type_name}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.vote_status | statusFilter">{{scope.row.vote_status | voteStatusFilter}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.vote_status!='1'" type="primary" size="mini" @click="" disabled>编辑</el-button>
          <el-button v-if="scope.row.vote_status=='0'" size="mini" type="success" @click="handlePublish(scope.$index,scope.row)">发布</el-button>
          <el-button v-if="scope.row.vote_status!='0'" size="mini" type="info" @click="viewResult(scope.$index,scope.row)">结果</el-button>
          <!-- <el-button size="mini" type="info" @click="viewResult(scope.$index,scope.row)">结果</el-button> -->
          <el-button v-if="scope.row.vote_status!='1'" size="mini" type="danger" disabled>删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 这是选票创建或修改弹框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" top="5vh">
      <!-- 这是简单选票创建 -->
      <el-form v-if="dialogStatus === 'simpleCreate'" label-position="left" label-width="125px" >
       <div v-show="steps['simpleCreate']==1" class="step-container">
          <el-tag type="mini">选票属性</el-tag>
          <div class="info-container">
            <el-form-item label="投票内容">
              <el-input v-model="simpleCreateForm.voteName"></el-input>
            </el-form-item>
            <el-form-item label="规则描述">
              <el-input  v-model="simpleCreateForm.ruleDescription">
                <el-button slot="append" icon="el-icon-printer">生成规则</el-button>
              </el-input>
              
            </el-form-item>
            <el-form-item label="弃权单列">
              <el-tooltip :content="'当前状态: ' + abstainMap[simpleCreateForm.isAbstain]" placement="top">
                <el-switch
                  v-model="simpleCreateForm.isAbstain"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  active-value=true
                  inactive-value=false>
                </el-switch>
              </el-tooltip>
            </el-form-item>
          </div>
          <el-tag type="mini">详细参数</el-tag>
          <div class="info-container">
            <el-form-item label="行数">
              <el-input-number :min="0" v-model="simpleCreateForm.rowNumber"></el-input-number>
            </el-form-item>
            <el-form-item label="列数">
              <el-input-number :min="0" v-model="simpleCreateForm.columnNumber"></el-input-number>
            </el-form-item>
            
            <el-form-item label="最大优先推荐人数">
              <el-input-number :min="0" v-model="simpleCreateForm.maxAgree"></el-input-number>
            </el-form-item>
          </div>
       </div>
       <div v-show="steps['simpleCreate']==2" class="step-container">
         <el-tag type="mini">选票信息</el-tag>
          <div class="info-container">
            <el-row :gutter="20" type="flex" justify="space-around">
              <h3>{{meetingForm.meetingName}}</h3>                                                                                                                                                                                                                                                                                                                                                                                                                         
            </el-row>
            <el-row :gutter="20">    
              <el-col :span="10"><div class="grid-content bg-purple">投票限制：最多优先推荐人数为{{simpleCreateForm.minAgree}}</div></el-col>
              <el-col :span="10">
                <el-tag size="big" type="info">简单选票</el-tag>
                <!-- <el-tag size="big" type="info">实名投票</el-tag> -->
                <el-tag size="big" v-if="simpleCreateForm.isAbstain == 'true'" type="info">允许弃权</el-tag>
              </el-col>
            </el-row>
          </div>
          <el-tag type="mini">选票数据</el-tag>
          <div class="info-container">
            <el-row :gutter="20" type="flex" justify="space-around">
              <el-col :span="8"><el-button :disabled="true"size="small">添加行</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">添加列</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">删除行</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">删除列</el-button></el-col>
            </el-row>
            <upload-excel-component @on-selected-file='selected'></upload-excel-component>
            <el-table :data="tableData" border highlight-current-row style="width: 100%;margin-top:20px;">
              <el-table-column v-for='item of tableHeader' :prop="item" :label="item" :key='item'>
              </el-table-column>
            </el-table>
          </div>
       </div>
      </el-form>
      <!-- 这是传统选票创建 -->
      <el-form v-if="dialogStatus === 'traditionalCreate'" label-position="left"  label-width="100px" >
        <div v-show="steps['traditionalCreate']==1" class="step-container">
         
          <el-tag type="mini">选票属性</el-tag>
          <div class="info-container">
            <el-form-item label="投票内容">
              <el-input v-model="traditionalCreateForm.voteName"></el-input>
            </el-form-item>
            <el-form-item label="规则描述">
              <el-input  v-model="traditionalCreateForm.ruleDescription">
                <el-button slot="append" icon="el-icon-printer" @click="handleRuleGenerate">生成规则</el-button>
              </el-input>
              
            </el-form-item>
            <el-form-item label="弃权单列">
              <el-tooltip :content="'当前状态: ' + abstainMap[traditionalCreateForm.isAbstain]" placement="top">
                <el-switch
                  v-model="traditionalCreateForm.isAbstain"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  :active-value="1"
                  :inactive-value="0">
                </el-switch>
              </el-tooltip>
            </el-form-item>
          </div>
          <el-tag type="mini">详细参数</el-tag>
          <div class="info-container">
            <el-form-item label="行数">
              <el-input-number :min="0" v-model="traditionalCreateForm.rowNumber"></el-input-number>
            </el-form-item>
            <el-form-item label="列数">
              <el-input-number :min="0" v-model="traditionalCreateForm.columnNumber"></el-input-number>
            </el-form-item>
            <el-form-item label="同意票数">
              <el-input-number :min="0" v-model="traditionalCreateForm.minAgree"></el-input-number>
              至
              <el-input-number :min="0"   v-model="traditionalCreateForm.maxAgree"></el-input-number>
            </el-form-item>
            <el-form-item label="通过要求">
              <el-input-number :min="0" v-model="traditionalCreateForm.passRateChild"></el-input-number>
              ／
              <el-input-number :min="1" v-model="traditionalCreateForm.passRateMother"></el-input-number>
              <span>（含）</span>
              <el-tooltip :content="'当前状态: ' + containMap[traditionalCreateForm.isContain]" placement="top">
                <el-switch
                  v-model="traditionalCreateForm.isContain"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  :active-value="1"
                  :inactive-value="0">
                </el-switch>
              </el-tooltip>
            </el-form-item>
            <el-form-item label="最大通过人数">
              <el-input-number :min="0" v-model="traditionalCreateForm.maxPassNumber"></el-input-number>
            </el-form-item>
          </div>
        </div>
        <div v-show="steps['traditionalCreate']==2" class="step-container">
          <el-tag type="mini">选票信息</el-tag>
          <div class="info-container">
            <el-row :gutter="20" type="flex" justify="space-around">
              <h3>{{meetingForm.meetingName}}</h3>                                                                                                                                                                                                                                                                                                                                                                                                                         
            </el-row>
            <el-row :gutter="20">                                                                                                                                                   
              <el-col :span="10"><div class="grid-content bg-purple">通过条件：{{traditionalCreateForm.passRateChild}}/{{traditionalCreateForm.passRateMother}}可投票者以上同意</div></el-col>
              <el-col :span="10">
                <el-tag size="big" type="info">传统选票</el-tag>
                <!-- <el-tag size="big" type="info">实名投票</el-tag> -->
                <el-tag size="big" v-if="traditionalCreateForm.isAbstain" type="info">允许弃权</el-tag>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="10"><div class="grid-content bg-purple">投票限制：每人可同意{{traditionalCreateForm.minAgree}}至{{traditionalCreateForm.maxAgree}}项</div></el-col>
            </el-row>
          </div>
          <el-tag type="mini">选票数据</el-tag>
          <div class="info-container">
            <el-row :gutter="20" type="flex" justify="space-around">
              <el-col :span="8"><el-button :disabled="true"size="small">添加行</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">添加列</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">删除行</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">删除列</el-button></el-col>
            </el-row>
            <upload-excel-component @on-selected-file='selected'></upload-excel-component>
            <el-table :data="tableData" border highlight-current-row style="width: 100%;margin-top:20px; height:450px; overflow-y:scroll">
              <el-table-column :sortable="true" @sort-method="tableDataSort" v-for='(item,index) of tableHeader' v-if="index==0"  :prop="item" :label="item" :key='item'>
              </el-table-column>
              <el-table-column  v-for='(item,index) of tableHeader' v-if="index!=0" :prop="item" :label="item" :key='item'>
              </el-table-column>
            </el-table>
          </div>
        </div>
      </el-form>
      <!-- 这是排名选票创建 -->
      <el-form v-if="dialogStatus === 'rankCreate'" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <div v-show="steps['rankCreate']==1" class="step-container">
          <el-tag type="mini">选票属性</el-tag>
          <div class="info-container">
            <el-form-item label="投票内容">
              <el-input v-model="rankCreateForm.voteName"></el-input>
            </el-form-item>
            <el-form-item label="规则描述">
              <el-input  v-model="rankCreateForm.ruleDescription">
                <el-button slot="append" icon="el-icon-printer">生成规则</el-button>
              </el-input>
              
            </el-form-item>
            <el-form-item label="弃权单列">
              <el-tooltip :content="'当前状态: ' + abstainMap[rankCreateForm.isAbstain]" placement="top">
                <el-switch
                  v-model="rankCreateForm.isAbstain"
                  active-color="#13ce66"
                  inactive-color="#ff4949"
                  active-value=true
                  inactive-value=false>
                </el-switch>
              </el-tooltip>
            </el-form-item>
          </div>
          <el-tag type="mini">详细参数</el-tag>
          <div class="info-container">
            <el-form-item label="行数">
              <el-input-number :min="0" v-model="rankCreateForm.rowNumber"></el-input-number>
            </el-form-item>
            <el-form-item label="列数">
              <el-input-number :min="0" v-model="rankCreateForm.columnNumber"></el-input-number>
            </el-form-item>
            <el-form-item label="同意票数">
              <el-input-number :min="0" v-model="rankCreateForm.minAgree"></el-input-number>
              至
              <el-input-number :min="0"   v-model="rankCreateForm.maxAgree"></el-input-number>
            </el-form-item>
            <el-form-item label="通过要求">
              <el-input-number :min="0" v-model="rankCreateForm.passRateChild"></el-input-number>
              ／
              <el-input-number :min="1" v-model="rankCreateForm.passRateMother"></el-input-number>
            </el-form-item>
            <el-form-item label="最大通过人数">
              <el-input-number :min="0" v-model="rankCreateForm.maxPassNumber"></el-input-number>
            </el-form-item>
          </div>
       </div>
       <div v-show="steps['rankCreate']==2" class="step-container">
         <el-tag type="mini">选票信息</el-tag>
          <div class="info-container">
            <el-row :gutter="20" type="flex" justify="space-around">
              <h3>{{meetingForm.meetingName}}</h3>                                                                                                                                                                                                                                                                                                                                                                                                                         
            </el-row>
            <el-row :gutter="20">                                                                                                                                                   
              <el-col :span="10"><div class="grid-content bg-purple">通过条件：{{rankCreateForm.passRateChild}}/{{rankCreateForm.passRateMother}}可投票者以上同意</div></el-col>
              <el-col :span="10">
                <el-tag size="big" type="info">排名选票</el-tag>
                <el-tag size="big" type="info">实名投票</el-tag>
                <el-tag size="big" v-if="rankCreateForm.isAbstain == true" type="info">允许弃权</el-tag>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="10"><div class="grid-content bg-purple">投票限制：每人可同意{{rankCreateForm.minAgree}}至{{rankCreateForm.maxAgree}}项</div></el-col>
            </el-row>
          </div>
          <el-tag type="mini">选票数据</el-tag>
          <div class="info-container">
            <el-row :gutter="20" type="flex" justify="space-around">
              <el-col :span="8"><el-button :disabled="true"size="small">添加行</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">添加列</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">删除行</el-button></el-col>
              <el-col :span="8"><el-button :disabled="true"size="small">删除列</el-button></el-col>
            </el-row>
            <upload-excel-component @on-selected-file='selected'></upload-excel-component>
            <el-table :data="tableData" border highlight-current-row style="width: 100%;margin-top:20px;">
              <el-table-column v-for='item of tableHeader' :prop="item" :label="item" :key='item'>
              </el-table-column>
            </el-table>
          </div>
       </div>
      </el-form>
      <!-- 开始会议，可重新编辑 -->
      <el-form  v-if="dialogStatus === 'meetingCreate'" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="会议名称">
          <el-input readonly v-model="meetingForm.meetingName"></el-input>
        </el-form-item>
        <el-form-item label="会议时间">
          <el-date-picker
            v-model="meetingForm.meetingTime"
            readonly 
            type="datetime"
            placeholder="选择日期时间"
            default-time="12:00:00">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="会议地点">
          <el-input readonly v-model="meetingForm.meetingAddr"></el-input>
        </el-form-item>
        <el-form-item label="与会评委">
          <el-table
            border
            :data="usersMeeting"
            style="width: 100% ; overflow:scroll;height:220px">
            <el-table-column
              label="工号">
              <template slot-scope="scope">
                {{scope.row.username}}
              </template>
            </el-table-column>
            <el-table-column
              label="姓名">

              <template slot-scope="scope">
                <span>{{scope.row.loginName}}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item label="与会机器">
          <el-table
          :data="terminalsMeeting"
            border
            style="width: 100% ; overflow:scroll;height:220px">
            <el-table-column
              label="序号">
              <template slot-scope="scope">
                <span>{{scope.row.terminal_sequence}}</span>
              </template>
            </el-table-column>
          </el-table>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button v-if="steps[dialogStatus]!=1&&dialogStatus!='meetingCreate'" type="primary" @click="handleLastStep(dialogStatus)">上一步</el-button>
        <el-button v-else-if="steps[dialogStatus]!=2&&dialogStatus!='meetingCreate'" type="primary"  @click="handleNextStep(dialogStatus)">下一步</el-button>
        <el-button v-if="['simpleCreate','traditionalCreate','rankCreate'].indexOf(dialogStatus)>-1&&steps[dialogStatus]==2" type="primary" :disabled="unclickable" @click="handleCreate">{{clickabletext}}</el-button>
        <el-button v-if="dialogStatus=='meetingCreate'" type="primary" @click="handleMeetingStart">开始会议</el-button>
        <!-- <el-button v-else type="primary" >更新</el-button> -->
      </div>
    </el-dialog>
    <!-- 这是选票记录修改或结果弹框 -->
    <el-dialog :title="recordTextMap[recordDialogStatus]"  :visible.sync="recordDialogFormVisible" top="10vh" :center="true" width="70%">

      <el-form v-if="dialogStatus === 'voteResult'" label-position="left" label-width="70px" style='width: 100%;'>
        <el-container>
          <el-header>
            <h2 class="result-title">{{nowRecord.title}}</h2>
          </el-header>
          <el-main>
            <p>{{nowRecord.sub_title}}</p>
            <div>
              <el-button type="success" size="small" @click="refreshResult">刷新</el-button>
              <el-button type="primary" size="small">详情</el-button>
              <el-button v-if="isShowVoters" type="primary" size="small" @click="isShowVoters=!isShowVoters">隐藏投票人</el-button>
              <el-button v-else type="primary" size="small" @click="isShowVoters=!isShowVoters">显示投票人</el-button>
              <el-button v-if="isShowResult" type="primary" size="small" @click="isShowResult=!isShowResult">隐藏结果</el-button>
              <el-button v-else type="primary" size="small" @click="isShowResult=!isShowResult">显示结果</el-button>
              <el-tag type="danger" style="margin:0 20px;">{{voteResult.usersNotVote.length}}人已投</el-tag>
              <el-button type="primary" size="small" icon="el-icon-download" @click="exportVoteResult">导出结果</el-button>
              <el-button type="danger" size="small" @click="endNowVote" :disabled="nowRecord.vote_status==2">结束</el-button>
            </div>
            <el-table 
            
             style="margin-top:10px;height:450px; overflow-y:scroll" border :data="voteResult.line">
              
              
              <el-table-column v-for="(item,index) in voteResult.headers" v-if="index!=voteResult.headers.length-1" :key="index" align="center" :label="item">
                
                <template  slot-scope="scope">
                  <span>{{scope.row.headers[index]}}</span>
                </template>
              </el-table-column>
              <el-table-column align="center" label="详情">
                <template slot-scope="scope">
                  <span>
                    <el-tag type="success">同意:{{scope.row.resultDetailMap['1']}}</el-tag>
                    <el-tag type="danger">不同意:{{scope.row.resultDetailMap['-1']}}</el-tag>
                    <el-tag type="info" v-if="nowRecord.is_abstain">弃权:{{scope.row.resultDetailMap['0']}}</el-tag>
                  </span>
                </template>
              </el-table-column>
              <el-table-column v-if="isShowResult" align="center" label="当前结果">
                <template slot-scope="scope">
                  <span>{{scope.row.result}}</span>
                </template>
              </el-table-column>
              
            </el-table>
          </el-main>
          <el-footer v-if="isShowVoters" class="vote-result-footer">
            <p><el-tag>投票者</el-tag><span v-for="(item,index) in voteResult.usersNotVote">{{item}}<span v-if="index!=(voteResult.usersNotVote.length-1)">、</span></span><span v-if="voteResult.usersNotVote.length==0">暂无评委投票</span></p>
            <!-- <p><el-tag>未投票者</el-tag>1,2</p> -->
          </el-footer>
        </el-container>
      </el-form>

    </el-dialog>
  </div>
</template>

<script>
import { fetchList,createVote,getTraditionalResult,changeVoteStatus,exportExcel } from '@/api/vote'
import { startMeeting,endMeeting } from "@/api/meeting";
import { getList } from "@/api/judges";
import { getListTerminals } from '@/api/terminal'
import UploadExcelComponent from '@/components/UploadExcel/index.vue'
import Cookies from "js-cookie";
import { parseTime } from '@/utils'

export default {
  name: 'vote',
  components: { UploadExcelComponent },
  data() {
    return {
      usersMeeting:[],
      terminalsMeeting:[],
      unclickable:false,
      clickabletext:'确认',
      tableData: [],
      isShowVoters:false,
      isShowResult:false,
      tableHeader: [],
      traditionalCreateForm:{
        meetingId:0,
        voteName:'',
        vote_type_id:2,
        ruleDescription:'',
        isAbstain:0,
        rowNumber:0,
        columnNumber:0,
        minAgree:0,
        maxAgree:0,
        passRateMother:1,
        passRateChild:1,
        maxPassNumber:0,
        isContain:0,
        table:{
          header:[],
          rows:[]
        }
      },
      simpleCreateForm:{
        meetingId:0,
        voteName:'',
        vote_type_id:2,
        ruleDescription:'',
        isAbstain:true,
        rowNumber:0,
        columnNumber:0,
        minAgree:0,
        maxAgree:0,
        passRateMother:1,
        passRateChild:1,
        maxPassNumber:0,
        table:{
          header:[],
          rows:[]
        }
      },
      rankCreateForm:{
        meetingId:0,
        voteName:'',
        vote_type_id:2,
        ruleDescription:'',
        isAbstain:true,
        rowNumber:0,
        columnNumber:0,
        minAgree:0,
        maxAgree:0,
        passRateMother:1,
        passRateChild:1,
        maxPassNumber:0,
        table:{
          header:[],
          rows:[]
        }
      },
      nowMeetingId:undefined,
      nowRecord:{},
      meetingStatus:0,
      meetingStatusMap:['未开始','正在进行','已结束'],
      list: null,
      listLoading: true,
      voteTypeOptions: [1, 2, 3],
      searchInputPlaceholder: '请输入房源类型',
      recordDialogStatus: 'voteResult',
      recordDialogFormVisible:false,
      recordTextMap:{
        voteResult:'选票结果'
      },
      responseData: [],
      dialogFormVisible: false,
      dialogStatus: 'simpleCreate',
      listQuery: {
        page: 1,
        limit: 10,
        importance: undefined
      },
      textMap: {
        update: 'Edit',
        simpleCreate: '添加简单选票',
        traditionalCreate: '添加传统选票',
        rankCreate: '添加排名选票',
        meetingCreate: '创建会议',
      },
      steps:{
        simpleCreate:1,
        traditionalCreate:1,
        rankCreate:1
      },
      isAbstain:false,
      abstainMap:{
        1:"弃权单列开启",
        0:"弃权单列关闭"
      },
      containMap:{
        1:'包含',
        0:'不包含'
      },
      meetingForm:{
        meetingName:'',
        meetingTime:'',
        meetingAddr:''
      },
      voteResult:{
        voteId:'',
        usersNotVote:[],
        receivedNum:'',
        line:[]
      },
      changeVoteStatusForm:{
        voteId:undefined,
        status:undefined
      }
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        '0': 'success',
        '1': 'info',
        deleted: 'danger'
      }
      return statusMap[status]
    },
    voteStatusFilter(status){

      const voteStatusMap = ['未发布','已发布','已结束']
      return voteStatusMap[status]
    }
  },
  created() {
    this.meetingStatus = Cookies.get('meetingStatus')
    let meetingForm = Cookies.get('meetingForm')
    if (meetingForm) {
      this.meetingForm = JSON.parse(meetingForm)
    }
    this.getList()
    if(!this.nowMeetingId){
      this.$message.warning('当前会议已结束！请先创建会议！')
    }
  },
  methods: {
    tableDataSort(a,b){
      return parseInt(a)-parseInt(b)
    },
    saveMeetingStatus(){
       Cookies.set('meetingStatus',this.meetingStatus)
    },
    selected(data) {
      this.tableData = data.results
      this.tableHeader = data.header
      this.traditionalCreateForm.rowNumber = this.tableData.length+1
      this.traditionalCreateForm.columnNumber = this.tableHeader.length
    },
    handleCancel(){
      this.dialogFormVisible = false
      for (const key in this.steps) {
        if (this.steps.hasOwnProperty(key)) {
          this.steps[key] = 1
        }
      }
    },
    handleSimple(){
      if (!this.nowMeetingId) {
        this.$message.warning('当前会议已结束！请重新创建会议！')
        return
      }
      this.dialogStatus = 'simpleCreate'
      this.dialogFormVisible = true
    },
    handleTraditional(){
      if (!this.nowMeetingId) {
        this.$message.warning('当前会议已结束！请重新创建会议！')
        return
      }
      this.dialogStatus = 'traditionalCreate'
      this.dialogFormVisible = true;
    },
    handleRank(){
      if (!this.nowMeetingId) {
        this.$message.warning('当前会议已结束！请重新创建会议！')
        return
      }
      this.dialogStatus = 'rankCreate'
      this.dialogFormVisible = true;
    },
    handleCreate(){
      switch (this.dialogStatus) {
        case 'traditionalCreate':
          this.handleTraditionalCreate()
          break;
      
        default:
          break;
      }
    },
    handleTraditionalCreate(){
      this.unclickable = true;
      this.clickabletext = '正在创建中...'
      this.traditionalCreateForm.meetingId = this.nowMeetingId
      this.traditionalCreateForm.table.rows = this.tableData
      this.traditionalCreateForm.table.header = this.tableHeader
      createVote(this.traditionalCreateForm).then(response => {
        if (response.success) {
          this.$message.success(response.message)
          this.dialogFormVisible = false
          this.getList()
          this.unclickable = false
          this.clickabletext = '确认'
        }else{
          this.$message.error(response.message)
        }
      })
    },
    handleRuleGenerate(){
      let traditionalForm = this.traditionalCreateForm;
      this.traditionalCreateForm.ruleDescription = '同意票数'+
                                                    (traditionalForm.isContain==0?'超过':'达到')+
                                                    '有效票数'+
                                                    traditionalForm.passRateChild+
                                                    '/'+
                                                    traditionalForm.passRateMother+
                                                    '通过，每位评委可以同意'+
                                                    traditionalForm.minAgree+
                                                    '至'+
                                                    traditionalForm.maxAgree+
                                                    '人'
    },

    handleMeeting(){
      this.dialogStatus = 'meetingCreate'

       getList({
        role:2,
        meetingId:this.nowMeetingId
       }).then(response =>{
        if (response.success) {
          for (let index = 0; index < response.data.length; index++) {
            const element = response.data[index];
            if (element.onMeeting) {
              
            this.usersMeeting.push(element)
            }
          }
        }
      })
      getListTerminals(this.nowMeetingId).then(response => {
        if (response.success) {
          for (let index = 0; index < response.data.length; index++) {
            const element = response.data[index];
            if (element.is_in_meeting) {
              
                this.terminalsMeeting.push(element)
            }
          }
        }
      })
      this.dialogFormVisible = true;
    },
    handleMeetingStart(){
      startMeeting(this.nowMeetingId).then(response => {
        if (response.success) {
          this.$message.success(response.message)
          this.meetingStatus = 1
          console.log(this.meetingStatus)
          this.saveMeetingStatus()
          this.dialogFormVisible = false
        }else{
          this.$message.error(response.message)
        }
      })
    },
    handleMeetingEnd(){
      this.$confirm('是否要停止结束?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '结束会议成功!'
          });

          endMeeting(this.nowMeetingId).then(response => {
            if (response.success) {
              this.$message.success(response.message)
              this.meetingStatus = 2
            }else{
              this.$message.error(response.message)
            }
            this.saveMeetingStatus()
            Cookies.remove('meetingId')
          })
        })
    },
    handleLastStep(dialogStatus){
      this.steps[dialogStatus]--
    },
    handleNextStep(dialogStatus){
      this.steps[dialogStatus]++
    },
    getList() {
      this.listLoading = true
      this.nowMeetingId = Cookies.get('meetingId')
      if (!this.nowMeetingId) {
        
        this.$notify({
          title: '提示',
          message: '请先创建会议！',
          duration: 7000,
          type: 'error'
        })
        return
            
      }
      fetchList(this.nowMeetingId).then(response => {
        const items = response.data
        this.responseData = items
        this.list = items.map(v => {
          this.$set(v, 'edit', false) // https://vuejs.org/v2/guide/reactivity.html

          v.originalTitle = v.title //  will be used when user click the cancel botton

          return v
        })
        this.listLoading = false
      })
    },
    cancelEdit(row) {
      row.title = row.originalTitle
      row.edit = false
      this.$message({
        message: 'The title has been restored to the original value',
        type: 'warning'
      })
    },
    confirmEdit(row) {
      row.edit = false
      row.originalTitle = row.title
      this.$message({
        message: 'The title has been edited',
        type: 'success'
      })
    },
    viewResult(index,row){
      this.recordDialogFormVisible = true
      this.dialogStatus = 'voteResult'
      this.nowRecord = row
      getTraditionalResult(row.vote_id).then(response =>{
        if (response.success) {
          this.voteResult = response.data
        }
      })
    },
    refreshResult(){
      this.viewResult(0,this.nowRecord)
    },
    handlePublish(index,row){
      this.changeVoteStatusForm.voteId = row.vote_id
      this.changeVoteStatusForm.status = 1
      changeVoteStatus(this.changeVoteStatusForm).then(response =>{
        if (response.success) {
          this.$message.success(response.message)
          this.getList()
        }else{
          this.$message.error(response.message)
        }
      })
    },
    endNowVote(){
      this.changeVoteStatusForm.voteId = this.nowRecord.vote_id
      this.changeVoteStatusForm.status = 2
      changeVoteStatus(this.changeVoteStatusForm).then(response =>{
        if (response.success) {
          this.recordDialogFormVisible = false
          this.$message.success(response.message)
          this.getList()
        }else{
          this.$message.error(response.message)
        }
      })
    },
    exportVoteResult(){
      if (this.nowRecord.vote_status!=2) {
        this.$message.warning('请结束选票后导出结果！')
        return
      }
      
      exportExcel(this.nowRecord.vote_id).then(response => {
        if (response.success) {
          location.href = response.data
        }
      })
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    }
  }
}
</script>

<style scoped <style lang="scss">

div.info-container{
  width: 750px;
  padding: 20px;
  border: 2px solid #F2F2F0;
  margin-bottom: 10px;
}

.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.bg-purple {
  background: #d3dce6;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.el-header{
  text-align: center;
  p{
    text-align: left
  }
}
.result-title{
  font-size: 30px;
}
.vote-result-footer{
  display: flex;
  justify-content: space-around;
  background: #EBEEF5;
}
</style>
