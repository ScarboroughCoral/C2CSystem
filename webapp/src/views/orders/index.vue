<template>
  <div class="app-container">
    <el-tag>评委管理</el-tag>
    <div class="filter-container">
      <el-button type="primary"  icon="el-icon-refresh" @click="refreshList">刷新列表</el-button>
      <el-button type="primary" @click="handleCreate" icon="el-icon-circle-plus">添加评委</el-button>
      <el-button type="primary" icon="el-icon-check" disabled>选择组别</el-button>
      <el-button type="primary" @click="handleUpdate" disabled  icon="el-icon-edit">修改信息</el-button>
      <el-button type="danger"  icon="el-icon-delete" disabled>删除评委</el-button>
    </div>

    <div class="filter-bar">
      <el-select v-model="nowVote" filterable placeholder="请选择投票"
      @change="fetchDataByVote">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </div>
    <el-table :data="list" v-loading.body="listLoading"
    @selection-change="handleSelectionChange"
     element-loading-text="Loading" border fit highlight-current-row
     :default-sort = "{prop: 'userid', order: 'descending'}"
     >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column align="center" label="房源类型">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源简介">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源所在地">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="phone">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="zujin">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="fabushijian">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column class-name="status-col" label="终端状态" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.terminal_status}}</el-tag>
        </template>
      </el-table-column> -->

      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.vote_status!='1'" type="success" size="mini" @click="" >envaluate</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" >
      <el-form   label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="工号">
          <el-input v-model="judgeForm.loginName"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="judgeForm.username"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="judgeForm.password"></el-input>
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
import { getList,changeUserMeeting,changeUserVote } from '@/api/judges'
import { fetchList } from '@/api/vote'
import Cookies from "js-cookie";

export default {
  // name: 'judges',
  data() {
    return {
      options: [],
      nowVote: undefined,
      nowMeetingId:0,
      list: null,
      listLoading: false,
      dialogFormVisible: false,
      dialogStatus: 'create',
      textMap: {
        update: 'Edit',
        create: '添加评委'
      },
      judgeForm:{
        loginName:'',
        username:'',
        password:'',
        userPriority:1,
        role:2,
      },
      //评委查询条件----孙云栋
      listQuery:{
        role:2,
        meetingId:'',
        voteId:undefined
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
    onMeetingStatusFilter(status){
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
      this.listQuery.meetingId = this.nowMeetingId
      this.fetchData()
      return
    }
    this.listQuery.meetingId = this.nowMeetingId
    this.fetchData()
    fetchList(this.nowMeetingId).then(response=>{
      if (response.success) {
        for (let index = 0; index < response.data.length; index++) {
          const element = response.data[index];
          this.options.push({
            value:element.vote_id,
            label:element.title
          })
        }
      } else {
        this.$notify({
          title: '提示',
          message: '当前无投票！请先创建投票！再切换评委能否投票！',
          duration: 0,
          type: 'warning'
        });
      }
    })
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getList(this.listQuery).then(response => {
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
      this.loading = true
      this.$store.dispatch('addJudge', this.judgeForm).then((response) => {
          this.loading = false
          this.dialogFormVisible = false;
          this.$message.success('创建成功！')
          this.fetchData()
      }).catch(() => {
        this.loading = false
      })

      //--------------------------------
      //alert('OK11')
    },
    updateData() {
      alert('Update')
    },
    refreshList(){
      this.fetchData()
    },
    handleExchangeMeeting(){
      if (!this.nowMeetingId) {
        this.$message.warning('请先创建会议')
        return
      }
      let userIdList = []
      let fullUserIdList = []
      for (let index = 0; index < this.multipleSelection.length; index++) {
        const element = this.multipleSelection[index];
        if (!element.onMeeting) {
            userIdList.push(element.userId)
        }
        fullUserIdList.push(element.userId)
      }
      for (let index = 0; index < this.list.length; index++) {
        const element = this.list[index];
        if (fullUserIdList.indexOf(element.userId)<0&&element.onMeeting) {
          userIdList.push(element.userId)
        }
      }

      changeUserMeeting(this.nowMeetingId,userIdList).then(response => {
        if(response.success){
          this.$message.success(response.message)
          this.fetchData()
        }else{
          this.$message.error(response.message)
        }
      })
    },
    handleExchangeVote(){
      if (!this.nowVote) {
        this.$message.warning('请先选择当前投票！')
        return
      }
      let userIdList = []
      let fullUserIdList = []
      for (let index = 0; index < this.multipleSelection.length; index++) {
        const element = this.multipleSelection[index];
        if (!element.onVote) {
            userIdList.push(element.userId)
        }
        fullUserIdList.push(element.userId)
      }
      for (let index = 0; index < this.list.length; index++) {
        const element = this.list[index];
        if (fullUserIdList.indexOf(element.userId)<0&&element.onVote) {
          userIdList.push(element.userId)
        }
      }

      changeUserVote(this.nowVote,userIdList).then(response => {
        if(response.success){
          this.$message.success(response.message)
          this.fetchDataByVote()
        }else{
          this.$message.error(response.message)
        }
      })

    },
    handleSelectionChange(val){
        this.multipleSelection = val;
    },
    fetchDataByVote(){
      this.listQuery.voteId = this.nowVote
      this.fetchData()
    }
  }
}
</script>
