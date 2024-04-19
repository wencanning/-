"use strict";
const common_vendor = require("../../common/vendor.js");
const api_request = require("../../api/request.js");
const _sfc_main = {
  __name: "login",
  setup(__props) {
    const phone_num = common_vendor.ref();
    const messg_num = common_vendor.ref();
    common_vendor.ref();
    function onInputOfPhone(e) {
      phone_num.value = e.detail;
    }
    function onInputOfMess(e) {
      messg_num.value = e.detail;
    }
    function btnLogin() {
      common_vendor.wx$1.login({
        success: async (res) => {
          if (res.code) {
            await api_request.requestApi(
              "/login",
              {
                phone: phone_num,
                messg: messg_num
              },
              "POST"
            );
          }
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
