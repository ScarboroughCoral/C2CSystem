<template>
  <div class="app-container">
   
    <el-table :data="list" v-loading.body="listLoading"
    @selection-change="handleSelectionChange"
     element-loading-text="Loading" border fit highlight-current-row
     :default-sort = "{prop: 'userid', order: 'descending'}"
     >
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column align="center" label="房源类型">
        <template slot-scope="scope">
          <span>{{scope.row.houseType}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源简介">
        <template slot-scope="scope">
          <span>{{scope.row.houseDesc}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源所在地">
        <template slot-scope="scope">
          <span>{{scope.row.provinceDesc+scope.row.cityDesc+scope.row.districtDesc}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="手机号">
        <template slot-scope="scope">
          <span>{{scope.row.username}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="订单时间">
        <template slot-scope="scope">
          <span>{{scope.row.orderTime|dateTimeFilter}}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column class-name="status-col" label="终端状态" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.terminal_status}}</el-tag>
        </template>
      </el-table-column> -->

      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.vote_status!='1'" type="success" size="mini" @click="" >评价</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getList,changeUserMeeting,changeUserVote } from '@/api/judges'
import { fetchList } from '@/api/vote'
import { listOrder } from "@/api/order";
import Cookies from "js-cookie";
import { parseTime } from "@/utils/index";

export default {
  // name: 'judges',
  data() {
    return {
      options: [],
      userId:undefined,
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
      dateTimeFilter(val){
          return parseTime(val,'{y}年{m}月{d}日')
      }
  },
  created() {
    this.userId = Cookies.get('userID')
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      listOrder(this.userId).then(response => {
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

    },
    updateData() {
      alert('Update')
    },
    refreshList(){
      this.fetchData()
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
