"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  __name: "login",
  setup(__props) {
    const phone_num = common_vendor.ref();
    const messg_num = common_vendor.ref();
    function onInputOfPhone(e) {
      phone_num.value = e.detail;
    }
    function onInputOfMess(e) {
      messg_num.value = e.detail;
    }
    function btnSend() {
    }
    return (_ctx, _cache) => {
      return {
        a: common_vendor.o(onInputOfPhone),
        b: common_vendor.o(onInputOfMess),
        c: common_vendor.o(btnSend)
      };
    };
  }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/life-pay-app/前端/pages/login/login.vue"]]);
wx.createPage(MiniProgramPage);
