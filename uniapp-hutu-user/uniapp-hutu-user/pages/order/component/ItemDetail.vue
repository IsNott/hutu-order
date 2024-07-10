<template>
</template>

<script>
	const empty = {
		actuallyAmount: '',
		itemDesc: '',
		itemId: '',
		itemImageUrls: '',
		itemName:'',
		itemTag:'',
		memuCatalogId: '',
		originAmount: '',
		special: ''
	}
	export default{
		name:'ItemDetail',
		props:{
			item:{
				type: Object,
				default: empty
			},
			skuList:{
				type: Array,
				default: []
			}
		}
	}
</script>

<style>
</style>