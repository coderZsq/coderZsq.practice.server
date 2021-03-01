var vue = new Vue({
    el:"#app",
    data:{
        page:{},
        themes:[],
        toasts:[]
    },
    methods:{
        themeSelect:function(themeId, event){
            $("._j_tag").removeClass("on")
            $(event.currentTarget).addClass("on");
            vue.doPage(1);
        },
        doPage:function(page){
            var param = getParams();
            var themeId = $("._j_tag.on").data("tid");
            ajaxGet("/strategies/query",{themeId:themeId, destId:param.destId, currentPage:page}, function (data) {
                vue.page = data.data;
                buildPage(vue.page.current, vue.page.pages,vue.doPage);
            })
        }
    },
    mounted:function () {
        var param = getParams();
        //吐司
        ajaxGet("/destinations/toasts",{destId:param.destId}, function (data) {
            var list = data.data;
            vue.toasts = list;
        })

        //攻略主题
        ajaxGet("/strategies/themes", {}, function (data) {
            vue.themes = data.data;
        })
        //攻略分页
       /* ajaxGet("/strategies/query", {destId:param.destId}, function (data) {
            //data.data;
            vue.page = data.data;
            buildPage(vue.page.current, vue.page.pages,vue.doPage);  //构建分页条件
        })*/
        //攻略分页列表
        this.doPage(1);



















       /* //攻略主题
        ajaxGet("/strategies/themes",{}, function (data) {
            vue.themes = data.data;
        })
        //攻略分页列表
       this.doPage(1);*/
    }
});

