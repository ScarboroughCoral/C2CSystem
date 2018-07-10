<template>
  <div class="app-container calendar-list-container">
    <!-- 这是操作栏 -->
    <div class="filters">
      <div class="filter-block vote-create">
        <el-tag>房源管理</el-tag>
        <div class="filter-container double-filter-container">
            <el-button type="primary"  icon="el-icon-tickets" @click="handleHouse">发布房源</el-button>
        </div>
      </div>
      
    </div>
    <!-- 这是筛选栏 -->
    <div class="filter-bar">
      <!-- <el-select disabled style="width: 200px;" class="filter-item" :placeholder="searchInputPlaceholder"></el-select> -->
    </div>
    <!-- 这是选票列表表格 -->
    <el-table :data="responseData" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" type="selection" width="55"></el-table-column>
      <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
      <el-table-column align="center" label="房源类型">
        <template slot-scope="scope">
          <span>{{scope.row.houseTypeDes}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源简介">
        <template slot-scope="scope">
          <span>{{scope.row.houseDecs}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="房源所在地">
        <template slot-scope="scope">
          <span>{{scope.row.houseAddr}}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="租金">
        <template slot-scope="scope">
          <span>{{scope.row.price}}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column align="center" label="发布时间">
        <template slot-scope="scope">
          <span>{{scope.row.vote_type_name}}</span>
        </template>
      </el-table-column> -->
      <el-table-column align="center" label="状态">
        <template slot-scope="scope">
          <el-tag :type="scope.row.houseStateDes | statusFilter">{{scope.row.houseStateDes}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="">编辑</el-button>
          <!-- <el-button size="mini" type="success">发布</el-button> -->
          <el-button size="mini" type="danger">撤回</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 这是发布房源或修改弹框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" top="5vh">
      <el-form v-if="dialogStatus === 'addHouse'" label-position="left"  label-width="100px" >
        <el-form-item label="房源类型">
          <el-select v-model="houseForm.houseTypeID" placeholder="请选择">
            <el-option
              v-for="item in houseTypes"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="租金">
          <el-input-number v-model="houseForm.price" :precision="2" :step="0.01" ></el-input-number>
        </el-form-item>
        <el-form-item label="房间面积">
          <el-input-number v-model="houseForm.houseArea" :precision="2" :step="0.01" ></el-input-number>
        </el-form-item>
        <el-form-item label="容纳人数">
          <el-input-number v-model="houseForm.holdNum" :precision="2" :step="1" ></el-input-number>
        </el-form-item>
        <el-form-item label="房间图片">
          <el-upload
            action="https://jsonplaceholder.typicode.com/posts/"
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-form-item>
        <el-form-item label="出租时间">
          <el-date-picker
            v-model="value5"
            type="datetimerange"
            :picker-options="pickerOptions2"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            align="right">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="房源所在地">
           <el-cascader
            expand-trigger="hover"
            placeholder="请选择地区"
            size="medium"
            clearable
            :options="options"
            v-model="selectedOptions2"
            @change="handleChange">
          </el-cascader>
          <!-- <el-input v-model="houseForm.houseAddr"></el-input> -->
        </el-form-item>
        <el-form-item label="房源简介">
          <el-input
            type="textarea"
            :autosize="{ minRows: 2, maxRows: 4}"
            placeholder="请输入内容"
            v-model="houseForm.houseDesc">
          </el-input>
        </el-form-item>
        
        
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="handleCancel">取消</el-button>
        <el-button type="primary" :disabled="unclickable" @click="handlePublish">{{clickabletext}}</el-button>
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
import Cookies from "js-cookie";
import { parseTime } from '@/utils'
import { publishHouse ,getHouseType,renterHouseMsg} from "@/api/house";

export default {
  name: 'vote',
  data() {
    return {
      pickerOptions2: {
          shortcuts: [{
            text: '最近一周',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近一个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
              picker.$emit('pick', [start, end]);
            }
          }, {
            text: '最近三个月',
            onClick(picker) {
              const end = new Date();
              const start = new Date();
              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
              picker.$emit('pick', [start, end]);
            }
          }]
        },
        value4: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
        value5: '',
       options: [{
          value: 'zhinan',
          label: '指南',
          children: [{
            value: 'shejiyuanze',
            label: '设计原则',
            children: [{
              value: 'yizhi',
              label: '一致'
            }, {
              value: 'fankui',
              label: '反馈'
            }, {
              value: 'xiaolv',
              label: '效率'
            }, {
              value: 'kekong',
              label: '可控'
            }]
          }, {
            value: 'daohang',
            label: '导航',
            children: [{
              value: 'cexiangdaohang',
              label: '侧向导航'
            }, {
              value: 'dingbudaohang',
              label: '顶部导航'
            }]
          }]
        }, {
          value: 'zujian',
          label: '组件',
          children: [{
            value: 'basic',
            label: 'Basic',
            children: [{
              value: 'layout',
              label: 'Layout 布局'
            }, {
              value: 'color',
              label: 'Color 色彩'
            }, {
              value: 'typography',
              label: 'Typography 字体'
            }, {
              value: 'icon',
              label: 'Icon 图标'
            }, {
              value: 'button',
              label: 'Button 按钮'
            }]
          }, {
            value: 'form',
            label: 'Form',
            children: [{
              value: 'radio',
              label: 'Radio 单选框'
            }, {
              value: 'checkbox',
              label: 'Checkbox 多选框'
            }, {
              value: 'input',
              label: 'Input 输入框'
            }, {
              value: 'input-number',
              label: 'InputNumber 计数器'
            }, {
              value: 'select',
              label: 'Select 选择器'
            }, {
              value: 'cascader',
              label: 'Cascader 级联选择器'
            }, {
              value: 'switch',
              label: 'Switch 开关'
            }, {
              value: 'slider',
              label: 'Slider 滑块'
            }, {
              value: 'time-picker',
              label: 'TimePicker 时间选择器'
            }, {
              value: 'date-picker',
              label: 'DatePicker 日期选择器'
            }, {
              value: 'datetime-picker',
              label: 'DateTimePicker 日期时间选择器'
            }, {
              value: 'upload',
              label: 'Upload 上传'
            }, {
              value: 'rate',
              label: 'Rate 评分'
            }, {
              value: 'form',
              label: 'Form 表单'
            }]
          }, {
            value: 'data',
            label: 'Data',
            children: [{
              value: 'table',
              label: 'Table 表格'
            }, {
              value: 'tag',
              label: 'Tag 标签'
            }, {
              value: 'progress',
              label: 'Progress 进度条'
            }, {
              value: 'tree',
              label: 'Tree 树形控件'
            }, {
              value: 'pagination',
              label: 'Pagination 分页'
            }, {
              value: 'badge',
              label: 'Badge 标记'
            }]
          }, {
            value: 'notice',
            label: 'Notice',
            children: [{
              value: 'alert',
              label: 'Alert 警告'
            }, {
              value: 'loading',
              label: 'Loading 加载'
            }, {
              value: 'message',
              label: 'Message 消息提示'
            }, {
              value: 'message-box',
              label: 'MessageBox 弹框'
            }, {
              value: 'notification',
              label: 'Notification 通知'
            }]
          }, {
            value: 'navigation',
            label: 'Navigation',
            children: [{
              value: 'menu',
              label: 'NavMenu 导航菜单'
            }, {
              value: 'tabs',
              label: 'Tabs 标签页'
            }, {
              value: 'breadcrumb',
              label: 'Breadcrumb 面包屑'
            }, {
              value: 'dropdown',
              label: 'Dropdown 下拉菜单'
            }, {
              value: 'steps',
              label: 'Steps 步骤条'
            }]
          }, {
            value: 'others',
            label: 'Others',
            children: [{
              value: 'dialog',
              label: 'Dialog 对话框'
            }, {
              value: 'tooltip',
              label: 'Tooltip 文字提示'
            }, {
              value: 'popover',
              label: 'Popover 弹出框'
            }, {
              value: 'card',
              label: 'Card 卡片'
            }, {
              value: 'carousel',
              label: 'Carousel 走马灯'
            }, {
              value: 'collapse',
              label: 'Collapse 折叠面板'
            }]
          }]
        }, {
          value: 'ziyuan',
          label: '资源',
          children: [{
            value: 'axure',
            label: 'Axure Components'
          }, {
            value: 'sketch',
            label: 'Sketch Templates'
          }, {
            value: 'jiaohu',
            label: '组件交互文档'
          }]
        }],
        selectedOptions: [],
        selectedOptions2: [],
      dialogImageUrl: '',
      dialogVisible: false,
      usersMeeting:[],
      terminalsMeeting:[],
      unclickable:false,
      clickabletext:'确认',
      tableHeader: [],
      houseForm:{
        userId:undefined,
        price:0,
        houseTypeID:undefined,
        houseStateID:1,
        holdNum:0,
        houseDesc:'',
        houseArea:0,
        houseAddr:''
      },
      houseTypes:[
        {
          value:1,
          label:'海景房测试'
        }
      ],
      nowMeetingId:undefined,
      nowRecord:{},
      meetingStatus:0,
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
      dialogStatus: 'addHouse',
      listQuery: {
        page: 1,
        limit: 10,
        importance: undefined
      },
      textMap:{
        addHouse:'发布房源'
      },
      userID:undefined
     
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        '0': 'success',
        '1': 'info',
        deleted: 'danger'
      }
      return statusMap[status]||'primary'
    },
    voteStatusFilter(status){

      const voteStatusMap = ['未发布','已发布','已结束']
      return voteStatusMap[status]
    }
  },
  created() {
    this.userID = Cookies.get('userID')
    this.getList()
    this.houseForm.userId = this.userID
  },
  methods: {
    tableDataSort(a,b){
      return parseInt(a)-parseInt(b)
    },
    saveMeetingStatus(){
       Cookies.set('meetingStatus',this.meetingStatus)
    },
    handlePublish(){
      publishHouse(this.houseForm).then(response => {
        if (response.success) {
          this.$message.success(response.message)
          this.dialogFormVisible = false
          this.getList()
        }
      })
      this.getList()
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
    handleHouse(){
      this.dialogStatus = 'addHouse'
      this.dialogFormVisible = true;
      getHouseType().then(response => {
        this.houseTypes = []
        if(response.success){
          
          for (let i = 0; i < response.data.length; i++) {
            const element = response.data[i];
              this.houseTypes.push({
                value:element.houseTypeId,
                label:element.houseType
              })
          }
        }
      })
    },

    getList() {
      this.listLoading = true
      renterHouseMsg(this.userID).then(response => {
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
    viewResult(index,row){
      this.recordDialogFormVisible = true
      this.dialogStatus = 'voteResult'
      this.nowRecord = row
      getHouseResult(row.vote_id).then(response =>{
        if (response.success) {
          this.voteResult = response.data
        }
      })
    },
    refreshResult(){
      this.viewResult(0,this.nowRecord)
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => {
        if (j === 'timestamp') {
          return parseTime(v[j])
        } else {
          return v[j]
        }
      }))
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
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
