<template>
  <div class="app-container" style="
    padding-left: 20%;
    padding-right: 30%;
    padding-top: 5%;
">
    <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="24">
          <el-form-item label="单位" prop="custname">
            <el-select v-model="form.custid" placeholder="请选择">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="标题">
            <el-input v-model="form.title"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="描述">
            <el-input type="textarea" :rows="4" v-model="form.descs"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="预定时间">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="form.booktime" style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="创建时间">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="form.createtime" style="width: 100%;"></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>

      <el-form-item>
        <el-button type="primary" @click="onSubmit()">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import * as util from '@/utils/index'
  export default {
    created() {
      axios.post('/api/customers/getAll').then(response => {
        let data = response.data.res
        let i = 0
        while (data[i]) {
          let option = {
            value: '',
            label: '',
          }
          option.value = data[i].id
          option.label = data[i].name
          this.options.push(option)
          i++
        }
      })
    },
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm2.checkPass !== '') {
            this.$refs.ruleForm2.validateField('checkPass');
          }
          callback();
        }
      };
      var validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm2.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        form: {
          "custid": '',
          "title": "",
          "descs": "",
          "booktime": "",
          "createtime": "",
        },
        ruleForm2: {
          user: '',
          region: '',
          name: '',
          pass: '',
          checkPass: '',
          radio: '',
          date: '',
          value: '',
        },
        options: [],
        rules2: {
          name: [{
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          }, ],
          date: [{
            required: true,
            message: '请选择日期',
            trigger: 'blur'
          }, ],
          sex: [{
            required: true,
            message: '请选择性别',
            trigger: 'blur'
          }, ],
          role: [{
            required: true,
            message: '请选择角色',
            trigger: 'blur'
          }, ],
          pass: [{
            required: true,
            validator: validatePass,
            trigger: 'blur'
          }],
          checkPass: [{
            required: true,
            validator: validatePass2,
            trigger: 'blur'
          }],
        }
      };
    },
    methods: {
      onSubmit() {
        if (true) {
          axios.post('/api/bookinfo/add', {
            "custid": this.form.custid,
            "title": this.form.title,
            "descs": this.form.descs,
            "booktime": util.parseTime(this.form.booktime),
            "createtime": util.parseTime(this.form.createtime)
          }).then(response => {
            this.$notify({
              title: '成功',
              message: '新增预定成功',
              type: 'success',
              duration: 3000
            })
            this.$router.push('/book/list')
            console.log(response.data)
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }

</script>
