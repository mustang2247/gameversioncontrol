import Vue from 'vue';
import VueRouter from 'vue-router';
import cookie from '../assets/cookie.js';
Vue.use(VueRouter);

// 创建路由实例并配置路由映射
const router = new VueRouter({
	routes: [{
			path: '/index',
			component: resolve => require(['../components/index.vue'], resolve),
			children: [{
				path: 'version',
				component: resolve => require(['../components/content/box/level2.vue'], resolve),
				children: [{
					path: 'list',
					component: resolve => require(['../components/content/versionMana/verList.vue'], resolve)
				}, {
					path: 'add',
					component: resolve => require(['../components/content/versionMana/verAdd.vue'], resolve)
				}, {
					path: 'update_list',
					component: resolve => require(['../components/content/versionMana/updateList.vue'], resolve)
				}]
			}, {
				path: 'system',
				component: resolve => require(['../components/content/box/level2.vue'], resolve),
				children: [{
					path: 'test',
					component: resolve => require(['../components/content/systemMana/system.vue'], resolve)
				}]
			}]
		},
		{
			path: '/login',
			component: resolve => require(['../components/login.vue'], resolve)
		},
		{
			path: '/',
			redirect: '/index'
		}
	]
});
/*此方法是给全局中的每个路由页面都加判断*/
router.beforeEach((to, from, next) => {
//	console.log("我将发生跳转");
//	console.log(to, from);
	var isLogin = cookie.getCookie('block18_login');
//	console.log(isLogin)
	if(isLogin !== 'true' && to.path !== '/login'){
		next('/login');
		return;
	}
	next();
})
/*after 钩子没有next 方法，不能改变导航*/
router.afterEach((to, from) => {
//	console.log("跳转完成");
})

// 输出router
export default router;