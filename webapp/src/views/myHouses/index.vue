<template>
<div class="app-container calendar-list-container">
    <!-- 这是操作栏 -->
    <div class="filters">
        <div class="filter-block vote-create">
            <el-tag>房源管理</el-tag>
            <div class="filter-container double-filter-container">
                <el-button type="primary" icon="el-icon-tickets" @click="handleHouse">发布房源</el-button>
            </div>
        </div>

    </div>
    <!-- 这是筛选栏 -->
    <div class="filter-bar">
        <!-- <el-select disabled style="width: 200px;" class="filter-item" :placeholder="searchInputPlaceholder"></el-select> -->
    </div>
    <el-table :data="responseData" border fit highlight-current-row style="width: 100%">
        <el-table-column align="center" type="selection" width="55"></el-table-column>
        <el-table-column align="center" label="序号" type="index" width="55"></el-table-column>
        <el-table-column align="center" label="房源类型描述">
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
                <span>{{scope.row.provinceDesc+scope.row.cityDesc+scope.row.districtDesc}}</span>
            </template>
        </el-table-column>
        <el-table-column align="center" label="租金">
            <template slot-scope="scope">
                <el-tag type="danger">¥{{scope.row.price}}</el-tag>
            </template>
        </el-table-column>
        <el-table-column align="center" label="状态">
            <template slot-scope="scope">
                <el-tag :type="scope.row.houseStateDes | statusFilter">{{scope.row.houseStateDes}}</el-tag>
            </template>
        </el-table-column>
        <el-table-column align="center" label="操作">
            <template slot-scope="scope">
                <el-button type="success" size="mini" @click="handleHousePic(scope.$index,scope.row)">上传图片</el-button>
                <el-button type="primary"  v-if="scope.row.houseStateDes!='已经租出'" size="mini" @click="handleHouseEdit(scope.$index,scope.row)">编辑</el-button>
                <!-- <el-button size="mini" type="success">发布</el-button> -->
                <el-button size="mini" type="danger" @click="handleCollect(scope.$index,scope.row)" v-if="scope.row.houseStateDes=='可以租用'">撤回</el-button>
                <el-button size="mini" type="success" @click="handleRepublic(scope.$index,scope.row)" v-if="scope.row.houseStateDes=='已经撤回'">发布</el-button>
            </template>
        </el-table-column>
    </el-table>
    <!-- 这是发布房源或修改弹框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" top="5vh">
        <el-form label-position="left" label-width="100px">
            <el-form-item required label="房源类型" >
                <el-select v-model="houseForm.houseTypeID" placeholder="请选择">
                    <el-option v-for="item in houseTypes" :key="item.value" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item required label="租金">
                <el-input-number v-model="houseForm.price" :precision="2" :step="0.01"></el-input-number><el-tag type="danger">元</el-tag>
            </el-form-item>
            <el-form-item required label="房间面积">
                <el-input-number v-model="houseForm.houseArea" :precision="2" :step="0.01"></el-input-number>
            </el-form-item>
            <el-form-item required label="容纳人数">
                <el-input-number v-model="houseForm.holdNum" :precision="2" :step="1"></el-input-number>
            </el-form-item>

            <el-form-item required label="出租时间">
                <el-date-picker v-model="value5" type="datetimerange" :picker-options="pickerOptions2" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" align="right">
                </el-date-picker>
            </el-form-item>
            <el-form-item required label="房源所在地">
                <el-select v-model="houseForm.provinceId" @change="chooseCity" placeholder="省级地区">
                    <el-option v-for="item in provinces" :key="item.id" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
                <el-select v-model="houseForm.cityId" @change="chooseDistrict" placeholder="市级地区">
                    <el-option v-for="item in cities" :key="item.id" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>
                <el-select v-model="houseForm.districtId" placeholder="区级地区">
                    <el-option v-for="item in districts" :key="item.id" :label="item.label" :value="item.value">
                    </el-option>
                </el-select>

                <!-- <el-input v-model="houseForm.houseAddr"></el-input> -->
            </el-form-item>
            <el-form-item required label="房源简介">
                <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 4}" placeholder="请输入内容" v-model="houseForm.houseDesc">
                </el-input>
            </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible=false">取消</el-button>
            <el-button type="primary" v-if="dialogStatus === 'addHouse'" :disabled="unclickable" @click="handlePublish">{{clickabletext}}</el-button>
            <el-button type="primary" v-else :disabled="unclickable" @click="handleEditSubmit">{{clickabletext}}</el-button>
            <!-- <el-button v-else type="primary" >更新</el-button> -->
        </div>
    </el-dialog>
    <el-dialog title="点击按钮立即上传房间图片" :visible.sync="uploadDialogVisible">
        <el-form>
            <el-form-item required label="房间图片">
                <el-upload action="https://jsonplaceholder.typicode.com/posts/" list-type="picture-card" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :file-list="imgList" :multiple="true" :http-request="handleUpload" :show-file-list="true">
                    <i class="el-icon-plus"></i>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
            </el-form-item>
        </el-form>
    </el-dialog>
</div>
</template>

<script>
import Cookies from "js-cookie";
import {
    parseTime
} from '@/utils'
import {
    publishHouse,
    getHouseType,
    renterHouseMsg,
    uploadImg,
    updateHouseState,
    getSepcificHouse,
    updateHouseInfo
} from "@/api/house";
import {
    getProvince,
    getCity,
    getDistrict
} from "@/api/address";

export default {
    name: 'vote',
    data() {
        return {
            uploadDialogVisible: false,
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
            options: [],
            selectedOptions: [],
            selectedOptions2: [],
            dialogImageUrl: '',
            dialogVisible: false,
            usersMeeting: [],
            terminalsMeeting: [],
            unclickable: false,
            clickabletext: '确认',
            tableHeader: [],
            provinces: [],
            cities: [],
            districts: [],
            imgList: [],
            houseForm: {
                userId: undefined,
                price: 0,
                houseTypeID: undefined,
                houseStateID: 1,
                holdNum: 0,
                houseDesc: '',
                houseArea: 0,
                houseAddr: '',
                provinceId: undefined,
                cityId: undefined,
                districtId: undefined,
                rentStartTime: undefined,
                rentEndTime: undefined
            },
            editForm: {

            },
            houseTypes: [{
                value: 1,
                label: '海景房测试'
            }],
            nowRecord: {},
            list: null,
            listLoading: true,
            searchInputPlaceholder: '请输入房源类型',
            recordDialogStatus: 'voteResult',
            recordDialogFormVisible: false,
            recordTextMap: {
                voteResult: '选票结果'
            },
            responseData: [],
            dialogFormVisible: false,
            dialogStatus: 'addHouse',
            listQuery: {
                page: 1,
                limit: 10,
                importance: undefined
            },
            textMap: {
                addHouse: '发布房源',
                editHouse: '编辑房源'
            },
            userID: undefined,
        }
    },
    filters: {
        statusFilter(status) {
            const statusMap = {
                '可以租用': 'success',
                '已经撤回': 'info',
                deleted: 'danger'
            }
            return statusMap[status] || 'primary'
        },
    },
    created() {
        this.userID = Cookies.get('userID')
        this.getList()
        this.houseForm.userId = this.userID
        this.getAllProvince()
    },
    methods: {
        handleUpload(item) {
            let img = item.file
            let newData = new FormData()
            newData.append('img', img)
            // newData.append('houseId',this.nowRecord.houseId)
            newData.append('houseId', this.nowRecord.houseId)
            uploadImg(newData).then(response => {
                if (response.success) {
                    this.$message.success(response.message)
                } else {
                    this.$message.error(response.message)
                }
            })
            console.log(this.imgList)
            return true
        },
        handleHousePic(index, row) {
            this.uploadDialogVisible = true
            this.nowRecord = row
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
        tableDataSort(a, b) {
            return parseInt(a) - parseInt(b)
        },
        handlePublish() {
            this.houseForm.rentStartTime = this.value5[0]
            this.houseForm.rentEndTime = this.value5[1]
            publishHouse(this.houseForm).then(response => {
                if (response.success) {
                    this.$message.success(response.message)
                    this.dialogFormVisible = false
                    this.getList()
                }
            })
            this.getList()
        },
        handleHouseEdit(index, row) {
            this.getHouseTypes()
            this.dialogStatus = 'editHouse'
            this.dialogFormVisible = true
            this.nowRecord = row
            const self = this
            getSepcificHouse(row.houseId).then(response => {
                if (response.success) {
                    let houseInfo = response.data

                    this.houseForm.rentStartTime = this.value5[0]
                    this.houseForm.rentEndTime = this.value5[1]
                    self.houseForm.price = houseInfo.price
                    self.houseForm.houseArea = houseInfo.area
                    self.editForm = {
                        price: self.houseForm.price,
                        area: self.houseForm.houseArea,
                        houseDesc: self.houseForm.houseDesc,
                        houseAddr: self.houseForm.houseDesc,
                        rentStartTime: self.houseForm.rentStartTime,
                        rentEndTime: self.houseForm.rentEndTime
                    }
                }
            })
        },
        handleEditSubmit() {
            updateHouseInfo(this.nowRecord.houseId, this.editForm).then(response => {

            this.dialogFormVisible = false
              if (response.success) {
                this.$message.success(response.message)
                return
              }
              this.$message.error(response.message)
            })
        },
        selected(data) {
            this.tableData = data.results
            this.tableHeader = data.header
            this.traditionalCreateForm.rowNumber = this.tableData.length + 1
            this.traditionalCreateForm.columnNumber = this.tableHeader.length
        },
        handleHouse() {
            this.dialogStatus = 'addHouse'
            this.dialogFormVisible = true;
            this.getHouseTypes()
        },
        getHouseTypes() {

            getHouseType().then(response => {
                this.houseTypes = []
                if (response.success) {

                    for (let i = 0; i < response.data.length; i++) {
                        const element = response.data[i];
                        this.houseTypes.push({
                            value: element.houseTypeId,
                            label: element.houseType
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

            return true
        },
        handleCollect(index, row) {
            this.$confirm('确定要撤回房源吗, 是否继续?', '撤回房源', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                let hosueStateForm = {
                    houseId: row.houseId,
                    houseStateId: 3
                }
                updateHouseState(hosueStateForm).then(response => {
                    if (response.success) {
                        this.$message.success(response.message)
                        this.getList()
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '撤回失败！'
                });
            });
        },
        handleRepublic(index, row) {
            let hosueStateForm = {
                houseId: row.houseId,
                houseStateId: 1
            }
            updateHouseState(hosueStateForm).then(response => {
                if (response.success) {
                    this.$message.success(response.message)
                    this.getList()

                }
            })
        }
    }
}
</script>

<style lang="scss">
div.info-container {
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

.el-header {
    text-align: center;
    p {
        text-align: left
    }
}

.result-title {
    font-size: 30px;
}

.vote-result-footer {
    display: flex;
    justify-content: space-around;
    background: #EBEEF5;
}
</style>
