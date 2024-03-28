<template>
	<radio-group @change="radioChange">
		<label v-for="(item, index) in data" :key="index">
			<view>
				<radio :value="item.value" :checked="item.checked"></radio>
				<text>{{item.name}}</text>
			</view>
		</label>
	</radio-group>
</template>

<script setup>
	import {ref} from 'vue';
	
	const data = ref([
		{
			name: '挪威的森林',
			value: '001',
			checked: false
		},
		{
			name: '晴天',
			value: '002',
			checked: false
		},
		{
			name: '夕阳无限好',
			value: '003',
			checked: false
		}
	])
	
	function radioChange(event) {
		console.log(event);
		if(event.detail.value == '002') {
			console.log("yes");
		}else {
			console.log("no");
		}
	}
</script>

<style>
</style>