<template>
  <div>
    <el-form :inline="true" :model="formInline" class="demo-form-inline" style="float:left">
      <el-form-item label="字典名称">
        <el-input type="text" v-model="formInline.name" placeholder="字典名称"></el-input>
      </el-form-item>
      <el-form-item label="字典类型">
        <el-input type="text" v-model="formInline.category" placeholder="字典类型"></el-input>
      </el-form-item>
      <el-form-item label="启用标记">
        <el-input type="text" v-model="formInline.status" placeholder="启用标记"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
      </el-form-item>
    </el-form>
    <edit-form @onSubmit="loadCategories()" ref="edit"></edit-form>
    <div style="float:left">
      <el-button icon="el-icon-plus" size="mini" @click="dialogFormVisible = true" type="primary">新增</el-button>
      <el-button icon="el-icon-search" size="mini" @click="del(sels)" type="danger">删除</el-button>
      <el-button icon="el-icon-edit" size="mini" @click="dialogFormVisible = true" type="danger">编辑</el-button>
      <el-button icon="el-icon-edit" size="mini" @click="loadCategories" type="primary">刷新</el-button>
    </div>
    <!-- 记录数据的表单 -->
    <div>
      <el-table
        ref="multipleTable"
        :data="categories"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        :default-sort="{prop: 'date', order: 'descending'} "
      >
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column prop="name" label="字典名" width="120"></el-table-column>
        <el-table-column prop="category" label="字典类型" sortable width="130"></el-table-column>
        <el-table-column prop="value" label="字典值" width="100"></el-table-column>
        <el-table-column prop="mark" label="标记" width="120"></el-table-column>
        <el-table-column prop="updated_time" label="更新时间" sortable width="180"></el-table-column>
        <el-table-column prop="remark" label="备注信息" width="160"></el-table-column>
        <el-table-column prop="status" label="状态" width="100"></el-table-column>
        <el-table-column prop="operate" label="操作" width="100">
          <template slot-scope="scope">
            <i class="el-icon-plus" @click="dialogFormVisible = true"></i>
            <i class="el-icon-delete" @click="del(scope.row.category_id)"></i>
            <i class="el-icon-edit" @click="findbyid(scope.row.category_id)"></i>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 添加字典弹窗 -->
    <div>
    <el-dialog title="添加数据字典" :visible.sync="dialogFormVisible" @close="clear">
    <hr>
    <h2  style="color: blue;text-align: left;font-style: italic">基本信息</h2>
    <hr>
      <el-form v-model="form" style="text-align: left" ref="dataForm">
        <el-form-item label="字典名称" :label-width="formLabelWidth" prop="name" >
          <el-input v-model="form.name"  placeholder="字典名称" ></el-input>
        </el-form-item>
        <el-form-item label="字典类型" :label-width="formLabelWidth" prop="category" >
          <el-input v-model="form.category" placeholder="字典类型"></el-input>
        </el-form-item>
        <el-form-item label="字典值" :label-width="formLabelWidth" prop="value" :rules="[{ required: true, message: '字典值必须为整数'}]">
          <el-input v-model="form.value" placeholder="字典值" type="int"></el-input>
        </el-form-item>
        <el-form-item label="是否启用" :label-width="formLabelWidth" prop="press">
          <el-radio v-model="form.radio" label="1">是</el-radio>
          <el-radio v-model="form.radio" label="2">否</el-radio>
        </el-form-item>
        <el-form-item label="备注信息" :label-width="formLabelWidth" prop="remark">
          <el-input type="textarea" v-model="form.remark" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">关 闭</el-button>
        <el-button type="primary" @click="save">保 存</el-button>
      </div>
    </el-dialog>
    </div>

    <!-- 修改字典弹窗 -->
    <div>
    <el-dialog title="修改数据字典" :visible.sync="dialogTableVisible" @close="clear">
    <hr>
    <h2  style="color: blue;text-align: left;font-style: italic">基本信息</h2>
    <hr>
      <el-form v-model="upform" style="text-align: left" ref="dataForm">
        <el-form-item label="字典ID" :label-width="formLabelWidth" prop="name" >
          <el-input v-model="upform.id"  placeholder="字典ID" ></el-input>
        </el-form-item>
        <el-form-item label="字典名称" :label-width="formLabelWidth" prop="name" >
          <el-input v-model="upform.name"  placeholder="字典名称" ></el-input>
        </el-form-item>
        <el-form-item label="字典类型" :label-width="formLabelWidth" prop="category" >
          <el-input v-model="upform.category" placeholder="字典类型"></el-input>
        </el-form-item>
        <el-form-item label="字典值" :label-width="formLabelWidth" prop="value" :rules="[{ required: true, message: '字典值必须为整数'}]">
          <el-input v-model="upform.value" placeholder="字典值" type="int"></el-input>
        </el-form-item>
        <el-form-item label="是否启用" :label-width="formLabelWidth" prop="press">
          <el-radio v-model="upform.radio" label="1">是</el-radio>
          <el-radio v-model="upform.radio" label="2">否</el-radio>
        </el-form-item>
        <el-form-item label="备注信息" :label-width="formLabelWidth" prop="remark">
          <el-input type="textarea" v-model="upform.remark" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">关 闭</el-button>
        <el-button type="primary" @click="edit">修改</el-button>
      </div>
    </el-dialog>
    </div>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage4"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="10"
      ></el-pagination>
    </div>
  </div>
</template>

<script>
import Edit from './Edit'
export default {
  name: 'Categpries',
  components: { Edit },
  data () {
    return {
      // 从后台传来的数据字典信息
      categories: [],
      currentPage: 1,
      pagesize: 10,
      // 多选选中值的坐标，用于批量删除
      sels: [],
      // 搜索框数据
      formInline: {
        name: '',
        category: '',
        status: ''
      },
      dialogTableVisible: false,
      dialogFormVisible: false,
      updialogFormVisible: false,
      form: {
        name: '',
        category: '',
        value: '',
        remark: '',
        radio: ''
      },
      upform: {
        id: '',
        name: '',
        category: '',
        value: '',
        remark: '',
        radio: ''
      },
      formLabelWidth: '120px'
    }
  },
  // 页码显示问题
  handleCurrentChange: function (currentPage) {
    this.currentPage = currentPage
    console.log(this.currentPage)
  },
  // 页面加载完成后加载数据
  mounted: function () {
    this.loadCategory()
  },
  methods: {
    // 加载数据方法
    loadCategories () {
      var _this = this
      this.$axios.get('/categories').then(resp => {
        if (resp && resp.status === 200) {
          _this.categories = resp.data
        }
      })
    },
    // 每一行多选选中时触发该方法
    handleSelectionChange (sels) {
      this.sels = sels
    },
    // 搜索功能
    search () {
      var _this = this
      this.$axios
        .post('/search', {
          name: this.formInline.name,
          category: this.formInline.category,
          status: this.formInline.status
        })
        .then(resp => {
          if (resp && resp.status === 200) {
            this.$notify({
              title: '成功',
              message: '查询结果如下',
              type: 'success',
              duration: 1000
            })
            // 若搜索成功则重新刷新页面
            _this.categories = resp.data
          }
        })
    },
    // 增添數據
    save () {
      this.$axios.post('/add', {
        name: this.form.name,
        category: this.form.category,
        value: this.form.value,
        remark: this.form.value,
        radio: this.form.radio
      }).then(resp => {
        // 成功增加数据后刷新页面
        if (resp && resp.status === 200) {
          this.$notify({
            title: '成功',
            message: '数据已成功插入',
            type: 'success',
            duration: 1500
          })
          this.loadCategories()
          this.dialogFormVisible = false
        }
      }).catch(() => {
        this.$message({
          type: 'error',
          message: '数据插入失败',
          duration: 1000
        })
      })
    },
    // 根据所选的id删除相应数据
    del (id) {
      this.$confirm('确认要删除该字典信息吗?', '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // console.log(this.$refs.multipleTable.selection)
          this.$axios.post('/delete', id).then(resp => {
            if (resp && resp.status === 200) {
              this.$notify({
                title: '成功',
                message: '数据已成功删除',
                type: 'success',
                duration: 1000
              })
              // 若删除成功则重新刷新页面
              this.loadCategories()
            }
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除',
            duration: 1000
          })
        })
    },
    // 根据所选id从后台查询数据
    findbyid (id) {
      this.$axios.post('/findbyid', id).then(resp => {
        // 获取数据后显示修改弹窗
        if (resp && resp.status === 200) {
          this.$notify({
            title: '成功',
            message: '查询到数据',
            type: 'success',
            duration: 1000
          })
          this.upform = {
            name: name
            // category: category,
            // value: value,
            // remark: remark
          }
          this.dialogTableVisible = true
        }
      })
    },
    // 编辑数据
    edit () {
      this.$axios.post('/update', {
        category_id: this.upform.id,
        name: this.upform.name,
        category: this.upform.category,
        value: this.upform.value,
        remark: this.upform.value,
        radio: this.upform.radio
      }).then(resp => {
        // 成功修改数据后刷新页面
        if (resp && resp.status === 200) {
          this.$notify({
            title: '成功',
            message: '数据已成功修改',
            type: 'success',
            duration: 1000
          })
          this.loadCategories()
          this.dialogTableVisible = false
        }
      }).catch(() => {
        this.$message({
          type: 'error',
          message: '数据修改失败',
          duration: 1000
        })
      })
    }
  }
}
</script>
