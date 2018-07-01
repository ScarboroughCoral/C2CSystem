<template>
  <div class="app-container">
    <el-tabs type="border-card">
      <el-tab-pane label="管理员管理">
        <admin></admin>
      </el-tab-pane>
      <el-tab-pane label="日志管理">
        <log></log>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
    import Admin from './admin/index'
    import Log from './log/index'
    export default {
      name: 'System',
      components: {
        Admin,
        Log
      },
      created(){
        this.$alert('该功能暂未开放！!',{
            callback:function () {
              location.href = '/#/dashboard'
            }
        });
      }
    }
</script>

<style scoped>

</style>
