<template>
	<view class="container">
		<view class="nav">
			<view class="nav-1">
				<image src="/static/payitem/火.png"></image>
				<text>燃气费</text>
			</view>
			<text class="nav-city">武汉市</text>
		</view>
		<view class="pay-card">
			<view class="card-item">
				<text>缴费单位</text>
				<text>武汉燃气有限公司</text>
			</view>
			<view class="card-item">
				<text>缴费户号</text>
				<text>12345678</text>
			</view>
		</view>
	</view>
</template>

<script>
</script>

<style>
	page {
		background-color: #f1f3f5;
	}
	
	text {
		letter-spacing: 2rpx;
	}
	.container {
		padding: 0 25rpx;
	}
	.nav {
		display: flex;
		align-items: center;
		justify-content: space-between;
		padding-top: 40rpx;
		padding-bottom: 50rpx;
	}
	.nav image {
		height: 70rpx;
		width: 70rpx;
	}
	
	.nav-city {
		font-size: 30rpx;
	}
		
	.nav-1 {
		display: flex;
		align-items: center;
		justify-content: space-between;
		gap: 10rpx;
		font-weight: bold;
		font-size: 38rpx;
	}

	.pay-card {
		background-color: #fff;
		border-radius: 10rpx;
		box-shadow: 0 0 50rpx rbga(0, 0, 0, 0.1);
		display: flex;
		flex-direction: column;
		padding: 40rpx 45rpx;
	}
</style>