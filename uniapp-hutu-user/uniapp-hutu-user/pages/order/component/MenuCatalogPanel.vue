<template>
	<uni-list :border="false">
		<uni-list-item direction="column" v-for="(item,index) in menuCatalog" :key="item.id"
			:clickable="true" :border="false" ref="listItems" @click="handlerCatalogClick(item.id)">
			<template class="panel-body" v-slot:body>
				<image class="catalog-img" :src="item.imgUrl ? baseUrl + item.imgUrl : require('@/static/image/not-image.png')" mode="aspectFit"></image>
				<text class="catalog-name">{{ item.catalogName}}</text>
			</template>
		</uni-list-item>
	</uni-list>
</template>

<script>
	export default {
		name: 'MenuCatalogPanel',
		props: {
			menuCatalog: {
				type: Array,
				default: []
			}
		},
		data() {
			return {
				itemStyle: {
					backgroundColor: 'white',
					borderStyle: 'none'
				},
				currentIndex: 0,
				firstItemClicked: false
			}
		},
		methods: {
			handlerCatalogClick(id) {
				this.$emit('menuClick', id)
			},
			triggerFirstItemClick() {
				if (!this.firstItemClicked && this.$refs.listItems && this.$refs.listItems.length > 0) {
					this.$nextTick(() => {
						this.firstItemClicked = true;
					})
				}
			}
		}
	}
</script>

<style scoped>
	.catalog-name {
		display: block;
		font-size: 16px;
		font-weight: 400;
	}

	.catalog-img {
		margin: auto;
		width: 52px;
		height: 64px;
	}

	.panel-body {
		display: flex;
		flex-direction: row;
	}
</style>