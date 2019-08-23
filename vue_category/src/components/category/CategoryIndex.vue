<!-- 管理页面主页面 -->
<template>
  <el-container>
    <el-aside style="width: 200px">
      <switch></switch>
      <SideMenu></SideMenu>
    </el-aside>
    <el-main>
      <Category class="category-area"></Category>
    </el-main>
  </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import Category from './Category'
export default {
  name: 'AppLibrary',
  components: {SideMenu, Category}
}
</script>

<style scoped>
</style>
