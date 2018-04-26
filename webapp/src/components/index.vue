<template>
  <div id="mains">
    <el-container class='main-box' direction='horizontal'>
    	<el-header class='head'>
    		18区-后台管理系统
    		<el-dropdown 
    			placement="bottom-end"
    			split-button 
    			type="info" 
    			size="small"
    			trigger="click"
    			@command="adminClick"
    			style="float: right;">
    			<i class="el-icon-bell"></i>
				  admin
				  <el-dropdown-menu slot="dropdown">
				    <el-dropdown-item command="reg">注册新用户</el-dropdown-item>
				    <el-dropdown-item command="log_out" divided>退出登录</el-dropdown-item>
				  </el-dropdown-menu>
				</el-dropdown>
    	</el-header>
    	<el-aside class='aside' width='200px'>
    		<index-aside></index-aside>
			</el-aside>
			<el-main class='content'>
				<router-view></router-view>
			</el-main>
    </el-container>
  </div>
</template>

<script>
	import indexAside from './aside.vue'; //首页-左侧组件
	import cookie from '../assets/cookie.js';
	export default {
	  	name: 'mains',
	  	data (){
	  		return {
	  			
	  		}
	  	},
	  	methods: {
	  		adminClick (e) {
	  			console.log(e);
	  			switch (e){
	  			    default:
	  			    	cookie.delCookie("block18_login");
	  			    	this.$router.push("/login");
	  			        break;
	  			}
	  		}
	  	},
	  	components: {
	  		indexAside
	  	}
	}
</script>

<style scoped>
	#mains, .main-box {
		width: 100%;
		height: 100%;
	}
	.head {
		position: absolute;
		top: 0;
		width: 100%;
		padding-left: 220px;
		line-height: 60px;
		color: #fff;
		background: #545C64;
	}
	.aside {
		position: relative;
		height: 100%;
		background: #545C64;
		z-index: 10;
	}
	.content {
		width: 100%;
		height: 100%;
		padding: 60px 0 0;
	}
</style>
