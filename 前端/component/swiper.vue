<template>
	<swiper indicator-dots autoplay circular>
		<swiper-item v-for="(item, index) in swiperData" :key="index">
			<image :src="item.url"  @click="btn(index)" mode="aspectFit"></image> 
		</swiper-item>
	</swiper>
</template>

<script setup>
	import {ref, defineProps} from 'vue'
	defineProps({
		swiperData:Array
	})
	function btn(index) {
		uni.navigateTo({
			url:'/pages/detail/index?index='+index
		})
	}
</script>

<style>
</style>