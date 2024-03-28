"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
    __name: "index-003",
    setup(__props) {
        const phone_number = common_vendor.ref("");
        const password_number = common_vendor.ref("");
        return (_ctx, _cache) => {
            return {
                a: phone_number.value,
                b: common_vendor.o(($event) => phone_number.value = $event.detail.value),
                c: password_number.value,
                d: common_vendor.o(($event) => password_number.value = $event.detail.value),
                e: common_vendor.t(phone_number.value),
                f: common_vendor.t(password_number.value)
            };
        };
    }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/uniapp/demo-project/pages/index/index-003.vue"]]);
wx.createPage(MiniProgramPage);
