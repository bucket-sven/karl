<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <title>用户授权</title>
    <link href="/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/css/animate.css" rel="stylesheet">
    <link href="/css/style.css?v=4.1.0" rel="stylesheet">
    <link
            href="//cdn.bootcss.com/zTree.v3/3.5.24/css/zTreeStyle/zTreeStyle.css"
            rel="stylesheet">
    <link href="/css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="//cdn.bootcss.com/iCheck/1.0.2/skins/all.css"
          rel="stylesheet">

    <script>
        var _adminId = "${admin.id}";
    </script>
</head>

<body class="gray-bg">
<div class="row wrapper border-bottom white-bg page-heading">
    <div class="col-lg-10">
        <h2>用户权限管理</h2>
        <ol class="breadcrumb">
            <li><a href="index.html">主页</a></li>
            <li><a href="/admin/index.html">用户管理</a></li>
            <li><a>用户权限管理</a></li>
        </ol>
    </div>
    <div class="col-lg-2"></div>
</div>

<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="ibox">
            <div class="ibox-title">
                <h5>用户权限信息</h5>
            </div>
            <div class="ibox-content">
                <div class="row">
                    <form class="form-horizontal" action="/admin/oauthsubmit"
                          method="post" id="adminOauthForm">
                        <input type="hidden" value="${admin.id}" name="id"/>
                        <div class="form-group">
                            <div class="form-group ">
                                <label class="col-lg-3 control-label">用户名称：</label>
                                <div class="col-lg-8">
                                    <span type="text" class="form-control">${admin.user.username}</span>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="form-group ">
                                <label class="col-lg-3 control-label">角色选择：</label>
                                <div class="col-lg-8">
                                <#list roles as role>
                                    <label class="radio-inline i-checks">
                                        <input type="checkbox" value="${role.id}"
                                            <#list admin.roles as r>
                                                <#if r.id == role.id>
                                               checked
                                                </#if>
                                            </#list>
                                               name="roleIds">
                                        <i></i> ${role.name}
                                    </label>
                                </#list>
                                </div>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">保存</button>
                                <button type="button" class="btn btn-white"
                                        onclick="history.go(-1);">返回
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript"
        src="http://apps.bdimg.com/libs/jquery/1.9.1/jquery.js"></script>
<script
        src="//cdn.bootcss.com/zTree.v3/3.5.24/js/jquery.ztree.all.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.js"></script>
<script src="/js/plugins/sweetalert/sweetalert.min.js"></script>
<script src="//cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<script src="/js/pages/admin/oauth.js"></script>
<!-- 第三方插件 -->
<script src="/js/plugins/pace/pace.min.js"></script>
</html>