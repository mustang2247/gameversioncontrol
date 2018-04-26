<template>
	<div id="app">
	   <router-view></router-view>
	</div>
</template>

<script>
	export default {
	  name: 'App',
	  components: {
	  }
	}
</script>

<style>
	html, body, a, img, p, ul, li, ol, h1, h2, h3, h4, h5, h6 {
		margin: 0;
		padding: 0;
		border: none;
		list-style: none;
		text-decoration: none;
	  	-webkit-font-smoothing: antialiased;
	  	-moz-osx-font-smoothing: grayscale;
	  	font-family: "微软雅黑";
	}
	html, body, #app {
		width: 100%;
		height: 100%;
		overflow: hidden;
	}
	.box-border { -webkit-box-sizing: border-box; -moz-box-sizing: border-box; -ms-box-sizing: border-box; -o-box-sizing: border-box; box-sizing: border-box; }
	.text-c { text-align: center; }
	/*修改表格组件里难以修改的样式*/
		/*文章列表组件*/
		.art-list-tab-head th {
			color: #666;
			background: #ddd;
		}
		.art-list-cell-td .cell, .art-list-cell-td .cell span div {
			overflow: hidden;
			text-overflow: ellipsis;
			white-space: nowrap!important;
			height: 25px!important;
			white-space: normal;
		}
</style>
