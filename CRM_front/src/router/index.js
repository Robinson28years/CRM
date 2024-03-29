import Vue from 'vue'
import Router from 'vue-router'
const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/** note: submenu only apppear when children.length>=1
*   detail see  https://panjiachen.github.io/vue-element-admin-site/#/router-and-nav?id=sidebar
**/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/
export const constantRouterMap = [
  { path: '/login', component: _import('login/index'), hidden: true },
  { path: '/authredirect', component: _import('login/authredirect'), hidden: true },
  { path: '/404', component: _import('errorPage/404'), hidden: true },
  { path: '/401', component: _import('errorPage/401'), hidden: true },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [{
      path: 'dashboard',
      component: _import('dashboard/index'),
      name: 'dashboard',
      meta: { title: '客户管理系统', icon: 'dashboard',roles: ['admin', 'property'], noCache: true }
    }]
  },

]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  // {
  //   path: '/permission',
  //   component: Layout,
  //   redirect: '/permission/index',
  //   meta: { roles: ['admin', 'property'] }, // you can set roles in root nav
  //   children: [{
  //     path: 'index',
  //     component: _import('permission/index'),
  //     name: 'permission',
  //     meta: {
  //       title: 'permission',
  //       icon: 'lock',
  //       roles: ['admin', 'property'] // or you can only set roles in sub nav
  //     }
  //   }]
  // },
  // {
  //   path: '/register',
  //   component: Layout,
  //   children: [{
  //     path: 'register',
  //     component: _import('register/index'),
  //     name: 'register',
  //     meta: { title: '注册申请', icon: 'people', noCache: true }
  //   },]
  // },
  {
    path: '/user',
    component: Layout,
    redirect: 'noredirect',
    name: 'user',
    meta: {
      title: '用户管理',
      icon: 'peoples'
    },
    children: [
      { path: 'add', component: _import('user/add'), name: 'add1', meta: { title: '新增用户' ,icon: 'owner'}},
      { path: 'list', component: _import('user/list'), name: 'list1', meta: { title: '管理用户' ,icon: 'property'}},
    ]
  },

  {
    path: '/customer',
    component: Layout,
    redirect: 'noredirect',
    name: 'customer',
    meta: {
      title: '客户管理',
      icon: 'customer'
    },
    children: [
      { path: 'add', component: _import('customer/add'), name: 'add2', meta: { title: '新增客户' ,icon: 'owner'}},
      { path: 'list', component: _import('customer/list'), name: 'list2', meta: { title: '管理客户' ,icon: 'property'}},
    ]
  },

  {
    path: '/contact',
    component: Layout,
    redirect: 'noredirect',
    name: 'contact',
    meta: {
      title: '联络管理',
      icon: 'contact'
    },
    children: [
      { path: 'add', component: _import('contact/add'), name: 'add3', meta: { title: '新增联络' ,icon: 'owner'}},
      { path: 'list', component: _import('contact/list'), name: 'list3', meta: { title: '管理联络' ,icon: 'property'}},
    ]
  },

  {
    path: '/file',
    component: Layout,
    redirect: 'noredirect',
    name: 'file',
    meta: {
      title: '文档管理',
      icon: 'file'
    },
    children: [
      { path: 'add', component: _import('file/add'), name: 'add4', meta: { title: '新增文档' ,icon: 'owner'}},
      { path: 'list', component: _import('file/list'), name: 'list4', meta: { title: '管理文档' ,icon: 'property'}},
    ]
  },

  {
    path: '/book',
    component: Layout,
    redirect: 'noredirect',
    name: 'book',
    meta: {
      title: '预定管理',
      icon: 'book'
    },
    children: [
      { path: 'add', component: _import('book/add'), name: 'add5', meta: { title: '新增预定' ,icon: 'owner'}},
      { path: 'list', component: _import('book/list'), name: 'list5', meta: { title: '管理预定' ,icon: 'property'}},
    ]
  },

  {
    path: '/log',
    component: Layout,
    redirect: 'noredirect',
    name: 'log',
    meta: {
      title: '日志管理',
      icon: 'log'
    },
    children: [
      { path: 'add', component: _import('log/add'), name: 'add6', meta: { title: '新增日志' ,icon: 'owner'}},
      { path: 'list', component: _import('log/list'), name: 'list6', meta: { title: '管理日志' ,icon: 'property'}},
    ]
  },
  

  // {
  //   path: '/manager',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   name: 'manager',
  //   meta: {
  //     title: '人员管理',
  //     icon: 'peoples'
  //   },
  //   children: [
  //     // { path: 'json-editor', component: _import('components-demo/jsonEditor'), name: 'jsonEditor-demo', meta: { title: '人员注册' }},
  //     { path: 'owner', component: _import('manager/owner'), name: 'owner', meta: { title: '业主管理' ,icon: 'owner'}},
  //     { path: 'property', component: _import('manager/property'), name: 'property', meta: { title: '内部人员管理' ,icon: 'property'}},
  //     { path: 'visiter', component: _import('manager/visiter'), name: 'visiter', meta: { title: '访客管理' ,icon: 'visiter'}},
  //     { path: 'splitpane', component: _import('components-demo/splitpane'), name: 'splitpane-demo', meta: { title: '非注册人员管理' ,icon: 'user2'}},
  //   ]
  // },

  // {
  //   path: '/power',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   name: 'power',
  //   meta: {
  //     title: '权限管理',
  //     icon: 'lock'
  //   },
  //   children: [
  //     { path: 'door', component: _import('power/door'), name: 'power', meta: { title: '大门权限', noCache: true ,icon: 'door'}},
  //     { path: 'index', component: _import('power/index'), name: 'index', meta: { title: '楼幢权限', noCache: true ,icon: 'building'}},
  //   ]
  // },

  // // {
  // //   path: '/visits',
  // //   component: Layout,
  // //   redirect: 'noredirect',
  // //   name: 'visits',
  // //   meta: {
  // //     title: '门禁进出情况',
  // //     icon: 'visiter3'
  // //   },
  // //   children: [
  // //     { path: 'owner', component: _import('visits/owner2'), name: 'owner', meta: { title: '业主进出', noCache: true ,icon: 'owner'}},
  // //     { path: 'line', component: _import('visits/owner3'), name: 'lineChart', meta: { title: '门禁进出情况', noCache: true ,icon: 'visiter'}},
  // //     { path: 'line', component: _import('charts/line'), name: 'lineChart', meta: { title: '非注册人员情况', noCache: true ,icon: 'user2'}},
  // //   ]
  // // },

  // {
  //   path: '/visits',
  //   component: Layout,
  //   children: [{
  //     path: 'index',
  //     component: _import('visits/owner3'),
  //     name: 'visit',
  //     meta: { title: '门禁进出情况', icon: 'visiter3', noCache: true }
  //   }]
  // },



  // {
  //   path: '/controller',
  //   component: Layout,
  //   redirect: 'noredirect',
  //   name: 'controller',
  //   meta: {
  //     title: '门禁控制',
  //     icon: 'machine3'
  //   },
  //   children: [
  //     { path: 'door', component: _import('controller/door'), name: 'door', meta: { title: '大门控制', noCache: true ,icon: 'door'}},
  //     { path: 'index', component: _import('controller/index'), name: 'index', meta: { title: '楼幢控制', noCache: true ,icon: 'building'}}
  //   ]
  // },


  // {
  //   path: '/state',
  //   component: Layout,
  //   children: [{
  //     path: 'index',
  //     component: _import('state/index'),
  //     name: 'icon2',
  //     meta: { title: '设备运行情况', icon: 'machine2', noCache: true }
  //   }]
  // },



  { path: '*', redirect: '/404', hidden: true }
]
