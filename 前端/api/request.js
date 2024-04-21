const baseUrl = 'http://localhost:8080';

//发送请求
function requestApi(url,data={},method='GET') {
	console.log('网络请求的参数为:', data);
	return new Promise((resolev, reject)=>{
		wx.request({
			url:baseUrl+url,
			method:method,
			data:data,
			header:{
				'content-type': 'application/json' 
			},
			success:(res)=>{
				console.log('网络请求的结果为', res);
				const status = Number(res.data.statusCode);
				switch(status) {
					case 200:
					resolev(res.data);
					wx.hideLoading();
					break;
					case 404:
					console.error('接口不存在');
					console.error(res.data.message);
					reject('404');
					wx.hideLoading();
					break;
					case 401:
					showToast('请先登录');
					uni.navigateTo({url:'/pages/login/index'});
					wx.hideLoading();
					break;
					case 500:
					showToast('发生异常错误');
					console.error('服务器错误');
					console.error(res.data.message);
					reject('500');
					wx.hideLoading();
					break;
					case 501:
					showToast('发生异常错误');
					console.error('服务器错误');
					console.error(res.data.message);
					reject('501');
					wx.hideLoading();
					break;
					case 502:
					showToast('发生异常错误');
					console.error('服务器错误');
					console.error(res.data.message);
					reject('502');
					wx.hideLoading();
					break;
					case 400:
					console.error('参数不对');
					console.error(res.data.message);
					reject('400');
					wx.hideLoading();
					break;
				}
			},
			fail:(err)=>{
				console.log(err);
				reject(err);
				wx.hideLoading();
			}
		})
	})
}

function showToast(title) {
	wx.showToast({
	  title,
	  icon: 'none',
	  duration: 1500
	})
}

export{requestApi}