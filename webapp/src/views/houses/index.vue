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
        <el-select v-model="listQuery.districtId" @change="filterHouseFunc" placeholder="区级地区">
            <el-option v-for="item in districts" :key="item.id" :label="item.label" :value="item.value">
            </el-option>
        </el-select>
        <el-select v-model="listQuery.houseTypeId" @change="filterHouseFunc" placeholder="房间类型" style="margin-left:40px;">
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
                <span>{{scope.row.provinceDesc+scope.row.cityDesc+scope.row.districtDess}}</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="租金">
            <template slot-scope="scope">
                <el-tag type="danger">¥{{scope.row.houseMoney}}</el-tag>
            </template>
        </el-table-column>

        <el-table-column align="center" label="操作">
            <template slot-scope="scope">
                <el-button v-if="scope.row.vote_status!='1'" type="primary" size="mini" @click="handleDispatchSpecific(scope.$index,scope.row)">详情</el-button>
            </template>
        </el-table-column>
    </el-table>
</div>
</template>

<script>
import {
    getListTerminals,
    addTerminal,
    changeTerminalMeeting
} from '@/api/terminal'
import {
    listHouses,
    getHouseType,
    filterHouse
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
                houseTypeId: undefined,
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
        this.getHouseTypes()
    },
    methods: {
        getHouseTypes(){
            this.houseTypes = []
            getHouseType().then(response => {
                
                for (let i = 0; i < response.data.length; i++) {
                    const element = response.data[i];
                    this.houseTypes.push({
                        value:element.houseTypeId,
                        label:element.houseType
                    })
                }
            })
        },
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
        },
        chooseCity(val) {
            this.clearQuery(2)
            this.filterHouseFunc()
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
            this.clearQuery(1)
            this.filterHouseFunc()
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
        filterHouseFunc(){
            filterHouse(this.listQuery).then(response => {
                if (response.success) {
                    this.list  = response.data
                }
            })
        },
        clearQuery(number){
            switch (number) {
                case 2:
                    this.listQuery.cityId=undefined
                case 1:
                    this.listQuery.districtId=undefined
                default:
                    break;
            }
        }
    }
}
</script>
