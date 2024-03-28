"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = {
    __name: "index",
    setup(__props) {
        const cityName = common_vendor.ref("武汉");
        const data = common_vendor.ref([
            {
                url: "/static/payitem/电费.png",
                name: "电费"
            },
            {
                url: "/static/payitem/水.png",
                name: "水费"
            },
            {
                url: "/static/payitem/火.png",
                name: "燃气费"
            },
            {
                url: "/static/payitem/电话.png",
                name: "固话费"
            }
        ]);

        function changeCity() {
            common_vendor.index.navigateTo({
                url: "/pages/city_choice/city",
                events: {
                    acceptDataFromOpenedPage: function (data2) {
                        console.log(data2);
                        cityName.value = data2.name;
                    }
                }
            });
        }

        function toPayChoice() {
            common_vendor.index.navigateTo({
                url: "/pages/detail/pay_choice"
            });
        }

        return (_ctx, _cache) => {
            return {
                a: common_vendor.t(cityName.value),
                b: common_vendor.o(changeCity),
                c: common_vendor.f(data.value, (item, index, i0) => {
                    return {
                        a: item.url,
                        b: common_vendor.t(item.name),
                        c: index,
                        d: common_vendor.o(($event) => toPayChoice(), index)
                    };
                })
            };
        };
    }
};
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "D:/Project/uniapp/demo-project/pages/index/index.vue"]]);
wx.createPage(MiniProgramPage);
