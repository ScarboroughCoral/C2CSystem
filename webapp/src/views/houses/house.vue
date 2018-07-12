<template>
    <div class="app-container">
        <el-form  label-position="left" label-width="100px">
            <el-form-item label="房源类型">
            <el-tag type="info">{{houseForm.houseTypeDes}}</el-tag>
            </el-form-item>
            <el-form-item label="租金">
            <el-tag type="info">{{houseForm.price}}</el-tag>
            </el-form-item>
            <el-form-item label="房间面积">
            <el-tag type="info">{{houseForm.area}}</el-tag>
            </el-form-item>
            <el-form-item label="房东联系方式">
            <el-tag type="info" >{{houseForm.phoneNumber}}</el-tag>
            </el-form-item>
            <el-form-item label="房间图片">

                <el-carousel :interval="3000" type="card" arrow="always" style="width:900px;" >
                    <el-carousel-item v-for="item in picList" :key="item">
                        <img :src="item" alt="">
                    </el-carousel-item>
                </el-carousel>
            </el-form-item>
            <el-form-item label="出租时间">
                <el-tag type="info">{{houseForm.startTime|dateTimeFilter}}</el-tag>至
                <el-tag type="info">{{houseForm.endTime|dateTimeFilter}}</el-tag>
            </el-form-item>
            <el-form-item label="房源所在地">
                <el-tag type="info">{{houseForm.provinceDesc+houseForm.cityDesc+houseForm.districtDesc}}</el-tag>
            </el-form-item>
            <!-- <el-form-item label="房源简介">
            <el-tag type="info">{{houseForm.houseTypeDes}}</el-tag>
            </el-form-item> -->
            <el-form-item label-width="1500px">
                <el-button type="success" @click="handleOrder">下订单</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>

<script>
import { getHousePics,getSepcificHouse } from "@/api/house";
import { parseTime } from "@/utils/index";
import { setOrder } from "@/api/order";
import Cookies from "js-cookie";
import urls from "urls-js";
export default {
    data(){
        return {
            userId:undefined,
            houseId:undefined,
            houseForm:{
                userId:undefined,
                price:0,
                houseTypeID:undefined,
                houseStateID:1,
                holdNum:0,
                houseDesc:'',
                houseArea:0,
                houseAddr:'',
                houseTypeDes:''
            },

            houseTypes:[
                {
                value:1,
                label:'海景房测试'
                }
            ],
            picList:[]
            
        }
    },
    created(){
        this.houseId = urls.parse().hash['houseId'];
        this.userId = Cookies.get('userID')
        this.loadHousePics()
        this.loadHouseInfo()
    },
    methods:{
        loadHousePics(){
            getHousePics(this.houseId).then( response => {
                if (response.success) {
                    for (let index = 0; index < response.data.length; index++) {
                        const element = response.data[index];
                        this.picList.push(element)
                    }
                }
            })
        },
        loadHouseInfo(){
            getSepcificHouse(this.houseId).then( response => {
                if (response.success) {
                    this.houseForm = response.data
                }
            })
        },
        handleOrder(){
            let userId = this.userId,
                houseId = this.houseId
            let orderForm = {
                userId,
                houseId
            }
            this.$confirm('确定要下订单吗, 是否继续?', '确认订单', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
            }).then(() => {
                setOrder(orderForm).then(response => {
                    if (response.success) {
                        this.$message({
                            type: 'success',
                            message: response.message
                        });
                    }
                })
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '未成功下订单'
                });          
            });
        }
    },
    filters:{
        dateTimeFilter(val){
            return parseTime(val,'{y}年{m}月{d}日')
        }
    }
}
</script>

<style lang="scss" scoped>
.el-carousel__item h3 {
color: #475669;
font-size: 18px;
opacity: 0.75;
line-height: 300px;
margin: 0;
}

.el-carousel__item:nth-child(2n) {
background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
background-color: #d3dce6;
}
</style>
