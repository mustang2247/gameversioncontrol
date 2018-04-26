<template>
  <div id="aside-box">
    <el-menu
      router
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      style="border-right: none">
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span>版本管理</span>
        </template>
       	<el-menu-item index="/index/version/list">
		      <i class="el-icon-tickets"></i>
		      <span>版本列表</span>
		    </el-menu-item>
		    <el-menu-item index="/index/version/add">
		      <i class="el-icon-plus"></i>
		      <span>添加版本</span>
		    </el-menu-item>
		    <el-menu-item index="/index/version/update_list">
		      <i class="el-icon-document"></i>
		      <span>更新公告</span>
		    </el-menu-item>
      </el-submenu>
      <!--系统管理下拉菜单-->
      <!--<el-submenu index="2">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span>系统管理</span>
        </template>
       	<el-submenu index="2-4">
          <template slot="title">团队管理</template>
          <el-menu-item index="2-4-1">
          	<i class="el-icon-more-outline"></i>
          	<span>成员列表</span>
          </el-menu-item>
          <el-menu-item index="2-4-2">
          	<i class="el-icon-plus"></i>
          	<span>添加成员</span>
          </el-menu-item>
        </el-submenu>
       	<el-submenu index="2-5">
          <template slot="title">系统配置</template>
          <el-menu-item index="2-4-3">
          	<i class="el-icon-setting"></i>
          	<span>系统设置</span>
          </el-menu-item>
        </el-submenu>
      </el-submenu>-->
      <el-menu-item index="/index/system/test">
        <i class="el-icon-share"></i>
        <span slot="title">系统管理</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
	export default {
	  	name: 'asideBox',
	  	data (){
	  		return {
	  			
	  		}
	  	},
	  	methods: {
	      handleOpen(key, keyPath) {
	        //console.log(key, keyPath);
	      },
	      handleClose(key, keyPath) {
	        //console.log(key, keyPath);
	      }
	    }
	}
</script>

<style scoped>
	#aside-box {
		width: 100%;
		height: 100%;
	}
</style>
