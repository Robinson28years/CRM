<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="住址" v-model="listQuery.title">
      </el-input>
      <el-select clearable style="width: 90px" class="filter-item" v-model="listQuery.importance" placeholder="楼幢">
        <el-option v-for="item in importanceOptions" :key="item" :label="item" :value="item">
        </el-option>
      </el-select>
      <!-- <el-select clearable class="filter-item" style="width: 130px" v-model="listQuery.type" :placeholder="$t('table.type')">
        <el-option v-for="item in  calendarTypeOptions" :key="item.key" :label="item.display_name+'('+item.key+')'" :value="item.key">
        </el-option>
      </el-select> -->
      <el-select @change='handleFilter' style="width: 140px" class="filter-item" v-model="listQuery.sort" >
        <el-option v-for="item in sortOptions" :key="item.key" :label="item.label" :value="item.key">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" v-waves icon="el-icon-search" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="el-icon-edit">{{$t('table.add')}}</el-button>
      <el-button class="filter-item" type="primary" :loading="downloadLoading" v-waves icon="el-icon-download" @click="handleDownload">{{$t('table.export')}}</el-button>
      <el-checkbox class="filter-item" style='margin-left:15px;' @change='tableKey=tableKey+1' v-model="showReviewer">授权者</el-checkbox>
    </div>

    <el-table :key='tableKey' :data="list" v-loading="listLoading" element-loading-text="给我一点时间" border fit highlight-current-row
      style="width: 100%">
      <el-table-column align="center" :label="$t('table.id')" width="65">
        <template slot-scope="scope">
          <span>{{scope.row.id}}</span>
        </template>
      </el-table-column>
      <el-table-column width="170px" align="center" :label="$t('table.date')">
        <template slot-scope="scope">
          <span>{{scope.row.created_at}}</span>
        </template>
      </el-table-column>
      <el-table-column min-width="150px" align="center" label="访问住址">
        <template slot-scope="scope">
          <span class="link-type" @click="handleUpdate(scope.row)">{{scope.row.address.building_id}}幢{{scope.row.address.unit_id}}单元{{scope.row.address.room_id}}</span>
          <el-tag>金桥文苑</el-tag>
        </template>
      </el-table-column>
      <el-table-column width="150px" align="center" label="昵称">
        <template slot-scope="scope">
          <span>{{scope.row.nickname}}</span>
        </template>
      </el-table-column>
      <el-table-column width="110px" align="center" label="授权者">
        <template slot-scope="scope">
          <span>{{scope.row.grantor}}</span>
        </template>
      </el-table-column>
      <el-table-column width="110px" v-if='showReviewer' align="center" label="授权者">
        <template slot-scope="scope">
          <span style='color:red;'>{{scope.row.grantor}}</span>
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
      <el-table-column class-name="status-col" label="角色" width="100">
        <template slot-scope="scope">
            <el-tag type="success">{{scope.row.role}}</el-tag>
          <!-- <el-tag :type="scope.row.status | statusFilter">{{scope.row.status}}</el-tag> -->
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="230" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{$t('table.edit')}}</el-button>
          <el-button v-if="scope.row.status!='published'" size="mini" type="success"  @click="handleModifyStatus(scope.row,'published')">访客记录
          </el-button>
          <!-- <el-button v-if="scope.row.status!='draft'" size="mini" @click="handleModifyStatus(scope.row,'draft')">{{$t('table.draft')}}
          </el-button> -->
          <el-button v-if="scope.row.status!='deleted'" size="mini" type="danger" @click="handleModifyStatus(scope.row,'deleted')">{{$t('table.delete')}}
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination background @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total">
      </el-pagination>
    </div>

    <el-dialog title="添加业主" :visible.sync="dialogFormVisible">
      <el-steps :active="active" align-center>
        <el-step title="步骤 1" description="填写基本信息" icon="el-icon-edit"></el-step>
        <el-step title="步骤 2" description="填写地址信息" icon="el-icon-picture"></el-step>
        <el-step title="步骤 3" description="上传人脸" icon="el-icon-upload"></el-step>
      </el-steps>
      <el-form v-show="active==1" :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="80px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="姓名" prop="title">
          <el-input v-model="temp.name"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="title">
          <el-input v-model="temp.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="title">
          <el-input v-model="temp.email"></el-input>
        </el-form-item>
        <el-form-item label="预设密码" prop="title">
          <el-input v-model="temp.password" type="password"></el-input>
        </el-form-item>
      </el-form>

      <el-form v-show="active==2" :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="80px" style='width: 400px; margin-left:50px;'>
        <el-form-item label="楼幢" prop="type">
          <el-select class="filter-item" v-model="temp2.building_id" placeholder="Please select">
            <el-option v-for="item in  buildings" :key="item.key" :label="item.display_name" :value="item.key">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="单元" prop="type">
          <el-select class="filter-item" v-model="temp2.unit_id" placeholder="Please select">
            <el-option v-for="item in  units" :key="item.key" :label="item.display_name" :value="item.key">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="房号" prop="type">
           <el-input v-model="temp2.room_id"></el-input>
        </el-form-item>
      </el-form>
      <el-form v-show="active==3" :rules="rules" ref="dataForm" :model="temp" label-position="left" label-width="80px" style='width: 400px; margin-left:50px;'>
        <el-form-item prop="type">
          <el-upload
            class="avatar-uploader"
            :action="uploadUrl"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="imageUrl" :src="imageUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
        <el-button v-if="active == 3" type="primary" @click="submit">提交</el-button>
        <el-button v-else type="primary" @click="next">下一步</el-button>
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
import { fetchPv, createArticle, updateArticle } from '@/api/article'
import { createUser,createAddress,getFaceId,createUserAddress,fetchList,fetchVisiterList } from '@/api/manager'
import waves from '@/directive/waves' // 水波纹指令
import { parseTime } from '@/utils'
import baseURL from '../../../config/api'

const buildings = [
  { key: '1', display_name: '一幢' },
  { key: '2', display_name: '二幢' },
  { key: '3', display_name: '三幢' },
  { key: '4', display_name: '四幢' },
  { key: '5', display_name: '五幢' },
  { key: '6', display_name: '六幢' },
  { key: '7', display_name: '七幢' },
  { key: '8', display_name: '八幢' },
  { key: '9', display_name: '九幢' },
]

const units = [
  { key: '1', display_name: '一单元' },
  { key: '2', display_name: '二单元' },
  { key: '3', display_name: '三单元' },
  { key: '4', display_name: '四单元' },
  { key: '5', display_name: '五单元' },
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
      uploadUrl: baseURL+"/face/upload",
      imageUrl:'',
      active:1,
      tableKey: 0,
      list: null,
      total: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined,
        type2: undefined,
        sort: '+id'
      },
      importanceOptions: [1, 2, 3],
      // calendarTypeOptions,
      buildings,
      units,
      sortOptions: [{ label: '正序', key: '+id' }, { label: '逆序', key: '-id' }],
      statusOptions: ['published', 'draft', 'deleted'],
      showReviewer: false,
      temp: {
        name: '',
        phone: '',
        email: '',
        password: '',
      },
      temp2:{
        building_id: '',
        unit_id: '',
        room_id:'',
      },
      temp3:{
        pic_path:'',
      },
      temp4:{
        user:'',
        face_id:'',
        address:'',
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
        type: [{ required: true, message: 'type is required', trigger: 'change' }],
        timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
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
  methods: {
      submit(){
        let user;
        let address;
        let face_id;
        getFaceId({
          'pic_path':this.temp3.pic_path
        }).then(res => {
          face_id = res.data.face_id;
            createUser({
              'name' : this.temp.name,
              'email': this.temp.email,
              'phone': this.temp.phone,
              'password':this.temp.password,
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
                        createUserAddress(address.id,{
                          'user_id':user.id,
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
      fetchVisiterList(this.listQuery).then(response => {
        //   console.log(response.data.data[0].address.id)
        this.list = response.data.data
        // this.total = response.data.total
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
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
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
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
      import('@/vendor/Export2Excel').then(excel => {
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
