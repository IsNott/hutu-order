<template>
	<view>
		
	</view>
</template>

<script>
	import { queryOrderById } from '@/api/settled'
	export default {
		name: 'Settled',
		data() {
			return {
				orderId: '',
				orderVo: ''
			}
		},
		onLoad: function(opt) {
			this.orderId = opt.orderId
			this.queryOrderById()
		},
		created() {
			
		},
		methods: {
			queryOrderById(){
				if(this.orderId){
					queryOrderById(this.orderId).then(res=>{
						console.log(res);
					})
				}
			}
		},
	}
</script>

<style>

</style>
