<template>
	<input placeholder="请输入手机号码" v-model="phone"> 
</template>

<script setup> 
	import {ref, watch} from 'vue';
	const phone = ref('');
	watch(phone, (newval, oldval)=>{
		console.log(newval);
	});
</script>

<style>
	input {
		background-color: antiquewhite;
	}
</style>