<template>
  <div class="login-box">
    <div class="login box-border">
    	<el-container style='margin-bottom: 10px;'>
		  	<el-header height="40px" style="line-height:30px;border-bottom: 1px solid #d5e3ef;">
		  		<div class="art-left">
		  			<!--<b><i class="el-icon-plus"></i></b>-->
		  			<h3 style="text-align: center;">登录</h3>
		  		</div>
		  	</el-header>
	    	<el-form 
	  			ref="ruleForm" 
	  			status-icon 
	  			:rules="rules"
	  			size="middle" 
	  			:model="forms" 
	  			label-position="left">
				  <el-form-item label="用户名" prop="username" style="margin-bottom: 10px;">
				    <el-input v-model="forms.username"></el-input>
				  </el-form-item>
				  <el-form-item label="密码" prop="password">
				    <el-input type="password" v-model="forms.password"></el-input>
				  </el-form-item>
				  <el-form-item size="middle">
				    <el-button type="primary" 
				    	style="width: 100%;"
				    	@click="onSubmit('ruleForm')"
				    	><i class="el-icon-loading" v-show="isLogin"></i> {{loginTxt}}</el-button>
				  </el-form-item>
			</el-form>
		</el-container>
    </div>
  </div>
</template>

<script>
	import cookie from '../assets/cookie.js';
	export default {
	  	name: 'loginBox',
	  	data (){
	  		return {
	  			forms: {
	  				username: '',
	  				password: '',
	  			},
  				isLogin: false,
  				loginTxt: '立即登录',
	  			rules: {
		          username: [
		            { required: true, message: '请输入用户名'}
		          ],
		          password: [
		            { required: true, message: '请输入密码'}
		          ]
		       	}
	  		}
	  	},
	  	methods: {
	  		setLogStatus (bool) {
	  			this.isLogin = bool;
		      this.loginTxt = bool ? '登陆中···' : '立即登录';
	  		},
	  		onSubmit (formName) {
		        this.$refs[formName].validate((valid) => {
		          if (valid) {
		          	if(this.isLogin === true) {
		          		this.$message({
					          message: '正在发送登录请求，请不要重复点击！',
					          type: 'warning'
					        });
		          		return;
		          	}
		          	this.setLogStatus(true);
		          	this.$http.post('http://upgrate.block18.io:9999/api/login',{
				  	  		username: this.forms.username,
			            password: this.forms.password
				  	  	},{ emulateJSON: true })
				  	  	.then(function(res) {
									//console.log(res);
									this.setLogStatus(false);
									if(res.status === 200) {
										if(res.body.code === '000009'){
											cookie.setCookie('block18_login', true, 1);
											this.$message({
							          message: res.body.message+', 即将跳转管理页面！',
							          type: 'success',
							          duration: 1500
							       	});
							        setTimeout(function() {
		          					this.$router.replace('/');
							        }.bind(this), 1500);
											return;
										}
						        this.$message({
						          message: res.body.message,
						          type: 'warning'
						        });
										//console.log(res.body)
										return;
									}
									this.$message.error('请求失败，请重新请求!');
								}, function(err) {
									this.setLogStatus(false);
									//console.log(err)
									if(err.status === 404) {
										this.$message.error('请求404错误，请重新请求!');
										return;
									}
									if(err.status === 500) {
										this.$message.error('服务器500异常，请重新请求!');
										return;
									}
									this.$message.error('请求失败，请重新请求!');
								})
		          }
		      	})
	       	}
	  	}
	}
</script>

<style scoped>
	.login-box {
		position: relative;
		width: 100%;
		height: 100%;
		background: #eee;
	}
	.login {
		position: absolute;
		top: 50%;
		left: 50%;
		width: 500px;
		height: 350px;
		padding: 30px;
		margin: -175px 0 0 -250px;
		background: #fff;
		border-radius: 10px;
		box-shadow: 0 0 5px rgba(0,0,0,.3);
	}
</style>
