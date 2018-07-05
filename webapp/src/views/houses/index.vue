<template>
  <div class="app-container">
    <el-tag>终端管理</el-tag>
    <div class="filter-container">
      <el-button type="primary"  icon="el-icon-refresh" @click="freshList">刷新列表</el-button>
      <el-button type="primary" @click="handleCreate" icon="el-icon-circle-plus">添加终端</el-button>
      <el-button type="danger"  icon="el-icon-delete">删除终端</el-button>
      <el-button type="primary" icon="el-icon-sort" @click="handleExchangeMeeting">参会反转</el-button>
    </div>
    <el-table :data="list" 
    v-loading.body="listLoading" 
    element-loading-text="Loading" 
    @selection-change="handleSelectionChange"
    :default-sort = "{prop: 'terminalid', order: 'descending'}"
    border 
    fit 
    highlight-current-row>
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column align="center" label="房源类型">
        <template slot-scope="scope">
          <span>{{scope.row.terminal_sequence}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源简介">
        <template slot-scope="scope">
          <span>{{scope.row.terminal_sequence}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源所在地">
        <template slot-scope="scope">
          <span>{{scope.row.terminal_sequence}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="phone">
        <template slot-scope="scope">
          <span>{{scope.row.terminal_sequence}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="zujin">
        <template slot-scope="scope">
          <span>{{scope.row.terminal_sequence}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="fabushijian">
        <template slot-scope="scope">
          <span>{{scope.row.terminal_sequence}}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column class-name="status-col" label="终端状态" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.terminal_status}}</el-tag>
        </template>
      </el-table-column> -->

      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.vote_status!='1'" type="primary" size="mini" @click="" >details</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="序列号">
          <el-input v-model="addTerminalInfo.terminalId"></el-input>
        </el-form-item>
        <el-form-item label="设备序号">
          <el-input-number v-model="addTerminalInfo.terminalSequence" controls-position="right" :min="1"></el-input-number>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确认</el-button>
        <el-button v-else type="primary" @click="updateData">更新</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getListTerminals,addTerminal,changeTerminalMeeting } from '@/api/terminal'
import Cookies from "js-cookie";

export default {
  data() {
    return {
      nowMeetingId:undefined,
      list: null,
      listLoading: false,
      dialogFormVisible: false,
      dialogStatus: 'create',
      textMap: {
        update: 'Edit',
        create: '添加终端'
      },
      addTerminalInfo: {
        terminalId: '',
        terminalSequence: 1
      },
      listQuery:{

      },
      multipleSelection:[]
      
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        true: 'success',
        false: 'danger'
      }
      return statusMap[status]
    },
    inMeetingFilter(status){
      const statusMap = {
        true: '是',
        false: '否'
      }
      return statusMap[status]
    }
  },
  created() {
      this.nowMeetingId = Cookies.get('meetingId')
      if(!this.nowMeetingId){
        this.$message.warning('请先创建会议！')
        this.nowMeetingId=-1
      }
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getListTerminals(this.nowMeetingId).then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleCreate() {
      this.dialogFormVisible = true
    },
    handleUpdate() {
      this.dialogStatus = 'Edit'
      this.dialogFormVisible = true
    },
    createData() {
      
      addTerminal(this.addTerminalInfo).then(response => {
        if(response.success){
          this.dialogFormVisible = false
          this.$message.success(response.message)
          this.fetchData()
        }else{
          this.$message.error(response.message)
        }
      })
    },
    updateData() {
      alert('Update')
    },
    freshList(){
      this.fetchData()
    },
    handleExchangeMeeting(){
      if (this.nowMeetingId==-1) {
        this.$message.warning('请先创建会议')
        return
      }
      let terminalIdList = []
      let fullTerminalIdList = []
      for (let index = 0; index < this.multipleSelection.length; index++) {
        const element = this.multipleSelection[index];
        if (!element.is_in_meeting) {
            terminalIdList.push(element.terminal_id)
        }
        fullTerminalIdList.push(element.terminal_id)
      }
      for (let index = 0; index < this.list.length; index++) {
        const element = this.list[index];
        if (fullTerminalIdList.indexOf(element.terminal_id)<0&&element.is_in_meeting) {
          terminalIdList.push(element.terminal_id)
        }
      }

      changeTerminalMeeting(this.nowMeetingId,terminalIdList).then(response => {
        if(response.success){
          this.$message.success(response.message)
          this.fetchData()
        }else{
          this.$message.error(response.message)
        }
      })
    },
    handleSelectionChange(val,test){
        this.multipleSelection = val;
    }
  }
}
</script>
