"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  __name: "success",
  setup(__props) {
    function btnFinish() {
      common_vendor.wx$1.switchTab({
        url: "/pages/index/index"
      });
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(btnFinish)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/pay/success.vue"]]);
wx.createPage(MiniProgramPage);
