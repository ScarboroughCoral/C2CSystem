<template>
<div class="app-container">
    <el-tag>筛选条件</el-tag>
    <div class="filter-container">

        <el-select v-model="listQuery.provinceId" @change="chooseCity" placeholder="省级地区">
            <el-option v-for="item in provinces" :key="item.id" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <el-select v-model="listQuery.cityId" @change="chooseDistrict" placeholder="市级地区">
            <el-option v-for="item in cities" :key="item.id" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <el-select v-model="listQuery.districtId" placeholder="区级地区">
            <el-option v-for="item in districts" :key="item.id" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <el-select v-model="listQuery.houseTypeID" placeholder="房间类型" style="margin-left:40px;">
            <el-option v-for="item in houseTypes" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
    </div>
    <el-table :data="list" v-loading.body="listLoading" element-loading-text="Loading" @selection-change="handleSelectionChange" :default-sort="{prop: 'terminalid', order: 'descending'}" border fit highlight-current-row>
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column align="center" label="房源类型">
            <template slot-scope="scope">
                <span>{{scope.row.houseTypeDes}}</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="房源简介">
            <template slot-scope="scope">
                <span>{{scope.row.houseDes}}</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="房源所在地">
            <template slot-scope="scope">
                <span>{{scope.row.houseAddr}}</span>
            </template>
        </el-table-column>
        <!-- <el-table-column align="center" label="联系方式">
        <template slot-scope="scope">
          <span>{{scope.row.orderPhone||暂无}}</span>
        </template>
      </el-table-column> -->
        <el-table-column align="center" label="租金">
            <template slot-scope="scope">
                <span>{{scope.row.houseMoney}}</span>
            </template>
        </el-table-column>
        <!-- <el-table-column align="center" label="发布时间">
        <template slot-scope="scope">
          <span>{{(new Date(scope.row.orderTime))}}</span>
        </template>
      </el-table-column> -->
        <!-- <el-table-column class-name="status-col" label="终端状态" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.terminal_status}}</el-tag>
        </template>
      </el-table-column> -->

        <el-table-column align="center" label="操作">
            <template slot-scope="scope">
                <el-button v-if="scope.row.vote_status!='1'" type="primary" size="mini" @click="handleDispatchSpecific(scope.$index,scope.row)">详情</el-button>
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
import {
    getListTerminals,
    addTerminal,
    changeTerminalMeeting
} from '@/api/terminal'
import {
    listHouses
} from "@/api/house";
import {
    parseTime
} from '@/utils'

import {
    getProvince,
    getCity,
    getDistrict
} from "@/api/address";

export default {
    data() {
        return {
            provinces: [],
            cities: [],
            districts: [],
            nowMeetingId: undefined,
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
            selectedOptions: [],
            houseTypes: [{
                value: 1,
                label: '海景房测试'
            }],
            houseTypeID: undefined,
            listQuery: {
                houseTypeID: undefined,
                provinceId: undefined,
                cityId: undefined,
                districtId: undefined
            },
            multipleSelection: [],

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
        inMeetingFilter(status) {
            const statusMap = {
                true: '是',
                false: '否'
            }
            return statusMap[status]
        },
        parseTimeFilter(time) {
            return parseTime(time)
        }
    },
    created() {
        this.fetchData()
        this.getAllProvince()
    },
    methods: {
        fetchData() {
            this.listLoading = true
            listHouses().then(response => {
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
                if (response.success) {
                    this.dialogFormVisible = false
                    this.$message.success(response.message)
                    this.fetchData()
                } else {
                    this.$message.error(response.message)
                }
            })
        },
        updateData() {
            alert('Update')
        },
        freshList() {
            this.fetchData()
        },

        handleDispatchSpecific(index, row) {
            this.$router.push({
                path: '/house?houseId=' + row.houseID
            })
        },
        handleSelectionChange(val, test) {
            this.multipleSelection = val;
        },
        getAllProvince() {
            this.provinces = []
            getProvince().then(response => {
                for (let index = 0; index < response.data.length; index++) {
                    const element = response.data[index];
                    this.provinces.push({
                        value: element.provinceId,
                        label: element.provinceDesc,
                    })
                }
            })
            //  {
            //     value: 'zhinan',
            //     label: '指南',
            //     children: []
            //   }
        },
        chooseCity(val) {
            this.cities = []
            getCity(val).then(response => {
                for (let index = 0; index < response.data.length; index++) {
                    const element = response.data[index];
                    this.cities.push({
                        value: element.cityId,
                        label: element.cityDesc,
                    })
                }
            })
        },
        chooseDistrict(val) {
            this.districts = []
            getDistrict(val).then(response => {
                for (let index = 0; index < response.data.length; index++) {
                    const element = response.data[index];
                    this.districts.push({
                        value: element.districtId,
                        label: element.districtDesc,
                    })
                }
            })
        },
    }
}
</script>
