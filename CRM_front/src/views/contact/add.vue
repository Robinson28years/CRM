<template>
  <div class="app-container" style="
    padding-left: 20%;
    padding-right: 30%;
    padding-top: 5%;
">
    <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="24">
          <el-form-item label="公司名称" prop="custname">
            <el-select v-model="form.custid" placeholder="请选择">
              <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户姓名">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户部门">
            <el-input v-model="form.dept"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户职位">
            <el-select v-model="form.dgree2" placeholder="请选择职位">
              <el-option label="总经理" value="shanghai"></el-option>
              <el-option label="销售经理" value="beijing"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户电话">
            <el-input v-model="form.telephone"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户手机">
            <el-input v-model="form.mobile"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="客户邮箱">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户QQ">
            <el-input v-model="form.qq"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="技术程度">
            <el-select v-model="form.degree" placeholder="请选择技术程度">
              <el-option label="很懂" value="1"></el-option>
              <el-option label="一般" value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="其他信息">
            <el-input v-model="form.moreinfo"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
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
          "name": "",
          "dept": "",
          "telephone": "",
          "mobile": "",
          "email": "",
          "qq": "",
          "degree": '',
          "moreinfo": "",
        //   "title": '',
        //   "bookid": '',
          "createtime": ""
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
          axios.post('/api/contacts/add', {
            "custid": this.form.custid,
            "name": this.form.name,
            "dept": this.form.dept,
            "telephone": this.form.telephone,
            "mobile": this.form.mobile,
            "email": this.form.email,
            "qq": this.form.qq,
            "degree": this.form.degree,
            "moreinfo": this.form.moreinfo,
            // "title": 321,
            // "bookid": 312,
            "createtime": util.parseTime(this.form.createtime)
          }).then(response => {
            this.$notify({
              title: '成功',
              message: '新增联络成功',
              type: 'success',
              duration: 3000
            })
            this.$router.push('/contact/list')
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
