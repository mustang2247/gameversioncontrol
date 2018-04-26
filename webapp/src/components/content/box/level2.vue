<template>
  <div class="level2 box-border">
		<router-view></router-view>
  </div>
</template>

<script>
	export default {
	  	name: 'level2'
	}
</script>

<style scoped>
	.level2 { width: 100%; height: 100%; padding: 10px; overflow: auto; }
</style>
