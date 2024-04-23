"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  __name: "user",
  setup(__props) {
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
        a: common_vendor.o(toBill),
        b: common_vendor.o(changeUser)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/index/user.vue"]]);
wx.createPage(MiniProgramPage);
