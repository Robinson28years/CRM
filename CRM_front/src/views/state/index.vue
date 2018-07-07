<template>
<el-row style="margin-top:5%;">
	<el-table
      :data="tableData"
      border
      style="width: 100%">
      <!-- <el-table-column
        label="安装时间"
        width="180">
        <template slot-scope="scope">
          <el-icon name="time"></el-icon>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column> -->
	  <el-table-column
		label="门禁设备编号"
		width="150">
	  <template slot-scope="scope">
		  {{ scope.row.long }}
	  </template>
	  </el-table-column>
  	<el-table-column
        label="上次维护日期"
        width="150">
        <template slot-scope="scope">
          <el-icon name="time"></el-icon>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="门禁位置"
       >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>门禁位置: {{ scope.row.name }}</p>
            <p>上次维护日期: {{ scope.row.date }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag>{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
  	<el-table-column
  	  label="异常情况"
  	  width="150">
  	  <template slot-scope="scope">
  		<!-- <el-icon name="time"></el-icon> -->
  		<span style="margin-left: 10px">{{ scope.row.error }}</span>
  	  </template>
  	</el-table-column>
      <el-table-column label="异常处理">
        <template slot-scope="scope">
          <el-button
            size="small"
  		  type="warning"
            @click="handleEdit(scope.$index, scope.row)">现场检测</el-button>
          <el-button
            size="small"
            type="warning"
            @click="handleDelete(scope.$index, scope.row)">通知厂家处理</el-button>
        </template>
      </el-table-column>
    </el-table>
	<el-table
      :data="tableData2"
      border
      style="width: 100%">
      <!-- <el-table-column

        width="180">
        <template slot-scope="scope">
          <el-icon name="time"></el-icon>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column> -->
	  <el-table-column

		width="150">
	  <template slot-scope="scope">
		  {{ scope.row.long }}
	  </template>
	  </el-table-column>
  	<el-table-column

        width="150">
        <template slot-scope="scope">
          <el-icon name="time"></el-icon>
          <span style="margin-left: 10px">{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column

       >
        <template slot-scope="scope">
          <el-popover trigger="hover" placement="top">
            <p>门禁位置: {{ scope.row.name }}</p>
            <p>上次维护日期: {{ scope.row.date }}</p>
            <div slot="reference" class="name-wrapper">
              <el-tag>{{ scope.row.name }}</el-tag>
            </div>
          </el-popover>
        </template>
      </el-table-column>
  	<el-table-column
  	  width="150">
  	  <template slot-scope="scope">
  		<!-- <el-icon name="time"></el-icon> -->
  		<span style="margin-left: 10px">{{ scope.row.error }}</span>
  	  </template>
  	</el-table-column>
      <el-table-column >
        <template slot-scope="scope">
			已处理
        </template>
      </el-table-column>
    </el-table>
</el-row>

</template>

<script>
  export default {
    data() {
      return {
        tableData: [{
			long:'20588206',
          date: '2016-05-02',
          name: '三幢',
          address: '三幢楼下',
		  error: '连接异常'
        }, {
			long:'21809798',
          date: '2016-05-04',
          name: '六幢',
          address: '六幢楼下',
		  error: '摄像头异常'
        }, {
			long:'20580001',
          date: '2016-05-01',
          name: '三幢',
          address: '三幢',
		  error: '摄像头异常'
	  }],
	  tableData2: [{
	long:'20588206',
			date: '2016-05-02',
			name: '三幢',
			address: '三幢',
	error: '门禁开关失效'
		}, {
	long:'21809798',
			date: '2016-05-04',
			name: '三幢',
			address: '三幢',
	error: '系统关闭'
		}]
      }
    },
    methods: {
      handleEdit(index, row) {
		  this.$notify({
            title: '已发送',
            message: '已通知物业专员前往现场查看',
            type: 'success'
          });
      },
      handleDelete(index, row) {
		  this.$notify({
  		  title: '已发送',
  		  message: '已向厂家技术人员发送异常信息',
  		  type: 'success'
  		});
      }
    }
  }
</script>