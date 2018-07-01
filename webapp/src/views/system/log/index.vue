<template>
    <div class="admin-container">
        <el-tag>日志操作栏</el-tag>
        <div class="filter-container">
        <el-button type="primary"  icon="el-icon-refresh">刷新日志</el-button>
        <el-button type="success" @click="handleCreate" icon="el-icon-download">导出日志</el-button>
        </div>
        <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" border fit highlight-current-row>
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column align="center" label='ID' width="95">
            <template slot-scope="scope">
            {{scope.$index}}
            </template>
        </el-table-column>
        <el-table-column align="center" prop="created_at" label="日志时间" width="200">
            <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span>{{scope.row.display_time}}</span>
            </template>
        </el-table-column>
        <el-table-column label="日志内容">
            <template slot-scope="scope">
            <i class="el-icon-tickets"></i>
            {{scope.row.title}}
            </template>
        </el-table-column>

        </el-table>
        <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
        <!-- <el-form :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
            <el-form-item :label="$t('table.type')" prop="type">
            <el-select class="filter-item" v-model="temp.type" placeholder="Please select">
                <el-option v-for="item in  calendarTypeOptions" :key="item.key" :label="item.display_name" :value="item.key">
                </el-option>
            </el-select>
            </el-form-item>
            <el-form-item :label="$t('table.date')" prop="timestamp">
            <el-date-picker v-model="temp.timestamp" type="datetime" placeholder="Please pick a date">
            </el-date-picker>
            </el-form-item>
            <el-form-item :label="$t('table.title')" prop="title">
            <el-input v-model="temp.title"></el-input>
            </el-form-item>
            <el-form-item :label="$t('table.status')">
            <el-select class="filter-item" v-model="temp.status" placeholder="Please select">
                <el-option v-for="item in  statusOptions" :key="item" :label="item" :value="item">
                </el-option>
            </el-select>
            </el-form-item>
            <el-form-item :label="$t('table.importance')">
            <el-rate style="margin-top:8px;" v-model="temp.importance" :colors="['#99A9BF', '#F7BA2A', '#FF9900']" :max='3'></el-rate>
            </el-form-item>
            <el-form-item :label="$t('table.remark')">
            <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="Please input" v-model="temp.remark">
            </el-input>
            </el-form-item>
        </el-form> -->
        <!-- <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
            <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
            <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
        </div> -->
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取消</el-button>
            <el-button v-if="dialogStatus=='Create'" type="primary" @click="createData">确认</el-button>
            <el-button v-else type="primary" @click="updateData">更新</el-button>
        </div>
        </el-dialog>
    </div>
</template>


<script>
import { getListTerminals } from '@/api/terminal'

export default {
  data() {
    return {
      list: null,
      listLoading: true,
      dialogFormVisible: false,
      dialogStatus: 'Create',
      textMap: {
        update: 'Edit',
        create: 'Create'
      }
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: 'success',
        draft: 'gray',
        deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  created() {
    // this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getListTerminals(this.listQuery).then(response => {
        this.list = response.data.items
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
      alert('OK')
    },
    updateData() {
      alert('Update')
    }
  }
}
</script>


<style lang="scss" scoped>

</style>
