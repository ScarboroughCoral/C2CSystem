<template>
  <div class="dashboard-container">
    <!--<div class="dashboard-text">name:{{name}}</div>-->
    <!--<div class="dashboard-text">roles:<span v-for='role in roles' :key='role'>{{role}}</span></div>-->
    <img class="dashboard-image" :src="home"/>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import home from '@/assets/home_images/home.png'

export default {
  name: 'dashboard',
  data() {
    return {
      home
    }
  },
  computed: {
    ...mapGetters([
      'name',
      'roles'
    ])
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
  &-image{
    width: 1680px;
  }
}
</style>
