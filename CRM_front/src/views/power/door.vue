<template>
<el-row :gutter="20">
    <el-dialog
    title="权限修改"
    :visible.sync="dialogVisible"
    width="30%"
    hight="50%"
    :before-close="handleClose">
    <div>
        <div style="margin-bottom: 5%">
          <el-radio v-model="radio8" label="6" border size="small">家人</el-radio>
          <el-radio v-model="radio8" label="7" border size="small">亲戚</el-radio>
          <el-radio v-model="radio8" label="8" border size="small">保姆</el-radio>
        </div>
        <div style="margin-bottom: 5%">
          <el-radio v-model="radio8" label="9" border size="small">暂行</el-radio>
          <el-radio v-model="radio8" label="10" border size="small">暂行过期</el-radio>
          <el-radio v-model="radio8" label="11" border size="small">未注册</el-radio>
        </div>
          <el-date-picker
              v-model="value6"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd">
           </el-date-picker>
        <el-tree
          :data="data2"
          show-checkbox
          node-key="id"
          ref="tree"
          :props="defaultProps">
        </el-tree>
    </div>
    <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="submit">授权</el-button>
    </span>
  </el-dialog>
  <el-col :span="5" :offset="2" style="margin-top:5%;" v-for="item in items" :key="item.key">
    <el-card :body-style="{ padding: '0px' }">
      <img :src="'http://118.24.0.78:8000/api/v1/' + item.pic" class="image">
      <div style="padding: 14px;">
        <p class="title2">昵称:<span class="time"> {{item.nickname}}</span></p>
        <p class="title2">角色:<span class="time"> {{item.role.alias}}</span></p>
        <!-- <p class="title2">时间段:<span class="time"> {{item.time2}}</span></p>
        <p class="title2">星期:<span class="time"> {{item.week2}}</span></p> -->
        <div class="bottom clearfix">
          <el-button type="text" class="button" @click="dialog(item.user_id)">权限修改</el-button>
        </div>
      </div>
    </el-card>
  </el-col>
  
</el-row>
</template>


<script>
import { fetchOwnerVisiterList,updateVisiter,fetchAddressList } from '@/api/manager'
import store from '@/store'

export default {
  created(){
    // for(let i=0;i<20;i++){
    //   this.items.push(i);
    // }
    // this.getList();
    let address_all=[];
      fetchAddressList().then(res => {
          let temp = res.data.data;
          let i = 0;
          while(temp[i]){
            //   address_all.push(temp[i].id);
            this.address_id = temp[i].id;
            this.getList();
              i++;
          }
      })
  },
  methods:{
    submit(){
      let time = {
          "date":this.value6,
          "week":this.$refs.tree.getCheckedKeys()
        };
      let data = {
          'time':JSON.stringify(time),
          'role_id': this.radio8
        }
      updateVisiter(this.visiter_id,this.address_id,data).then(response => {
        console.log(response.data);
        this.items=[];
        this.getList();
        this.dialogVisible = false;
        this.value6='';
        // this.$refs.tree=null;
      })

    },
    dialog(val){
      console.log("user_id:"+val);
      this.visiter_id = val;
      this.dialogVisible = true;
    },
    handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {});
      },
    getList() {
      
    //   let address_all=[];
    //   fetchAdderssList().then(res => {
    //       let temp = res.data.data;
    //       let i = 0;
    //       while(temp[i]){
    //           address_all.push(temp[i].id);
    //           i++;
    //       }
    //   })
      
        // console.log("okoko"+this.address_id);
      fetchOwnerVisiterList(this.listQuery,this.address_id).then(response => {
        //   console.log(response.data.data[0].address.id)
        // this.items ;
        let temp = response.data.data;
        let i =0;
        while(temp[i]){
          let temp2 = temp[i];
          console.log(temp2.role);
        //   if(temp2.role.name=="owner")continue;
          if(temp2.time){
              temp2.time2 = temp2.time.date[0]+"至"+temp2.time.date[1];
                let j = 0;
                temp2.week2 = "" 
                // console.log(temp2.week);
                if(temp2.time.week.length>6){
                    temp2.week2 = "整周"
                }else{
                    while(temp2.time.week[j++]){
                    temp2.week2 = temp2.week2+weekSwitch(temp2.time.week[j-1])+" ";
                }
            }
          }else {
              
          }
          this.items.push(temp2);
          i++;
        }
        // this.total = response.data.total
      })
    },
  },
  data() {
    return {
      radio8: '1',
      dialogVisible: false,
      items:[],
      currentDate: new Date(),
      address_id:null,
      visiter_id:null,
      value6: '',
        data2: [{
          id: 0,
          label: '时间段内都可通行',
          children: [{
            id: 1,
            label: '周一'
          },{
            id: 2,
            label: '周二'
          }, {
            id: 3,
            label: '周三'
          },{
            id: 4,
            label: '周四'
          },{
            id: 5,
            label: '周五'
          },{
            id: 6,
            label: '周六'
          },{
            id: 7,
            label: '周日'
          },]
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
    };
  }
}

function weekSwitch(num) {
  let ss = "";
  switch (num){
    case 1:
      ss = "周一"
      break
    case 2:
      ss = "周二"
      break
    case 3:
      ss = "周三"
      break
    case 4:
      ss = "周四"
      break
    case 5:
      ss = "周五"
      break
    case 6:
      ss = "周六"
      break
    case 7:
      ss = "周日"
      break
  }
  return ss;

}
</script>



<style scoped>

  .title2 {
    font-size: 13px;
    color:#b741e8;
  }

  .time {
    font-size: 13px;
    color: #999;
  }
  
  .bottom {
    margin-top: 13px;
    line-height: 12px;
  }

  .button {
    padding: 0;
    float: right;
  }

  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both
  }

</style>