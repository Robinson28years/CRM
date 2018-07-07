<template>
<div>
<div class="container-fluid">
    <div class="row example-centered">
        <div class="col-md-12 example-title">
            <h2>人员进出情况</h2>
            <p>Situation of personnel entering and leaving.</p>
        </div>
        <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2">
            <ul class="timeline timeline-centered">
                <li class="timeline-item" v-for="item in items" :key="item.key">
                    <div class="timeline-info">
                        <span v-if="item.result=='未通过'">{{item.visit_time}} <span style="color:red;">{{item.result}}</span></span>
                        <span v-else-if="item.result=='通过'">{{item.visit_time}} <span style="color:green;">{{item.result}}</span></span>
                        <span v-else-if="item.result=='出门禁'">{{item.visit_time}} <span style="color:#e6a23c;">{{item.result}}</span></span>
                        
                    </div>
                    <div class="timeline-marker"></div>
                    <div class="timeline-content">
                        <h3 class="timeline-title">{{item.nickname}}</h3>
                        <p>访问地址：{{item.address.building_id}}幢{{item.address.unit_id}}单元{{item.address.room_id}}号</p>
                        <p>角色：{{item.role.alias}}</p>
                        <p v-if="item.attendant_num>0">随行人员： &lt;{{item.attendant_num}}人</p>
                        <p v-else>随行人员：无</p>
                        <img :src="'http://118.24.0.78:8000/api/v1/' + item.pic" class="image">
                        <!-- <p>1幢2单元302</p> -->
                        <!-- <el-row justify="center">
                        <el-col :span="11" >
                          <el-card>

                            <img :src="'http://127.0.0.1:8000/api/v1/' + item.pic" class="image">
                          </el-card>
                        </el-col>
                      </el-row> -->
                    </div>
                    
                </li>


                
                
            </ul>
        </div>
    </div>
</div>

</div>
</template>

<script>
import { fetchList,fetchLastList } from '@/api/visit'
import baseURL from '../../../config/api'


export default {
  created(){
    // for(let i=0;i<20;i++){
    //   this.items.push(i);
    // }
    // this.items = ['a','b','c','d','e','f'];
    this.getList();
    this.initWebSocket();
    this.threadPoxi();
    // this.websocketsend(JSON.stringify({
    //                     "type":"admin",
    //                 }));
    // this.$options.sockets.onmessage = (data) => console.log(data)

  },
  data(){
    return {
      picURL:baseURL+'/',
      items:[],

    }
  },
  methods:{

    getList() {
      fetchList(this.listQuery).then(response => {
        //   console.log(response.data.data[0].address.id)
        this.items = response.data.data.reverse();
        // this.total = response.data.total
      })
    },
    open3() {
        this.$notify({
          title: '注意',
          message: '门禁已开启！',
          type: 'warning'
        });
    },
    open4() {
        this.$notify({
          title: '提醒',
          message: '门禁已关闭',
          type: 'success'
        });
    },

            threadPoxi(){  // 实际调用的方法
                //参数
                const agentData =JSON.stringify({
                        "type":"admin",
                    });
                 //若是ws开启状态
                if (this.websock.readyState === this.websock.OPEN) {
                    console.log("okokokok");
                    this.websocketsend(JSON.stringify({
                        "type":"admin2",
                    }))
                }
                // 若是 正在开启状态，则等待300毫秒
                else if (this.websock.readyState === this.websock.CONNECTING) {
                    let that = this;//保存当前对象this
                    setTimeout(function () {
                        that.websocketsend(JSON.stringify({
                        "type":"admin3",
                    }))
                    }, 300);
                }
                // 若未开启 ，则等待500毫秒
                else {
                    this.initWebSocket();
                    let that = this;//保存当前对象this
                    setTimeout(function () {
                        that.websocketsend(JSON.stringify({
                        "type":"admin1",
                    }))
                    }, 500);
                }
            },
            initWebSocket(){ //初始化weosocket
                //ws地址
                const wsuri = "ws://118.24.0.78:9505";
                this.websock = new WebSocket(wsuri);
                this.websock.onopen = () => this.websock.send(JSON.stringify({
                        "type":"admin",
                    }));
                this.websock.onmessage = this.websocketonmessage;
                this.websock.onclose = this.websocketclose;
            },
            websocketonmessage(e){ //数据接收
                const redata = JSON.parse(e.data);
                console.log(e.data);
                // this.$options.methods.getList();
                // this.getList();
                //todo::了解函数间的调用
                console.log("kkk"+redata.open);
                console.log(redata.open==1);
                if(redata.open == 1) {
                    this.open3();
                    this.items=[];
                    this.getList();
                }else{
                    this.open4();
                }
                
                // location.reload();
                // $router.go(0);
                // fetchLastList(this.listQuery).then(response => {
                //         //   console.log(response.data.data[0].address.id)
                //         // this.items.push(response.data.data);
                //         console.log(this.items);
                //         // this.total = response.data.total
                //     })
                // this.items.push(1);
                    //   fetchList(this.listQuery).then(response => {
                    //         //   console.log(response.data.data[0].address.id)
                    //         // this.items = response.data.data.reverse();
                    //         // that.$route.router.go(0);
                    //         console.log("kookokokokok");
                    //         // this.total = response.data.total
                    //     })
            },
            websocketsend(agentData){//数据发送
                this.websock.send(agentData);
            },
            websocketclose(e){  //关闭
                console.log("connection closed (" + e.code + ")");
            }


  }

  
}
</script>


<style lang="scss">


  .image {
    width: 320px;
  }


$primary-color: #FF6B6B;
$primary-color-hover: scale-color($primary-color, $lightness: 32%);

body {
    color: #768390;
    background: #FFF;
    font-family: "Effra", Helvetica, sans-serif;
    padding: 0;
    -webkit-font-smoothing: antialiased;
}
h1,h2,h3,h4,h5,h6 {
    color: #3D4351;
    margin-top: 0;
}
a {
    color: $primary-color;
    &:hover {
        color: $primary-color-hover;
        text-decoration: none;
    }
}
.example-header {
    background: #3D4351;
    color: #FFF;
    font-weight: 300;
    padding: 3em 1em;
    text-align: center;
    h1 {
        color: #FFF;
        font-weight: 300;
        margin-bottom: 20px
    }
    p {
        font-size: 12px;
        text-transform: uppercase;
        letter-spacing: 3px;
        font-weight: 700;
    }
}
.container-fluid {
    .row {
        padding: 0 0 4em 0;
        &:nth-child(even) {
            background: #F1F4F5;
        }
    }
}

.example-title {
    text-align: center;
    margin-bottom: 60px;
    padding: 3em 0;
    border-bottom: 1px solid #E4EAEC;
    p {
        margin: 0 auto;
        font-size: 16px;
        max-width: 400px;
    }
}

/*==================================
    TIMELINE
==================================*/

    /*-- GENERAL STYLES
    ------------------------------*/
    .timeline {
        line-height: 1.4em;
        list-style: none;
        margin: 0;
        padding: 0;
        width: 100%;
        h1, h2, h3, h4, h5, h6 {
            line-height: inherit;
        }
    }

    /*----- TIMELINE ITEM -----*/

    .timeline-item {
        padding-left: 40px;
        position: relative;
        &:last-child {
            padding-bottom: 0;
        }
    }

    /*----- TIMELINE INFO -----*/

    .timeline-info {
        font-size: 12px;
        font-weight: 700;
        letter-spacing: 3px;
        margin: 0 0 .5em 0;
        text-transform: uppercase;
        white-space: nowrap;
    }
    /*----- TIMELINE MARKER -----*/

    .timeline-marker {
        position: absolute;
        top: 0; bottom: 0; left: 0;
        width: 15px;
        &:before {
            background: $primary-color;
            border: 3px solid transparent;
            border-radius: 100%;
            content: "";
            display: block;
            height: 15px;
            position: absolute;
            top: 4px; left: 0;
            width: 15px;
            transition: background 0.3s ease-in-out,
                    border 0.3s ease-in-out;
        }
        &:after {
            content: "";
            width: 3px;
            background: #CCD5DB;
            display: block;
            position: absolute;
            top: 24px; bottom: 0; left: 6px;
        }
        .timeline-item:last-child &:after {
            content: none;
        }
    }
    .timeline-item:not(.period):hover .timeline-marker:before {
        background: transparent;
        border: 3px solid $primary-color;
    }

    /*----- TIMELINE CONTENT -----*/

    .timeline-content {
        padding-bottom: 40px;
        p:last-child {
            margin-bottom: 0;
        }
    }

    /*----- TIMELINE PERIOD -----*/
    
    .period {
        padding: 0;
        .timeline-info {
            display: none;
        }
        .timeline-marker {
            &:before {
                background: transparent;
                content: "";
                width: 15px;
                height: auto;
                border: none;
                border-radius: 0;
                top: 0;
                bottom: 30px;
                position: absolute;
                border-top: 3px solid #CCD5DB;
                border-bottom: 3px solid #CCD5DB;
            }
            &:after {
                content: "";
                height: 32px;
                top: auto;
            }
        }
        .timeline-content {
            padding: 40px 0 70px;
        }
        .timeline-title {
            margin: 0;
        }
    }

    /*----------------------------------------------
        MOD: TIMELINE SPLIT
    ----------------------------------------------*/

        .timeline-split {
            @media (min-width: 768px) {
                .timeline {
                    display: table;
                }
                .timeline-item {
                    display: table-row;
                    padding: 0;
                }
                .timeline-info,
                .timeline-marker,
                .timeline-content,
                .period .timeline-info {
                    display: table-cell;
                    vertical-align: top;
                }
                .timeline-marker {
                    position: relative;
                }
                .timeline-content {
                    padding-left: 30px;
                }
                .timeline-info {
                    padding-right: 30px;
                }
                .period .timeline-title {
                    position: relative;
                    left: -45px;
                }
            }
        }

    /*----------------------------------------------
        MOD: TIMELINE CENTERED
    ----------------------------------------------*/

        .timeline-centered {
            @extend .timeline-split;
            @media (min-width: 992px) {
                &,
                .timeline-item,
                .timeline-info,
                .timeline-marker,
                .timeline-content {
                    display: block;
                    margin: 0;
                    padding: 0;
                }
                .timeline-item {
                    padding-bottom: 40px;
                    overflow: hidden;
                }
                .timeline-marker {
                    position: absolute;
                    left: 50%;
                    margin-left: -7.5px;
                }
                .timeline-info,
                .timeline-content {
                    width: 50%;
                }
                > .timeline-item:nth-child(odd) .timeline-info {
                    float: left;
                    text-align: right;
                    padding-right: 30px;
                }
                > .timeline-item:nth-child(odd) .timeline-content {
                    float: right;
                    text-align: left;
                    padding-left: 30px;
                }    
                > .timeline-item:nth-child(even) .timeline-info {
                    float: right;
                    text-align: left;
                    padding-left: 30px;
                }
                > .timeline-item:nth-child(even) .timeline-content {
                    float: left;
                    text-align: right;
                    padding-right: 30px;
                }
                > .timeline-item.period .timeline-content {
                    float: none;
                    padding: 0;
                    width: 100%;
                    text-align: center;
                }
                .timeline-item.period {
                    padding: 50px 0 90px;
                }
                .period .timeline-marker:after {
                    height: 30px;
                    bottom: 0;
                    top: auto;
                }
                .period .timeline-title {
                    left: auto;
                }
            }
        }

    /*----------------------------------------------
        MOD: MARKER OUTLINE
    ----------------------------------------------*/
        
        .marker-outline {
            .timeline-marker {
                &:before {
                    background: transparent;
                    border-color: $primary-color;
                }
            }
            .timeline-item:hover .timeline-marker:before {
                background: $primary-color;
            }
        }
</style>
