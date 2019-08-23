<template>
  <div>
    <i class="el-icon-circle-plus-outline" @click="dialogFormVisible = true"></i>
    <el-dialog title="添加/修改数据字典" :visible.sync="dialogFormVisible" @close="clear">
    <hr>
    <h2  style="color: blue;text-align: left;font-style: italic">基本信息</h2>
    <hr>
      <el-form v-model="form" style="text-align: left" ref="dataForm">
        <el-form-item label="字典名称" :label-width="formLabelWidth" prop="name" :rules="[{ required: true, message: '字典名称不能为空'}]">
          <el-input v-model="form.name" autocomplete="off" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="字典类型" :label-width="formLabelWidth" prop="category">
          <el-input v-model="form.category" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="字典值" :label-width="formLabelWidth" prop="value">
          <el-input v-model="form.value" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="是否启用" :label-width="formLabelWidth" prop="press">
          <el-radio v-model="radio" label="1">是</el-radio>
          <el-radio v-model="radio" label="2">否</el-radio>
        </el-form-item>
        <el-form-item label="备注信息" :label-width="formLabelWidth" prop="remark">
          <el-input type="textarea" v-model="form.remark" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Edit',
  data () {
    return {
      dialogFormVisible: false,
      form: {
        name: '',
        category: '',
        value: '',
        press: '',
        remark: ''
      },
      formLabelWidth: '120px'
    }
  },
  methods: {
    clear () {
      this.form = {
        name: '',
        category: '',
        value: '',
        press: '',
        remark: ''
      }
    },
    onSubmit () {
      this.$axios
        .post('/addupdate', {
          name: this.form.name,
          category: this.form.category,
          value: this.form.value,
          press: this.form.press,
          remark: this.form.remark
        })
        .then(resp => {
          if (resp && resp.status === 200) {
            this.dialogFormVisible = false
            this.$emit('onSubmit')
          }
        })
    }
  }
}
</script>

<style scoped>
.el-icon-circle-plus-outline {
  margin: 50px 0 0 20px;
  font-size: 100px;
  float: left;
  cursor: pointer;
}
</style>
