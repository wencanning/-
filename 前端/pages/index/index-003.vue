<template>
	<input placeholder="请输入手机号码"  v-model="phone_number">  
	<input placeholder="请输入密码" :password="true" v-model="password_number">
	<button>登录</button>
	<text>{{phone_number}}</text>
	<text>{{password_number}}</text>
</template>

<script setup>
	import {ref} from 'vue';
	const phone_number = ref('');
	const password_number = ref('');
	
</script>

<style>
	text, input {
		margin-bottom: 10px;
	}
	text {
		display: block;
	}
</style>