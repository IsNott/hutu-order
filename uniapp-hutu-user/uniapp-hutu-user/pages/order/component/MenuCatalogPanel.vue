<template>
	<uni-list :border="false">
		<uni-list-item direction="column" :customStyle="itemStyle" v-for="(item,index) in menuCatalog" :key="item.id"
			:clickable="true" :border="false" ref="listItems" @click="handlerCatalogClick(item.id)">
			<template class="panel-body" v-slot:body>
				<image class="catalog-img" src="@/static/item/coffee.png" mode="aspectFit"></image>
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
					backgroundColor: '#F5F5F5',
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
					// this.$refs.listItems[0].$el.click();
					this.$nextTick(() => {
						console.log(2, this.$refs.listItems)
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
		font-size: 12px;
		font-weight: 650;
	}

	.catalog-img {
		margin: auto;
		width: 36px;
		height: 36px;
	}

	.panel-body {
		display: flex;
		flex-direction: row;
	}
</style>