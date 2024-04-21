"use strict";
const common_vendor = require("../../common/vendor.js");
const api_request = require("../../api/request.js");
const _sfc_main = {
  __name: "login",
  setup(__props) {
    let phone_num = "";
    let messg_num = "";
    common_vendor.ref();
    function onInputOfPhone(e) {
      phone_num = e.detail;
    }
    function onInputOfMess(e) {
      messg_num = e.detail;
    }
    function btnLogin() {
      common_vendor.wx$1.showLoading({ title: "登录中" });
      common_vendor.wx$1.login({
        success: async (res) => {
          const userData = await api_request.requestApi(
            "/login",
            {
              phone: phone_num.value,
              message: messg_num.value
            },
            "POST"
          );
          console.log(userData);
          common_vendor.wx$1.setStorageSync("userInfo", userData.data);
          common_vendor.wx$1.hideLoading();
          common_vendor.wx$1.switchTab({
            url: "/pages/index/index"
            // 替换成你的 tabBar 页面路径
          });
        }
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(onInputOfPhone),
        b: common_vendor.o(onInputOfMess),
        c: common_vendor.o((...args) => _ctx.btnSend && _ctx.btnSend(...args)),
        d: common_vendor.o(btnLogin)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/login/login.vue"]]);
wx.createPage(MiniProgramPage);
