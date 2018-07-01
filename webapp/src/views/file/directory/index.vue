<template>
  <div class="tab-container">
    <el-upload
      class="upload-demo"
      ref="upload"
      action="/vote/file/upload/"
      list-type="picture"
      :multiple="true"
      :before-upload="beforeAvatarUpload"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      :on-success="handleSuccess"
      :on-error="handleError"
      :auto-upload="false">


      <el-button slot="trigger" size="small" type="primary" v-show="false"  disabled>选取文件</el-button>
      <el-button style="margin-left: 10px;"  size="small" type="success" @click="getFolderContent">刷新</el-button>

      <el-button icon="el-icon-caret-right" size="small" type="success" @click="handleMeeting">创建会议</el-button>
      <el-button style="margin-left: 10px;" size="small" type="primary" @click="submitUpload">上传到服务器</el-button>
      <el-button style="margin-left: 10px;" size="small" type="warning" @click="clearList">清空列表</el-button>
    </el-upload>
    <el-tag type="primary" size="large">当前会议ID：{{meetingId}}请将文件保存在以当前会议ID命名的文件夹下</el-tag>
    <el-tag type="danger" size="large" v-if="!meetingId">当前无会议请创建会议！</el-tag>
      <div slot="tip" class="el-upload__tip">上传文件列表</div>
    <!-- <uploader :options="uploadOptions()" class="uploader-example">
      <uploader-unsupport></uploader-unsupport>
      <uploader-drop>
        <p>将文件扔到这里或</p>
        <uploader-btn>选择文件</uploader-btn>
        <uploader-btn :attrs="attrs">选择图片</uploader-btn>
        <uploader-btn :directory="true">选择文件夹</uploader-btn>
      </uploader-drop>
      <uploader-list></uploader-list>
    </uploader> -->

   <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" top="10vh">
      <!-- 创建会议 -->
    <el-form label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
      <el-form-item label="会议名称">
        <el-input v-model="meetingForm.meetingName"></el-input>
      </el-form-item>
      <el-form-item label="会议时间">
        <el-date-picker
          v-model="meetingForm.meetingTime"
          type="datetime"
          placeholder="选择日期时间"
          default-time="12:00:00">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="会议地点">
        <el-input v-model="meetingForm.meetingAddr"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="handleCancel">取消</el-button>
      <el-button type="primary" @click="handleCreateMeeting">确认</el-button>
    </div>
  </el-dialog>
  </div>
</template>

<script>
import { upload,getFolder } from "@/api/file";
import Cookies from "js-cookie";
import { createMeeting } from "@/api/meeting";
export default {
  name: 'index',
  data() {
    return {
      files:{},
      attrs: {
        accept: '*'
      },
      dialogStatus:'meetingCreate',
      dialogFormVisible:false,
      isCreateMeeting:false,
      meetingId:undefined,
      textMap:{
        meetingCreate:'创建会议'
      },
      meetingForm:{
        meetingTime:'',
        meetingName:'',
        meetingAddr:''
      },
      meetingResponse:{},
      ruleForm: {
        name: '',
        region: '',
        date1: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: '',
      },
        fileList: [],
      rules: {
        name: [
          { required: true, message: '请输入活动名称', trigger: 'blur' },
          { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
        ],
        region: [
          { required: true, message: '请选择活动区域', trigger: 'change' }
        ],
        date1: [
          { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
        ],
        date2: [
          { type: 'date', required: true, message: '请选择时间', trigger: 'change' }
        ],
        type: [
          { type: 'array', required: true, message: '请至少选择一个活动性质', trigger: 'change' }
        ],
        resource: [
          { required: true, message: '请选择活动资源', trigger: 'change' }
        ],
        desc: [
          { required: true, message: '请填写活动形式', trigger: 'blur' }
        ]
      }
    }
  },
  created(){
    this.isCreateMeeting = Cookies.get('isCreateMeeting')
    this.meetingId = Cookies.get('meetingId')
  },
  methods: {
    getFolderContent(){
      this.fileList=[]
      getFolder(this.meetingId).then(response => {
        if (response.success) {
          for (let index = 0; index < response.data.length; index++) {
            const element = response.data[index];
            if (element.indexOf('.')>-1) {
              this.fileList.push({
                name:element,
                url:'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1530349351265&di=f77a9213bf2b45026c6c81869dc644d8&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F00%2F07%2F85%2F23%2F59310eab886e2.png'
              });
            } else {
              this.fileList.push({
                name:element,
                url:'https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1530339201&di=7687de30143868ea2d9e572517894128&src=http://image.tupian114.com/20140417/13325010.png'
              });
            }
          }

        }
      })
    },
    uploadOptions(){

      let options= {
        // https://github.com/simple-uploader/Uploader/tree/develop/samples/Node.js
        target: function (file) {
          // console.log(file.relativePath)
        },
        testChunks: false,
        query:{
          meetingId:this.meetingId
        }
      }
      return options
    },
    handleCancel(){
      this.dialogFormVisible = false
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    submitUpload() {
      console.log(this.fileList)
        if (!this.isCreateMeeting) {
          this.$message.warning("请首先创建会议！")
          return;
        }

        const loading = this.$loading({
            lock: true,
            text: '拼命上传中……',
            spinner: 'el-icon-loading',
            background: 'rgba(0, 0, 0, 0.7)'
          });
        upload(this.meetingId).then(response =>{
          loading.close();
          if (response.success) {
            this.$message.success(response.message)
          }else{
            this.$message.error(response.message)
          }
        })

        // this.$refs.upload.submit();
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleSuccess(response){
      this.$message(response.message)
      
    },
    handleError(){
      this.$message("上传失败，未知错误！")
    },

    handleMeeting(){
      this.dialogStatus = 'meetingCreate'
      this.dialogFormVisible = true;
    },
    handleCreateMeeting(){
      let self = this
      Cookies.set('meetingForm',this.meetingForm)
      this.meetingForm.meetingTime = new Date(this.meetingForm.meetingTime).getTime()
      createMeeting(this.meetingForm).then(response => {
        self.meetingResponse = response.data
        let meetingId = response.data.meeting_id
        this.meetingId = meetingId
        Cookies.set('meetingForm',this.meetingForm)
        Cookies.set('meetingId',meetingId)
        Cookies.set('isCreateMeeting',true)
        Cookies.set('meetingStatus',0)
        this.isCreateMeeting = true
        this.dialogFormVisible = false
        this.$message.success("创建会议成功！如需修改会议信息，请在选票模块开始会议时修改！")
      });
    },
    beforeAvatarUpload(file) {
      const loading = this.$loading({
          lock: true,
          text: '拼命上传中……',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });
      let newData = new FormData()
      newData.append('file',file)
      newData.append('meetingId',this.meetingId)
      upload(newData).then(response =>{
        loading.close();
        if (response.success) {
          this.$message.success(response.message)
        }else{
          this.$message.error(response.message)
        }
      })
      return false
    },
    clearList(){
      this.$refs.upload.clearFiles();
    },
  }
}
</script>

<style lang="scss" scoped>
  .upload-demo{
    min-height: 500px;
  }
  .uploader-example {
    width: 100%;
    padding: 15px;
    margin: 40px auto 0;
    font-size: 12px;
    box-shadow: 0 0 10px rgba(0, 0, 0, .4);
  }
  .uploader-example .uploader-btn {
    margin-right: 4px;
  }
  .uploader-example .uploader-list {
    max-height: 440px;
    overflow: auto;
    overflow-x: hidden;
    overflow-y: auto;
  }
</style>
