<template>
    <div class="app-container">
        <el-form  label-position="left" label-width="100px">
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

                <el-carousel :interval="3000" arrow="always" style="width:600px;" >
                    <el-carousel-item v-for="item in 4" :key="item">
                    <h3>{{ item }}</h3>
                    </el-carousel-item>
                </el-carousel>
            </el-form-item>
            <el-form-item label="出租时间">
            </el-form-item>
            <el-form-item label="房源所在地">
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
    </div>
</template>

<script>
export default {
    data(){
        return {

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
