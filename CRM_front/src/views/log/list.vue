<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-select clearable style="width: 110px" class="filter-item" v-model="listQuery.searchName" placeholder="查询字段">
        <el-option label="描述" value="descs"></el-option>
        <el-option label="标题" value="title"></el-option>
      </el-select>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="搜索框" v-model="listQuery.search">
      </el-input>
      <el-select @change='handleFilter' style="width: 110px" class="filter-item" v-model="listQuery.orderName" placeholder="查询字段">
        <el-option label="序号" value="id"></el-option>
        <el-option label="创建日期" value="createtime"></el-option>
        <!-- <el-option label="性别" value="sex"></el-option> -->
      </el-select>
      <el-select @change='handleFilter' style="width: 120px" class="filter-item" v-model="listQuery.order">
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" v-waves icon="el-icon-search" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">{{$t('table.add')}}</el-button>
      <el-button class="filter-item" type="primary" :loading="downloadLoading" v-waves icon="el-icon-download" @click="handleDownload">{{$t('table.export')}}</el-button>
      <el-checkbox class="filter-item" style='margin-left:15px;' @change='tableKey=tableKey+1' v-model="showReviewer">{{$t('table.reviewer')}}</el-checkbox>
    </div>
    <el-row>
      <el-col :span="4">
        <div class="block">
          <el-date-picker v-model="listQuery.start" type="datetime" placeholder="起始日期">
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span="4">
        <div class="block">
          <el-date-picker v-model="listQuery.over" type="datetime" placeholder="结束日期">
          </el-date-picker>
        </div>
      </el-col>
    </el-row>

    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row
      style="width: 100%;margin-top:2%;">
      <el-table-column align="center" :label="$t('table.id')" width="65">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column width="170px" align="center" label="创建日期">
        <template slot-scope="scope">
          <span>{{scope.row.createtime}}</span>
        </template>
      </el-table-column>
      <el-table-column width="170px" align="center" label="客户名称">
        <template slot-scope="scope">
          <span>{{scope.row.cust.name}}</span>
        </template>
      </el-table-column>
      <el-table-column width="150px" align="center" label="标题">
        <template slot-scope="scope">
          <span>{{scope.row.title}}</span>
        </template>
      </el-table-column>
      <el-table-column min-width="150px" align="center" label="描述">
        <template slot-scope="scope">
          <span class="link-type" @click="handleUpdate(scope.row)">{{scope.row.descs}}</span>
          <!-- <el-tag>金桥文苑</el-tag> -->
        </template>
      </el-table-column>
      <!-- <el-table-column width="170px" align="center" label="预定日期">
        <template slot-scope="scope">
          <span>{{scope.row.booktime}}</span>
        </template>
      </el-table-column> -->
      <el-table-column width="110px" v-if='showReviewer' align="center" :label="$t('table.reviewer')">
        <template slot-scope="scope">
          <span style='color:red;'>{{scope.row.reviewer}}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column width="80px" :label="$t('table.importance')">
        <template slot-scope="scope">
          <svg-icon v-for="n in +scope.row.importance" icon-class="star" class="meta-item__icon" :key="n"></svg-icon>
        </template>
      </el-table-column> -->
      <!-- <el-table-column align="center" label="绑定访客数" width="95">
        <template slot-scope="scope">
          <span class="link-type">{{scope.row.visiter_num}}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column class-name="status-col" :label="$t('table.status')" width="100">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag>
        </template>
      </el-table-column> -->
      <el-table-column align="center" :label="$t('table.actions')" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
          <!-- <el-button v-if="scope.row.status!='published'" size="mini" type="success"  @click="handleModifyStatus(scope.row,'published')">访客记录
          </el-button> -->
          <!-- <el-button v-if="scope.row.status!='draft'" size="mini" @click="handleModifyStatus(scope.row,'draft')">{{$t('table.draft')}}
          </el-button> -->
          <el-button v-if="scope.row.status!='deleted'" size="mini" type="danger" @click="handleDelete(scope.row)">{{$t('table.delete')}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="listQuery.page"
        :page-sizes="[5,10,20,30, 50]" :page-count="listQuery.total" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <el-dialog title="更新" :visible.sync="dialogFormVisible" width="60%" :before-close="handleClose">
      <div class="app-container">
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
            <!-- <el-col :span="12">
              <el-form-item label="预定时间">
                <el-date-picker type="datetime" placeholder="选择日期" v-model="form.booktime" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col> -->
            <el-col :span="12">
              <el-form-item label="创建时间">
                <el-date-picker type="datetime" placeholder="选择日期" v-model="form.createtime" style="width: 100%;"></el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item>
            <el-button type="primary" @click="updateForm('ruleForm2')">更新</el-button>
            <el-button @click="resetForm('ruleForm2')">重置</el-button>
          </el-form-item>
        </el-form>
      </div>

    </el-dialog>

    <el-dialog title="Reading statistics" :visible.sync="dialogPvVisible">
      <el-table :data="pvData" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="key" label="Channel"> </el-table-column>
        <el-table-column prop="pv" label="Pv"> </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogPvVisible = false">{{$t('table.confirm')}}</el-button>
      </span>
    </el-dialog>

  </div>
</template>

<script>
  import * as util from '@/utils/index'
  import {
    fetchPv,
    createArticle,
    updateArticle
  } from '@/api/article'
  import {
    createUser,
    createAddress,
    getFaceId,
    createUserAddress,
    fetchList
  } from '@/api/manager'
  import waves from '@/directive/waves' // 水波纹指令
  import {
    parseTime
  } from '@/utils'
  import baseURL from '../../../config/api'

  const buildings = [{
      key: '1',
      display_name: '一幢'
    },
    {
      key: '2',
      display_name: '二幢'
    },
    {
      key: '3',
      display_name: '三幢'
    },
    {
      key: '4',
      display_name: '四幢'
    },
    {
      key: '5',
      display_name: '五幢'
    },
    {
      key: '6',
      display_name: '六幢'
    },
    {
      key: '7',
      display_name: '七幢'
    },
    {
      key: '8',
      display_name: '八幢'
    },
    {
      key: '9',
      display_name: '九幢'
    },
  ]

  const units = [{
      key: '1',
      display_name: '一单元'
    },
    {
      key: '2',
      display_name: '二单元'
    },
    {
      key: '3',
      display_name: '三单元'
    },
    {
      key: '4',
      display_name: '四单元'
    },
    {
      key: '5',
      display_name: '五单元'
    },
  ]

  // arr to obj ,such as { CN : "China", US : "USA" }
  // const calendarTypeKeyValue = calendarTypeOptions.reduce((acc, cur) => {
  //   acc[cur.key] = cur.display_name
  //   return acc
  // }, {})

  export default {
    name: 'complexTable',
    directives: {
      waves
    },
    data() {
      return {
        options: [],
        form: '',
        uploadUrl: baseURL + "/face/upload",
        imageUrl: '',
        active: 1,
        tableKey: 0,
        list: null,
        total: null,
        listLoading: true,
        listQuery: {
          "t1": null,
          "t2": 0,
          "t3": 0,
          "s1": null,
          "s2": null,
          "s3": null,
          "total": 100,
          "limit": 5,
          "page": 1,
          "offset": 0,
          "search": "",
          "searchName": "descs",
          "order": "ASC",
          "orderName": "id",
          "start": null,
          "over": null,
        },
        importanceOptions: [1, 2, 3],
        // calendarTypeOptions,
        buildings,
        units,
        sortOptions: [{
          label: '正序',
          key: 'ASC'
        }, {
          label: '逆序',
          key: 'DESC'
        }],
        statusOptions: ['published', 'draft', 'deleted'],
        showReviewer: false,
        temp: {
          name: '',
          phone: '',
          email: '',
          password: '',
        },
        temp2: {
          building_id: '',
          unit_id: '',
          room_id: '',
        },
        temp3: {
          pic_path: '',
        },
        temp4: {
          user: '',
          face_id: '',
          address: '',
        },
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: 'Edit',
          create: 'Create'
        },
        dialogPvVisible: false,
        pvData: [],
        rules: {
          type: [{
            required: false,
            message: 'type is required',
            trigger: 'change'
          }],
          timestamp: [{
            type: 'date',
            required: false,
            message: 'timestamp is required',
            trigger: 'change'
          }],
          title: [{
            required: false,
            message: 'title is required',
            trigger: 'blur'
          }]
        },
        downloadLoading: false
      }
    },
    filters: {
      statusFilter(status) {
        const statusMap = {
          published: 'success',
          draft: 'info',
          deleted: 'danger'
        }
        return statusMap[status]
      },
      typeFilter(type) {
        return calendarTypeKeyValue[type]
      }
    },
    created() {
      this.getList()
    },
    mounted() {
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
    methods: {
      submit() {
        let user;
        let address;
        let face_id;
        getFaceId({
          'pic_path': this.temp3.pic_path
        }).then(res => {
          face_id = res.data.face_id;
          createUser({
            'name': this.temp.name,
            'email': this.temp.email,
            'phone': this.temp.phone,
            'password': this.temp.password,
            'face_id': face_id

          }).then(response => {
            console.log(response.data);
            user = response.data.data;
            createAddress({
              'building_id': this.temp2.building_id,
              'unit_id': this.temp2.unit_id,
              'room_id': this.temp2.room_id,
            }).then(res => {
              console.log(res.data);
              address = res.data.data;
              createUserAddress(address.id, {
                'user_id': user.id,
                'role_id': 5,
              }).then(res => {
                console.log(res);
                console.log(this.list);
                // this.list.push(res.data.data);
                this.getList();
                this.dialogFormVisible = false
                this.$notify({
                  title: '成功',
                  message: '业主添加成功',
                  type: 'success',
                  duration: 2000
                })
              })
            })
          })
        })
      },
      handleAvatarSuccess(res, file) {
        console.log(res);
        this.imageUrl = URL.createObjectURL(file.raw);
        this.temp3.pic_path = res.pic_path;
        // console.log(this.imageUrl2);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      next() {
        if (this.active++ > 2) this.active = 0;
      },
      getList() {
        this.listLoading = true
        if (this.listQuery.start != null) {
          this.listQuery.start = util.parseTime(this.listQuery.start)
          this.listQuery.over = util.parseTime(this.listQuery.over)
        }
        axios.post('api/logs/getAllSelect', this.listQuery).then(response => {
          //   console.log(response.data.data[0].address.id)
          this.list = response.data.res.object
          if (response.data.res.total % this.listQuery.limit != 0)
            this.listQuery.total = Math.floor(response.data.res.total / this.listQuery.limit) + 1
          else
            this.listQuery.total = Math.floor(response.data.res.total / this.listQuery.limit)
          // this.total = response.data.total
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        // this.listQuery.order = listQuery.order
        this.getList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val;
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val;
        this.listQuery.offset = (val - 1) * this.listQuery.limit;
        console.log(JSON.stringify(this.listQuery))
        this.getList()
      },
      handleModifyStatus(row, status) {
        this.$message({
          message: '操作成功',
          type: 'success'
        })
        row.status = status
      },
      resetTemp() {
        this.temp = {
          id: undefined,
          importance: 1,
          remark: '',
          timestamp: new Date(),
          title: '',
          status: 'published',
          type: ''
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.temp.id = parseInt(Math.random() * 100) + 1024 // mock a id
            this.temp.author = 'vue-element-admin'
            createArticle(this.temp).then(() => {
              this.list.unshift(this.temp)
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleUpdate(row) {
        this.temp = Object.assign({}, row) // copy obj
        this.temp.timestamp = new Date(this.temp.timestamp)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        // this.ruleForm2=this.list[row]
        this.form = row
        console.log(row)
        // console.log(this.list)
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateForm() {
        axios.post('/api/logs/update', {
          "id": this.form.id,
          "custid": this.form.custid,
          "title": this.form.title,
          "descs": this.form.descs,
          // "booktime": util.parseTime(this.form.booktime),
          "createtime": util.parseTime(this.form.createtime)
        }).then(response => {
          this.$notify({
            title: '成功',
            message: '更新用户成功',
            type: 'success',
            duration: 2000
          })
          this.dialogFormVisible = false
          this.$router.push('/log/list')
          console.log(response.data)
        })
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.temp)
            tempData.timestamp = +new Date(tempData.timestamp) // change Thu Nov 30 2017 16:41:05 GMT+0800 (CST) to 1512031311464
            updateArticle(tempData).then(() => {
              for (const v of this.list) {
                if (v.id === this.temp.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.temp)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete(row) {
        this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
        const index = this.list.indexOf(row)
        console.log(index)
        axios.post('/api/logs/delete?id=' + row.id).then(response => {

        })
        this.list.splice(index, 1)
      },
      handleFetchPv(pv) {
        fetchPv(pv).then(response => {
          this.pvData = response.data.pvData
          this.dialogPvVisible = true
        })
      },
      handleDownload() {
        this.downloadLoading = true
        import ('@/vendor/Export2Excel').then(excel => {
          const tHeader = ['timestamp', 'title', 'type', 'importance', 'status']
          const filterVal = ['timestamp', 'title', 'type', 'importance', 'status']
          const data = this.formatJson(filterVal, this.list)
          excel.export_json_to_excel(tHeader, data, 'table-list')
          this.downloadLoading = false
        })
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j])
          } else {
            return v[j]
          }
        }))
      }
    }
  }

</script>
<style scoped>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }

  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }

</style>
