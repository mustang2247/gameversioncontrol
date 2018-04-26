<template>
  <div id="add-version">
		<el-container style='margin-bottom: 10px;'>
	  	<el-header height="40px" style="line-height:30px;border-bottom: 1px solid #d5e3ef;">
	  		<div class="art-left">
	  			<b><i class="el-icon-plus"></i></b>
	  			<span><b>添加版本信息</b></span>
	  		</div>
	  	</el-header>
	  	<el-main>
	  		<!--表单主元素-->
	  		<el-form 
	  			ref="ruleForm" 
	  			status-icon 
	  			:rules="rules"
	  			size="small" 
	  			:model="forms" 
	  			label-width="120px" 
	  			label-position="right" 
	  			class="form-box">
				  <el-form-item label="应用appId" prop="appid">
				    <el-input v-model="forms.appid"></el-input>
				  </el-form-item>
				  <el-form-item label="应用名称" prop="appname">
				    <el-input v-model="forms.appname"></el-input>
				  </el-form-item>
				  <el-form-item label="渠道ID" prop="channelid">
				    <el-input v-model="forms.channelid"></el-input>
				  </el-form-item>
				  <el-form-item label="渠道名称" prop="channelname">
				    <el-input v-model="forms.channelname"></el-input>
				  </el-form-item>
				  <el-form-item label="应用版本" prop="appversion">
				    <el-input v-model="forms.appversion"></el-input>
				  </el-form-item>
				  <el-form-item label="更新策略" prop="updatestrategy">
				    <el-radio-group v-model="forms.updatestrategy">
				      <el-radio label="1">提示更新</el-radio>
				      <el-radio label="2">强制更新</el-radio>
				      <el-radio label="3">不更新</el-radio>
				    </el-radio-group>
				  </el-form-item>
				  <el-form-item label="apk更新地址" prop="apkurl">
				    <el-input v-model="forms.apkurl"></el-input>
				  </el-form-item>
				  <el-form-item label="更新信息" prop="updateinfo">
				    <el-input type="textarea" v-model="forms.updateinfo"></el-input>
				  </el-form-item>
				  <el-form-item size="middle">
				    <el-button type="primary" 
				    	@click="onSubmit('ruleForm')" 
				    	v-loading.fullscreen.lock="addLoad"
				    	>立即创建</el-button>
				    <el-button @click="onReset('ruleForm')">重置</el-button>
				  </el-form-item>
				</el-form>
	  		</el-main>
		</el-container>
  </div>
</template>

<script>
	export default {
	  	name: 'addVersion',
	  	data (){
	  		return {
	  			addLoad: false,
	  			forms: {
	          	appid: '',
	        	appname: '',
	        	channelid: '',
	        	channelname: '',
	        	appversion: '',
	        	updatestrategy: '',
	        	apkurl: '',
	        	updateinfo: ''
	       },
	       rules: {
	          appid: [
	            { required: true, message: '请输入应用appId'}
	          ],
	          appname: [
	            { required: true, message: '请输入应用名称' }
	          ],
	          channelid: [
	            { required: true, message: '请输入渠道ID' }
	          ],
	          channelname: [
	            { required: true, message: '请输入渠道名称' }
	          ],
	          appversion: [
	            { required: true, message: '请输入应用版本信息' },
	            { pattern: /^\d+\.\d+\.\d+$/, message: '请输入正确版本格式，如：1.0.0' }
	          ],
	          updatestrategy: [
	            { required: true, message: '请选择更新策略' }
	          ],
	          apkurl: [
	            { required: true, message: '请输入apk更新地址' }
	          ],
	          updateinfo: [
	          	{ required: true, message: '请输入更新信息' }
	          ]
	        }
	  		}
	  	},
	    methods: {
	      onSubmit (formName) {
	        this.$refs[formName].validate((valid) => {
	          if (valid) {
	            //console.log(this.forms)
	            this.addLoad = true;
	            this.$http.post('http://upgrate.block18.io:9999/api/insertInfor', 
	            	this.forms,
	            	{ emulateJSON: true }
	           	).then(function(res) {
					//console.log(res);
					this.addLoad = false;
					if(res.status === 200) {
						if(res.body.code === '000000'){
							this.$message({
			          message: '成功一条添加版本信息！',
			          type: 'success'
			       	});
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
					this.addLoad = false;
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
	        });
	      },
	      onReset (formName) {
	      	this.$refs[formName].resetFields();
	      }
	    }
	}
</script>

<style scoped>
	#add-version {
		width: 100%;
		height: 100%;
	}
	.art-left {
		float: left;
	}
	.form-box {
		width: 50%;
		margin: 0 auto;
	}
</style>
