"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  __name: "user",
  setup(__props) {
    const userName = common_vendor.ref("用户名");
    const img_url = common_vendor.ref("/static/icon/账单.png");
    const userId = common_vendor.ref();
    const phone = common_vendor.ref();
    common_vendor.onShow(() => {
      const userInfo = common_vendor.wx$1.getStorageSync("userInfo");
      if (userInfo) {
        userName.value = userInfo.user.username;
        img_url.value = userInfo.user.img_url;
        userId.value = userInfo.id;
        phone.value = userInfo.phonenumber;
      }
    });
    function toBill() {
      common_vendor.wx$1.navigateTo({
        url: "/pages/index/bill"
      });
    }
    function changeUser() {
      common_vendor.wx$1.navigateTo({
        url: "/pages/login/index"
      });
    }
    return (_ctx, _cache) => {
      return {
        a: img_url.value,
        b: common_vendor.t(userName.value),
        c: common_vendor.o(toBill),
        d: common_vendor.o(changeUser)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/index/user.vue"]]);
wx.createPage(MiniProgramPage);
