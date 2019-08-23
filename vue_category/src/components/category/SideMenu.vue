<!-- 管理页面的侧边栏 -->
<template>
  <el-menu default-active="1-4-1" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse"
  background-color="#545c64" text-color="#fff">
  <el-submenu index="1">
    <template slot="title">
      <i class="el-icon-location"></i>
      <span slot="title">系统管理</span>
    </template>
    <el-menu-item-group>
      <el-menu-item index="1-1">字典表管理</el-menu-item>
      <el-menu-item index="1-2">设置</el-menu-item>
    </el-menu-item-group>
  </el-submenu>
  <el-menu-item index="2">
    <i class="el-icon-menu"></i>
    <span slot="title">考试管理</span>
  </el-menu-item>
  <el-menu-item index="3" disabled>
    <i class="el-icon-document"></i>
    <span slot="title">考卷管理</span>
  </el-menu-item>
  <el-menu-item index="4">
    <i class="el-icon-setting"></i>
    <span slot="title">系统设置</span>
  </el-menu-item>
</el-menu>

</template>

<script>
export default {
  name: 'SideMenu',
  data () {
    return {
      isCollapse: false
    }
  }
}
</script>

<style>
  .el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 600px;
  }
</style>
