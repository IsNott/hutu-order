<template>
	<view>
		<uni-search-bar placeholder="搜索商品"
		 cancelButton="false"
			radius=20
			v-model="searchText"
			maxlength=56
		  @confirm="search"
		  @cancel="cancel"
		  bgColor="#EEEEEE"
		  >
			<uni-icons slot="searchIcon" color="#999999" size="18" type="cart" />
		</uni-search-bar>
	</view>
</template>

<script>
	export default{
		data(){
			return{
				
			}
		}
	}
</script>

<style>
</style>