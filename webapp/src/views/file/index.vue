<template>
  <div class="app-container">
    <el-tabs type="border-card">
      <el-tab-pane label="设置文件夹" >
        <directory></directory>
      </el-tab-pane>
      <!-- <el-tab-pane label="压缩文件">压缩文件</el-tab-pane>
      <el-tab-pane label="压临时文件">压临时文件</el-tab-pane> -->
      <el-tab-pane label="更新客户端">此功能暂未开放</el-tab-pane>
      <!-- <el-tab-pane label="显示按钮">显示按钮</el-tab-pane> -->
    </el-tabs>
  </div>
</template>

<script>
    import Directory from './directory/index.vue'

    export default {
      name: 'index',
      components: {
        Directory,
      }
    }
</script>

<style scoped>

</style>
