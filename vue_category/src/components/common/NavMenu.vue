<template>
    <el-menu
      :default-active="'/index'"
      router
      mode="horizontal"
      background-color="#545c64"
      text-color="white"
      active-text-color="#545c64"
      style="min-width: 1300px">
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        {{ item.navItem }}
      </el-menu-item>
      <a href="#nowhere" style="color: #222;float: right;padding: 20px;"></a>
      <i class="el-icon-menu" style="float:right;font-size: 45px;color: #FFF;padding-top: 8px"></i>
      <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold; color: #FFF">博思软件</span>
    </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/category', navItem: '管理数据字典'}
      ]
    }
  }
}
</script>

<style scoped>
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }
</style>
