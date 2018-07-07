<template>
  <el-table :data="list" style="width: 100%;padding-top: 15px;">
    <el-table-column label="访问地址" show-overflow-tooltip>
      <template slot-scope="scope">
        {{scope.row.address.building_id}}幢{{scope.row.address.unit_id}}单元{{scope.row.address.room_id}}
      </template>
    </el-table-column>
        <el-table-column label="访问时间" show-overflow-tooltip>
      <template slot-scope="scope">
        {{scope.row.visit_time}}
      </template>
    </el-table-column>
    <el-table-column label="昵称" width="195" align="center">
      <template slot-scope="scope">
        {{scope.row.nickname }}
      </template>
    </el-table-column>
    <el-table-column label="Status" width="100" align="center">
      <template slot-scope="scope">
        <el-tag :type="scope.row.result | statusFilter"> {{scope.row.result}}</el-tag>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { fetchList } from '@/api/visit'

export default {
  data() {
    return {
      list: null
    }
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        "通过": 'success',
        "未通过": 'danger',
        "出门禁":'warning'
      }
      return statusMap[status]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      fetchList().then(response => {
        this.list = response.data.data.slice(0, 5)
      })
    }
  }
}
</script>
