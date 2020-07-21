<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客户管理</title>
    <#include "../common/link.ftl">

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <#include "../common/navbar.ftl">
    <!--菜单回显-->
    <#assign currentMenu="customer">
    <#include "../common/menu.ftl">
    <div class="content-wrapper">
        <section class="content-header">
            <h1>客户管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/customer/list.do" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${(qo.keyword)!''}" placeholder="请输入姓名/电话">
                        </div>
                        <div class="form-group">
                            <label for="status">状态:</label>
                            <select class="form-control" id="status" name="status">
                                <option value="-1">全部</option>
                                <option value="0">潜在客户</option>
                                <option value="1">正式客户</option>
                                <option value="2">资源池客户</option>
                                <option value="3">失败客户</option>
                                <option value="4">流失客户</option>
                            </select>
                            <script>
                                $("#status option[value='${qo.status}']").prop("selected", true);
                            </script>
                        </div>

                        <div class="form-group">
                            <label for="seller">市场专员:</label>
                            <select class="form-control" id="seller" name="sellerId">
                                <option value="-1">全部</option>

                        <#list sellers as e>
                        <option value="${e.id}">${e.name}</option>
                        </#list>
                            </select>
                            <script>
                                $("#seller option[value='${qo.sellerId}']").prop("selected", true);
                            </script>
                        </div>
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                    </form>
                </div>
                <table class="table table-striped table-hover" >
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>名称</th>
                        <th>性别</th>
                        <th>年龄</th>
                        <th>电话</th>
                        <th>QQ</th>
                        <th>职业</th>
                        <th>来源</th>
                        <th>市场专员</th>
                        <th>状态</th>
                    </tr>
                    </thead>
                   <#list pageInfo.list as entity>
                       <tr>
                           <td>${entity_index+1}</td>
                           <td>${entity.name}</td>
                           <td>${entity.displayGender}</td>
                           <td>${entity.age}</td>
                           <td>${entity.tel}</td>
                           <td>${entity.qq}</td>
                           <td>${(entity.job.title)!''}</td>
                           <td>${(entity.source.title)!''}</td>
                           <td>${(entity.seller.name)!''}</td>
                           <td>${entity.displayStatus}</td>

                       </tr>
                   </#list>
                </table>
                <#--分页-->
                <#include "../common/page.ftl"/>
            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>


</body>
</html>
