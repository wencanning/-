"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  __name: "index",
  setup(__props) {
    function toLogin() {
      common_vendor.index.navigateTo({
        url: "/pages/login/login"
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(toLogin)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/login/index.vue"]]);
wx.createPage(MiniProgramPage);
