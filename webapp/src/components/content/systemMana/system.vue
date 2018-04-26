<template>
	<div>
		{{message}}/{{count}}
	</div>
</template>

<script>
	import { mapState } from 'vuex';
	export default {
		data () {
			return {
				message: '111'
			}
		},
		computed: {
			count () {
		      return this.$store.state.count
		    }
		}
	}
</script>

<style scoped>
	
</style>