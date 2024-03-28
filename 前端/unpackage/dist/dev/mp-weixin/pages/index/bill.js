"use strict";
const common_vendor = require("../../common/vendor.js");
if (!Array) {
    const _easycom_uni_datetime_picker2 = common_vendor.resolveComponent("uni-datetime-picker");
    _easycom_uni_datetime_picker2();
}
const _easycom_uni_datetime_picker = () => "../../uni_modules/uni-datetime-picker/components/uni-datetime-picker/uni-datetime-picker.js";
if (!Math) {
    _easycom_uni_datetime_picker();
}
const _sfc_main = {
    __name: "bill",
    setup(__props) {
        common_vendor.ref(2024);
        common_vendor.ref(3);
        const money = common_vendor.ref(0);
        const single = common_vendor.ref("2024-3-1");
        const data = common_vendor.ref([
            {
                name: "爱情麻辣烫",
                date: "3月20日 17:18",
                money: "24.00"
            },
            {
                name: "爱情麻辣烫",
                date: "3月20日 17:18",
                money: "24.00"
            },
            {
                name: "爱情麻辣烫",
                date: "3月20日 17:18",
                money: "24.00"
            },
            {
                name: "爱情麻辣烫",
                date: "3月20日 17:18",
                money: "24.00"
            }
        ]);
        return (_ctx, _cache) => {
            return {
                a: common_vendor.t(single.value),
                b: common_vendor.o(($event) => single.value = $event),
                c: common_vendor.p({
                    type: "date",
                    modelValue: single.value
                }),
                d: common_vendor.t(money.value),
                e: common_vendor.f(data.value, (item, index, i0) => {
                    return {
                        a: common_vendor.t(item.name),
                        b: common_vendor.t(item.date),
                        c: common_vendor.t(item.money),
                        d: index
                    };
                })
            };
        };
    }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/uniapp/demo-project/pages/index/bill.vue"]]);
wx.createPage(MiniProgramPage);
