"use strict";
const common_vendor = require("../../common/vendor.js");
if (!Array) {
    const _easycom_uni_indexed_list2 = common_vendor.resolveComponent("uni-indexed-list");
    _easycom_uni_indexed_list2();
}
const _easycom_uni_indexed_list = () => "../../uni_modules/uni-indexed-list/components/uni-indexed-list/uni-indexed-list.js";
if (!Math) {
    _easycom_uni_indexed_list();
}
const _sfc_main = {
    __name: "city",
    setup(__props) {
        const citylist = [{
            "letter": "A",
            "data": [
                "阿克苏机场",
                "阿拉山口机场",
                "阿勒泰机场",
                "阿里昆莎机场",
                "安庆天柱山机场",
                "澳门国际机场"
            ]
        }, {
            "letter": "B",
            "data": [
                "保山机场",
                "包头机场",
                "北海福成机场",
                "北京南苑机场",
                "北京首都国际机场"
            ]
        }];
        let eventChannel;
        common_vendor.onLoad((option) => {
            eventChannel = common_vendor.getCurrentInstance().proxy.getOpenerEventChannel();
            console.log(eventChannel);
        });

        function bindClick(obj) {
            eventChannel.emit("acceptDataFromOpenedPage", {name: obj.item.name});
            common_vendor.index.navigateBack();
        }

        return (_ctx, _cache) => {
            return {
                a: common_vendor.o(bindClick),
                b: common_vendor.p({
                    options: citylist,
                    ["show-select"]: false
                })
            };
        };
    }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/uniapp/demo-project/pages/city_choice/city.vue"]]);
wx.createPage(MiniProgramPage);
