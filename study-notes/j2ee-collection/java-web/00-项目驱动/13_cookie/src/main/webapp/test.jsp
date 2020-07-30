<%--
  Created by IntelliJ IDEA.
  User: zhushuangquan
  Date: 2020/7/30
  Time: 12:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="jquery.min.js"></script>
    <script>
        function get() {
            // 创建XMLHttpRequest对象
            const xhr = new XMLHttpRequest()
            // 配置请求方法和URL (第3个参数如果是true代表异步, false代表同步, 默认是true)
            xhr.open('GET', 'http://localhost:8080/tc/test?name=sq')
            // 服务器返回的数据格式
            xhr.responseType = 'json'
            // 发送请求
            xhr.send()
            // 监听响应
            xhr.onload = function () {
                if (xhr.status !== 200) return

                // 打印服务器返回的数据
                console.log(xhr.response)
            }
        }

        function post() {
            // 创建XMLHttpRequest对象
            const xhr = new XMLHttpRequest()
            // 配置请求方法和URL
            xhr.open('POST', 'http://localhost:8080/tc/test')
            // 服务器返回的数据格式
            xhr.responseType = 'json'
            // 请求头 (以表单形式提交)
            xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
            // 发送请求 (如果是POST请求, 需要传递请求体)
            xhr.send('name=sq')
            // 监听响应
            xhr.onload = function () {
                if (xhr.status !== 200) return

                // 打印服务器返回的数据
                console.log(xhr.response)
            }
        }

        function post2() {
            // 创建XMLHttpRequest对象
            const xhr = new XMLHttpRequest()
            // 配置请求方法和URL
            xhr.open('POST', 'http://localhost:8080/tc/test')
            // 服务器返回的数据格式
            xhr.responseType = 'json'
            // 请求头 (以表单-文件上传的形式)
            const body = new FormData()
            body.append('name', 'sq')
            xhr.send(body)
            // 监听响应
            xhr.onload = function () {
                if (xhr.status !== 200) return

                // 打印服务器返回的数据
                console.log(xhr.response)
            }
        }

        function j_ajax() {
            $.ajax({
                method: 'POST',
                url: 'http://localhost:8080/tc/test',
                data: {name: 'sq'},
                dataType: 'json',
                success: function (data) {
                    console.log(data)
                }
            })
        }

        function j_get() {
            // $.get('http://localhost:8080/tc/test', {name, 'sq'}, function (data) {
            //     console.log(data)
            // }, 'json')

            $.getJSON('http://localhost:8080/tc/test', {name: 'sq'}, function (data) {
                console.log(data)
            })
        }

        function j_post() {
            $.post('http://localhost:8080/tc/test', {name: 'sq'}, function (data) {
                console.log(data)
            }, 'json')
        }
    </script>
</head>
<body>
<button onclick="get()">GET</button>
<button onclick="post()">POST</button>
<button onclick="post2()">POST2</button>
<button onclick="j_ajax()">$.jax</button>
<button onclick="j_get()">$.get</button>
<button onclick="j_post()">$.post</button>
</body>
</html>
