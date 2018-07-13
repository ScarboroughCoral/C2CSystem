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
      <el-table-column align="center" label="消费">
        <template slot-scope="scope">
          <el-tag type="danger">¥{{scope.row.price}}</el-tag>
        </template >
      </el-table-column>
      <el-table-column align="center" label="订单状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.orderStateDesc|orderStateTagFilter" >{{scope.row.orderStateDesc}}</el-tag>
        </template >
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
          <el-button  type="success" size="mini" @click="handleEnvaluate(scope.$index,scope.row)" >评价</el-button>
          <el-button v-if="scope.row.orderStateDesc!='已退房'"  type="danger" size="mini" @click="handleCheckout(scope.$index,scope.row)" >退房</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="评价" :visible.sync="dialogFormVisible">
        <el-form label-position="left" label-width="100px">
            <el-form-item required label="评价" >
              <el-rate
                v-model="valuateForm.evaluationStar"
                :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
              </el-rate>
            </el-form-item>
            <el-form-item required label="评论">
              <el-input
                type="textarea"
                :autosize="{ minRows: 2, maxRows: 4}"
                placeholder="请输入内容"
                v-model="valuateForm.evaluationStr">
              </el-input>
            </el-form-item>

        </el-form>

        <div slot="footer" class="dialog-footer">
            <el-button type="primary" :disabled="unclickable" @click="envaluate">{{clickabletext}}</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
import { getList,changeUserMeeting,changeUserVote } from '@/api/judges'

import { fetchList } from '@/api/vote'
import { listOrder,checkout } from "@/api/order";
import { envaluateHouse ,updateHouseState} from "@/api/house";
import Cookies from "js-cookie";
import { parseTime } from "@/utils/index";

export default {
  // name: 'judges',
  data() {
    return {
      unclickable:false,
      clickabletext:'提交',
      valuateForm:{
        userId:undefined,
        houseId:undefined,
        evaluationStar:undefined,
        evaluationStr:''
      },
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
      multipleSelection:[],
      nowRecord:{}
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
      },
      orderStateTagFilter(status){
        const statusMap = {
          '已退房':'info',
          '交易完成':'success'
        }
        return statusMap[status]
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
    },
    handleEnvaluate(index,row){
      this.dialogFormVisible = true
      this.nowRecord = row
    },
    envaluate(){
      this.valuateForm.userId = this.userId
      this.valuateForm.houseId = this.nowRecord.houseId
      this.unclickable = true
      this.clickabletext = '正在提交'
      envaluateHouse(this.valuateForm).then(response => {
        if (response.success) {
          this.$message.success(response.message)
        } else {
          this.$message.error(response.message)
        }

        this.unclickable = false
        this.dialogFormVisible = false
        this.clickabletext = '提交'
      })
    },
    handleCheckout(index,row){

        this.$confirm('确定退房吗, 是否继续?', '退房', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
          
            checkout(row.orderId).then(response => {
                if (response.success) {
                    this.$message.success(response.message)
                    this.getList()
                }
            })
        }).catch(() => {
            this.$message({
                type: 'info',
                message: '退房失败！'
            });
        });
    }
  }
}
</script>
