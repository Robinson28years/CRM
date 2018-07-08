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
          <el-form-item label="文档类型">
            <el-select v-model="form.type" placeholder="请选择文档类型">
              <el-option label="订单" value="订单"></el-option>
              <el-option label="合同" value="合同"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-upload class="upload-demo" action="/api/public/upload/file/" :on-preview="handlePreview" :on-remove="handleRemove" :before-remove="beforeRemove"
            multiple :limit="1" :on-success="handleSuccess" :on-exceed="handleExceed" :file-list="fileList">
            <el-button size="small" type="primary">点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传文件，且不超过500kb</div>
          </el-upload>
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
        fileList: [],
        form: {
          "custid": '',
          "title": "",
          "descs": "",
          "booktime": "",
          "createtime": "",
          type:'',
          url:'',
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
      handleSuccess(response, file, fileList) {
          console.log(fileList[0].response.res)
          this.form.url = fileList[0].response.res
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      onSubmit() {
        if (true) {
          axios.post('/api/files/add', {
              "name":"huang",
            "custid": this.form.custid,
            "title": this.form.title,
            "descs": this.form.descs,
            "type": this.form.type, 
            "url": this.form.url,
            "createtime": util.parseTime(this.form.createtime)
          }).then(response => {
            this.$notify({
              title: '成功',
              message: '新增预定成功',
              type: 'success',
              duration: 3000
            })
            this.$router.push('/file/list')
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
