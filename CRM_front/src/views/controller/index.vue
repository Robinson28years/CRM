<template>
<el-row :gutter="20">
    <el-col :span="10" :offset="2" style="margin-top:5%;" v-for="item in items" :key="item.key">
    <el-card class="box-card">
    <div slot="header" class="clearfix">
        <span>{{item.building_id}}幢</span>
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
    <div v-if="item.building_id==1" class="text item ">
        <iframe class="plus" ref="iframe" src="http://118.24.0.78:8082/index.html" ></iframe>
    </div>
    <div v-else>
      <img src="http://otj6w86xd.bkt.clouddn.com/images.png" alt="">
    </div>
    <!-- <div class="text item">
        {{'维修人员： ' + item.fix_name }}
    </div>
    <div class="text item">
        {{'今日门禁开启次数： ' + item.inout_num }}
    </div> -->
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
              'inout_num':35,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
            {
              'building_id':2,
              'inout_num':12,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
            {
              'building_id':3,
              'inout_num':54,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
            {
              'building_id':4,
              'inout_num':66,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
            {
              'building_id':5,
              'inout_num':26,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
            {
              'building_id':6,
              'inout_num':77,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
            {
              'building_id':7,
              'inout_num':46,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
            {
              'building_id':8,
              'inout_num':12,
              'unregistered':5,
              'fix_name':"小陈",
              'state':false,
            },
            {
              'building_id':9,
              'inout_num':33,
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
  .plus {
    height: 460px;
    width: 360px;
  }
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
    height: 550px;
    width: 400px;
  }
</style>

