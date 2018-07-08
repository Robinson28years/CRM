<template>
  <div class="app-container" style="
    padding-left: 20%;
    padding-right: 30%;
    padding-top: 5%;
">
    <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
      <el-row>
        <el-col :span="12">
          <el-form-item label="公司名称">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="申请类型">
            <el-select v-model="form.type" placeholder="请选择申请类型">
              <el-option label="企业" value="1"></el-option>
              <el-option label="院校" value="2"></el-option>
              <el-option label="合资" value="3"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="公司背景">
            <el-select v-model="form.back" placeholder="请选择公司背景">
              <el-option label="民营" value="1"></el-option>
              <el-option label="国营" value="2"></el-option>
              <el-option label="合资" value="3"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否上市">
            <el-select v-model="form.listed" placeholder="请选择活动区域">
              <el-option label="是" value="1"></el-option>
              <el-option label="否" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="注册资本">
            <el-input v-model="form.capital"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="年销售额">
            <el-input v-model="form.sales"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="企业规模">
            <el-input v-model="form.scale"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="测试人员">
            <el-input v-model="form.no"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="公司网址">
            <el-input v-model="form.url"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮政编码">
            <el-input v-model="form.code"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="公司地址">
            <el-input v-model="form.address"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="主营产品">
            <el-input type="textarea" :rows="4" v-model="form.products"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主要服务">
            <el-input type="textarea" :rows="4" v-model="form.services"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="客户状态">
            <el-select v-model="form.status" placeholder="请选择客户状态">
              <el-option label="潜在客户" value="1"></el-option>
              <el-option label="正式客户" value="2"></el-option>
              <el-option label="放弃客户" value="3"></el-option>
              <el-option label="签约客户" value="4"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="指派人">
            <el-input v-model="form.userid"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="创建时间">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="form.date" style="width: 100%;"></el-date-picker>
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
          name: '',
          type: '',
          back: '',
          listed: '',
          capital: '',
          sales: '',
          scale: '',
          no: '',
          url: '',
          code: '',
          address: '',
          products: '',
          services: '',
          status: '',
          userid:'',
          date:''
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
        options: [{
          value: 'admin',
          label: '管理员'
        }],
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
        // this.$refs[formName].validate((valid) => {
          if (true) {
            axios.post('/api/customers/add', {
              "type": this.form.type,
              "name": this.form.name,
              "sales": this.form.sales,
              "scale": this.form.scale,
              "tester": this.form.no,
              "url": this.form.url,
              "zipcode": this.form.code,
              "address": this.form.address,
              "products": this.form.products,
              "services": this.form.services,
              "userid": this.form.userid,
              "newcolumn1": this.form.status,
              "background": this.form.back,
              "capital": this.form.capital,
              "listed": this.form.listed,
              "createtime": util.parseTime(this.form.date)
            }).then(response => {
              this.$notify({
                title: '成功',
                message: '添加用户成功',
                type: 'success',
                duration: 2000
              })
              this.$router.push('/customer/list')
              console.log(response.data)
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        // });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }

</script>
