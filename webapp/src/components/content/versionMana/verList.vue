<template>
  <div id="version-list">
	<el-container style='margin-bottom: 10px;'>
	  	<el-header height="40px" style="line-height:30px;border-bottom: 1px solid #d5e3ef;">
	  		<div class="art-left">
	  			<b><i class="el-icon-document"></i></b>
	  			<span><b>文章列表</b></span>
	  		</div>
	  		<div class="art-right">
	  			<el-input placeholder="请输入搜索标题" size="mini" v-model="artTil" style='width: 200px'>
				    <template slot="prepend">标题</template>
				</el-input>
				<el-input placeholder="请选择状态" readonly size="mini" v-model="artType" style='width: 150px'>
				    <el-select slot="append" v-model="artType" placeholder="请选择">
				      <el-option label="全部" value="全部"></el-option>
				      <el-option label="前台隐藏" value="前台隐藏"></el-option>
				      <el-option label="前台显示" value="前台显示"></el-option>
				    </el-select>
				</el-input>
				<el-button type="primary" icon="el-icon-search" size="mini">搜索</el-button>
	  		</div>
	  	</el-header>
	</el-container>
	<el-table
		border
		stripe
		size="small"
	    :data="tableData"
	    style="width: 100%"
	    cell-class-name="art-list-cell-td"
	    header-row-class-name="art-list-tab-head"
	    empty-text="暂无数据"
	    class='table-box'>
	    <!--标题-->
	    <el-table-column
	      label="应用 ID">
	      <template slot-scope="scope">
	        <span style="margin-left: 10px">{{ scope.row.appid }}</span>
	      </template>
	    </el-table-column>
	    <!--分类-->
	    <el-table-column
	      label="应用名称">
	      <template slot-scope="scope">
	        <span style="margin-left: 10px">{{ scope.row.appname }}</span>
	      </template>
	    </el-table-column>
	    <!--流量-->
	    <el-table-column
	      label="渠道 ID">
	      <template slot-scope="scope">
	        <span style="margin-left: 10px">{{ scope.row.channelid }}</span>
	      </template>
	    </el-table-column>
	    <el-table-column
	      label="渠道名称">
	      <template slot-scope="scope">
	        <span style="margin-left: 10px">{{ scope.row.channelname }}</span>
	      </template>
	    </el-table-column>
	    <el-table-column
	      label="应用版本"
	      width="80">
	      <template slot-scope="scope">
	        <span style="margin-left: 10px">{{ scope.row.appversion }}</span>
	      </template>
	    </el-table-column>
	    <el-table-column
	      label="更新策略"
	      width="90">
	      <template slot-scope="scope">
	        <span style="margin-left: 10px">{{ scope.row.updatestrategy | updatestr }}</span>
	      </template>
	    </el-table-column>
	    <el-table-column
	      label="APK 更新地址">
	      <template slot-scope="scope">
	        <el-popover trigger="hover" placement="top">
	          <p>{{ scope.row.apkurl }}</p>
	          <div slot="reference" class="name-wrapper">
	            <span style="margin-left: 10px">{{ scope.row.apkurl }}</span>
	          </div>
	        </el-popover>
	      </template>
	    </el-table-column>
	    <el-table-column
	      label="更新说明">
	      <template slot-scope="scope">
	        <el-popover trigger="hover" placement="top" v-if="scope.row.updateinfo.length > 0">
	          <p>{{ scope.row.updateinfo }}</p>
	          <div slot="reference" class="name-wrapper">
	            <span style="margin-left: 10px">{{ scope.row.updateinfo }}</span>
	          </div>
	        </el-popover>
	      </template>
	    </el-table-column>
	    <!--操作-->
	    <el-table-column 
	      label="操作"
	      width="70">
	      <template slot-scope="scope">
	        <el-popover trigger="hover" placement="top" style='display: inline-block;width: 20px;'>
	          <p>编辑此条文章</p>
	          <div slot="reference" class="name-wrapper">
	            <i class="el-icon-edit"
	          	   @click="handleEdit(scope.$index, scope.row)"></i>
	          </div>
	        </el-popover>
	        <el-popover trigger="hover" placement="top" style='display: inline-block;width: 20px;'>
	          <p>删除此条文章</p>
	          <div slot="reference" class="name-wrapper">
	            <i class="el-icon-delete"
	          	   @click="handleDelete(scope.$index, scope.row)"></i>
	          </div>
	        </el-popover>
	      </template>
	    </el-table-column>
	</el-table>
  	<el-container style='padding: 10px 0;'>
	  	<el-header style="height: auto;padding: 5px 0;border-bottom: 1px solid #d5e3ef;">
	  		<el-pagination
	  		  v-loading.fullscreen.lock="addLoad"
		      background
		      @size-change="handleSizeChange"
		      @current-change="handleCurrentChange"
		      :current-page="currentPage"
		      :page-size="pageSize"
		      layout="prev, pager, next, total, ->, jumper"
		      :total="total">
		    </el-pagination>
	  	</el-header>
	</el-container>
  </div>
</template>

<script>
	export default {
	  	name: 'versionList',
	  	data (){
	  		return {
	  			artTil: '',
	  			artType: '',
	  			tableData: [],
		        currentPage: 1,
		        pageSize: 10,
		        total: 0,
		        addLoad: false
	  		}
	  	},
	  	created () {
        	this.getTableData();
	  	},
	  	methods: {
	  	  getTableData () {
	  	  	this.addLoad = true;
	  	  	this.$http.post('http://upgrate.block18.io:9999/api/getHistoryHotUpdate',{
	  	  		page: this.currentPage - 1,
            pageSize: this.pageSize
	  	  	},{ emulateJSON: true })
	  	  	.then(function(res) {
				//console.log(res);
				this.addLoad = false;
				this.tableData = [];
				if(res.status === 200) {
					if(res.body.code === '000000'){
						this.total = res.body.data.totalElements;
						this.tableData = res.body.data.content;
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
				this.tableData = [];
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
	  	  },
	  	  delTableData (delId) {
	  	  	this.addLoad = true;
	  	  	this.$http.post('http://upgrate.block18.io:9999/api/deleteHotUpdateById',{
	  	  		id: delId
	  	  	},{ emulateJSON: true })
	  	  	.then(function(res) {
						//console.log(res);
						this.addLoad = false;
						if(res.status === 200) {
							if(res.body.code === '000000'){
								this.$message({
						          message: '成功删除此条数据！',
						          type: 'success'
						       	});
						       	this.getTableData();
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
	  	  },
	  	  /*表格的按钮功能*/
	      handleEdit(index, row) {
	        //console.log(index, row);
	      },
	      handleDelete(index, row) {
	        //console.log(index, row);
	        this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
	          confirmButtonText: '确定',
	          cancelButtonText: '取消',
	          type: 'warning'
	        }).then(() => {
	          this.delTableData(row.id);
	        }).catch(() => {});
	      },
	      /*改变分页数量功能*/
	      handleSizeChange(val) {
	        //console.log(`每页 ${val} 条`);
	      },
	      handleCurrentChange(val) {
	        //console.log(`当前页: ${val}`);
	        this.currentPage = val;
	        this.getTableData();
	      }
	   	},
	   	filters: {
		   	updatestr (index) {
		   		switch (index){
		   		    case 1:
		   		      return '提示更新'
		   		        break;
		   		    case 2:
		   		      return '强制更新'
		   		        break;
		   		    case 3:
		   		      return '不更新'
		   		        break;
		   		    default:
		   		      return '无'
		   		        break;
		   		}
		   	}
	   	}
	}
</script>

<style scoped>
	#version-list {
		width: 100%;
		height: 100%;
	}
	.art-left {
		float: left;
	}
	.art-right {
		float: right;
	}
	.el-icon-edit, .el-icon-delete {
		font-size: 18px;
	}
	.table-box .el-table .cell {
		height: 20px!important;
	}
</style>
