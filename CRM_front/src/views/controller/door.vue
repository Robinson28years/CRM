<template>
<el-row :gutter="20">
    <el-col :span="5" :offset="2" style="margin-top:5%;" v-for="item in items" :key="item.key">
    <el-card class="box-card">
    <div slot="header" class="clearfix">
        <span>{{item.building_id}}号大门</span>
        <el-switch
        style="display: block; float: right; padding: 3px 0"
        v-model="item.state"
        active-color="#13ce66"
        inactive-color="#ff4949"
        active-text="开"
        @change="open(item.building_id)"
        inactive-text="关">
        </el-switch>
    </div>
    <div class="text item">
        {{'维修人员： ' + item.fix_name }}
    </div>
    <div class="text item">
        {{'今日大门开启次数： ' + item.inout_num }}
    </div>
    </el-card>
  </el-col>
</el-row>
</template>

<script>
import { open_auth } from '@/api/controller'
export default {
  methods:{
    open(val){
      console.log(val);
      console.log(this.items[0]);
      // if(val==1){
        open_auth({
        'type':"open",
        'building_id':val
      }).then(res => {
        console.log(res.data);
      })
      setTimeout(() => {
            this.items[val-1].state = false;
        }, 4000);
      // }
    }
  },
  data(){
      return {
          items:[
            {
              'building_id':1,
              'inout_num':135,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
          ]
      }
  }
}
</script>


<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 430px;
  }
</style>

