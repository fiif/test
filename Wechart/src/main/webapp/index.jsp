<html>
<head>
    <script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<%--微信显示的位置--%>
<div id="weixin"></div>
</body>
<script>

    window.onload = function () {
        var obj = new WxLogin({
            id: "weixin",//根据id显示位置
            appid: "wx3bdb1192c22883f3",
            scope: "snsapi_login",//固定值
            redirect_uri: "http://note.java.itcast.cn/weixinlogin.do"
        });}
</script>
</html>
