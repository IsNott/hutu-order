<template>
	<uni-list :border="false">
		<uni-list-item direction="column" :customStyle="itemStyle" v-for="(item,index) in menuCatalog" :key="item.id"
			:clickable="true" :border="false" ref="listItems" @click="handlerCatalogClick(item.id)">
			<template v-slot:body>
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
				// this.menuCataActiveId = id;
				this.$emit('menuClick', id)
			},triggerFirstItemClick() {
			      if (!this.firstItemClicked && this.$refs.listItems && this.$refs.listItems.length > 0) {
			        this.$refs.listItems[0].$el.click();
			        this.firstItemClicked = true;
			      }
			    }
		},
		mounted() {
			// 在组件挂载后，通过 setTimeout 延迟执行，确保 DOM 渲染完成
			    setTimeout(() => {
			      this.triggerFirstItemClick();
			    }, 100);
		}
	}
</script>

<style scoped>
	.catalog-name {
		font-size: 12px;
		font-weight: 650;
	}

	.catalog-img {
		margin: auto;
		width: 36px;
		height: 36px;
	}
</style>