<%--
  Created by IntelliJ IDEA.
  User: jack
  Date: 2020/1/29
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
</head>
<body class="no-skin" youdao="bind" style="background: white">
<input id="gritter-light" checked="" type="checkbox" class="ace ace-switch ace-switch-5"/>

<div class="page-header">
    <h1>
        权限模块管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            维护权限模块和权限点关系
        </small>
    </h1>
</div>
<div class="main-content-inner">
    <div class="col-sm-3">
        <div class="table-header">
            权限模块列表&nbsp;&nbsp;
            <a class="green" href="#">
                <i class="ace-icon fa fa-plus-circle orange bigger-130 aclModule-add"></i>
            </a>
        </div>
        <div id="aclModuleList">
        </div>
    </div>
    <div class="col-sm-9">
        <div class="col-xs-12">
            <div class="table-header">
                权限点列表&nbsp;&nbsp;
                <a class="green" href="#">
                    <i class="ace-icon fa fa-plus-circle orange bigger-130 acl-add"></i>
                </a>
            </div>
            <div>
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="dataTables_length" id="dynamic-table_length"><label>
                                展示
                                <select id="pageSize" name="dynamic-table_length" aria-controls="dynamic-table" class="form-control input-sm">
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select> 条记录 </label>
                            </div>
                        </div>
                        <div class="col-xs-6">
                            <div id="dynamic-table_filter" class="dataTables_filter">
                                <label>
                                    搜索:
                                    <input type="search" class="form-control input-sm">
                                </label>
                            </div>
                        </div>
                    </div>
                    <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid"
                           aria-describedby="dynamic-table_info" style="font-size:14px">
                        <thead>
                        <tr role="row">
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                权限名称
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                权限模块
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                类型
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                URL
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                状态
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                顺序
                            </th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label=""></th>
                        </tr>
                        </thead>
                        <tbody id="aclList"></tbody>
                    </table>
                    <div class="row" id="aclPage">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialog-aclModule-form" style="display: none;">
    <form id="aclModuleForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="parentId">上级模块</label></td>
                <td>
                    <select id="parentId" name="parentId" data-placeholder="选择模块" style="width: 200px;"></select>
                    <input type="hidden" name="id" id="aclModuleId"/>
                </td>
            </tr>
            <tr>
                <td><label for="aclModuleName">名称</label></td>
                <td><input type="text" name="name" id="aclModuleName" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclModuleSeq">顺序</label></td>
                <td><input type="text" name="seq" id="aclModuleSeq" value="1" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclModuleStatus">状态</label></td>
                <td>
                    <select id="aclModuleStatus" name="status" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                        <option value="2">删除</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclModuleRemark">备注</label></td>
                <td><textarea name="remark" id="aclModuleRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="dialog-acl-form" style="display: none;">
    <form id="aclForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="parentId">所属权限模块</label></td>
                <td>
                    <select id="aclModuleSelectId" name="aclModuleId" data-placeholder="选择权限模块" style="width: 200px;"></select>
                </td>
            </tr>
            <tr>
                <td><label for="aclName">名称</label></td>
                <input type="hidden" name="id" id="aclId"/>
                <td><input type="text" name="name" id="aclName" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclType">类型</label></td>
                <td>
                    <select id="aclType" name="type" data-placeholder="类型" style="width: 150px;">
                        <option value="1">菜单</option>
                        <option value="2">按钮</option>
                        <option value="3">其他</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclUrl">URL</label></td>
                <td><input type="text" name="url" id="aclUrl" value="1" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclStatus">状态</label></td>
                <td>
                    <select id="aclStatus" name="status" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="aclSeq">顺序</label></td>
                <td><input type="text" name="seq" id="aclSeq" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="aclRemark">备注</label></td>
                <td><textarea name="remark" id="aclRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<script id="aclModuleListTemplate" type="x-tmpl-mustache">
<ol class="dd-list ">
    {{#aclModuleList}}
        <li class="dd-item dd2-item aclModule-name {{displayClass}}" id="aclModule_{{id}}" href="javascript:void(0)" data-id="{{id}}">
            <div class="dd2-content" style="cursor:pointer;">
            {{name}}
            &nbsp;
            <a class="green {{#showDownAngle}}{{/showDownAngle}}" href="#" data-id="{{id}}" >
                <i class="ace-icon fa fa-angle-double-down bigger-120 sub-aclModule"></i>
            </a>
            <span style="float:right;">
                <a class="green aclModule-edit" href="#" data-id="{{id}}" >
                    <i class="ace-icon fa fa-pencil bigger-100"></i>
                </a>
                &nbsp;
                <a class="red aclModule-delete" href="#" data-id="{{id}}" data-name="{{name}}">
                    <i class="ace-icon fa fa-trash-o bigger-100"></i>
                </a>
            </span>
            </div>
        </li>
    {{/aclModuleList}}
</ol>
</script>

<script id="aclListTemplate" type="x-tmpl-mustache">
{{#aclList}}
<tr role="row" class="acl-name odd" data-id="{{id}}"><!--even -->
    <td><a href="#" class="acl-edit" data-id="{{id}}">{{name}}</a></td>
    <td>{{showAclModuleName}}</td>
    <td>{{showType}}</td>
    <td>{{url}}</td>
    <td>{{#bold}}{{showStatus}}{{/bold}}</td>
    <td>{{seq}}</td>
    <td>
        <div class="hidden-sm hidden-xs action-buttons">
            <a class="green acl-edit" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-pencil bigger-100"></i>
            </a>
            <a class="red acl-role" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-flag bigger-100"></i>
            </a>
        </div>
    </td>
</tr>
{{/aclList}}
</script>

<script type="text/javascript">
    $(function() {
        var aclModuleList; // 存储树形部门列表
        var aclModuleMap = {}; // 存储map格式的权限模块信息
        var aclMap = {}; // 存储map格式的权限点信息
        var optionStr = ""; // 选择的权限项
        var lastClickAclModuleId = -1;  // 上一次点击的权限模块ID

        var aclModuleListTemplate = $('#aclModuleListTemplate').html();
        Mustache.parse(aclModuleListTemplate);
        var aclListTemplate = $("#aclListTemplate").html();
        Mustache.parse(aclListTemplate);

        // 初始加载部门树
        loadAclModuleTree();

        function loadAclModuleTree() {
            $.ajax({
                url: "/sys/aclModule/tree.json",
                success : function (result) {
                    if (result.ret) {
                        aclModuleList = result.data;
                        // 渲染逻辑
                        var rendered = Mustache.render(aclModuleListTemplate, {
                            aclModuleList : result.data,
                            "showDownAngle" : function () {
                                return function (text, render) {
                                    return (this.aclModuleList && this.aclModuleList.length > 0) ? "" : "hidden";
                                }
                            },
                            "displayClass" : function () {
                                return "";
                            }
                        });
                        $("#aclModuleList").html(rendered);
                        // 渲染层级树
                        recursiveRenderAclModule(result.data);
                        // 绑定点击事件
                        bindAclModuleClick();
                    } else {
                        showMessage("加载权限模块",result.msg, false);
                    }
                }
            })
        }

        $(".aclModule-add").click(function() {
            $("#dialog-aclModule-form").dialog({
                model: true,
                title: "新增权限模块",
                open: function(event, ui) {
                    $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                    optionStr = "<option value=\"0\">-</option>";
                    recursiveRenderAclModuleSelect(aclModuleList, 1);
                    $("#aclModuleForm")[0].reset();
                    $("#parentId").html(optionStr);
                },
                buttons : {
                    "添加": function(e) {
                        e.preventDefault();
                        updateAclModule(true, function (data) {
                            $("#dialog-aclModule-form").dialog("close");
                            //loadAclModuleList(lastClickDeptId);
                        }, function (data) {
                            showMessage("新增权限模块", data.msg, false);
                        })
                    },
                    "取消": function () {
                        $("#dialog-aclModule-form").dialog("close");
                    }
                }
            });
        });

        function updateAclModule(isCreate, successCallBack, failCallback) {
            $.ajax({
                url: isCreate ? "/sys/aclModule/save.json" : "/sys/aclModule/update.json",
                data: $("#aclModuleForm").serializeArray(),
                type: "POST",
                success: function(result) {
                    if (result.ret) {
                        loadAclModuleTree();
                        if (successCallBack) {
                            successCallBack(result);
                        }
                    } else {
                        if (failCallback) {
                            failCallback(result);
                        }
                    }
                }
            })
        }

        // 渲染选择部门树
        function recursiveRenderAclModuleSelect(aclModuleList, level) {
            level = level | 0;
            if (aclModuleList && aclModuleList.length > 0 ) {
                $(aclModuleList).each(function (i, aclModule) {
                    aclModuleMap[aclModule.id] = aclModule;
                    var blank = "";
                    if (level > 1) {
                        for (var j = 3; j <= level; j++) {
                            blank += "..";
                        }
                        blank += "∟";
                    }
                    optionStr += Mustache.render("<option value='{{id}}'>{{name}}</option>", {id: aclModule.id, name: blank + aclModule.name });
                    if (aclModule.aclModuleList && aclModule.aclModuleList.length > 0) {
                        recursiveRenderAclModuleSelect(aclModule.aclModuleList, level +1 );
                    }
                })
            }
        }

        // 递归层级方法
        function recursiveRenderAclModule(aclModuleList) {
            if (aclModuleList && aclModuleList.length > 0){
                // 遍历权限模块
                $(aclModuleList).each(function(i, aclModule){
                    aclModuleMap[aclModule.id] = aclModule;
                    // 渲染子模块
                    if (aclModule.aclModuleList && aclModule.aclModuleList.length > 0) {
                        var rendered = Mustache.render(aclModuleListTemplate, {
                            aclModuleList : aclModule.aclModuleList,
                            "showDownAngle" : function () {
                                return function (text, render) {
                                    return (this.aclModuleList && this.aclModuleList.length > 0) ? "" : "hidden";
                                }
                            },
                            // 隐藏子模块
                            "displayClass" : function () {
                                return "hidden";
                            }
                        });
                        $("#aclModule_"+ aclModule.id).append(rendered);
                        // 递归渲染
                        recursiveRenderAclModule(aclModule.aclModuleList);
                    }

                });
            }
        }

        // 绑定点击事件
        function bindAclModuleClick() {
            // 向下箭头的点击事件
            $(".sub-aclModule").click(function(e) {
                e.preventDefault();
                e.stopPropagation();
                $(this).parent().parent().parent().children().children(".aclModule-name").toggleClass("hidden");
                // 箭头交互
                if ($(this).is(".fa-angle-double-down")) {
                    $(this).removeClass("fa-angle-double-down").addClass("fa-angle-double-up");
                } else {
                    $(this).removeClass("fa-angle-double-up").addClass("fa-angle-double-down");
                }
            });

            $(".aclModule-name").click(function(e){
                e.preventDefault();
                e.stopPropagation();
                var aclModuleId = $(this).attr("data-id");
                handleAclModuleSelected(aclModuleId);
            });

            // 编辑按钮事件
            $(".aclModule-edit").click(function(e){
                e.preventDefault();
                e.stopPropagation();
                // 获得编辑模块ID
                var aclModuleId = $(this).attr("data-id");
                $("#dialog-aclModule-form").dialog({
                    model: true,
                    title: "编辑权限模块",
                    open: function(event, ui) {
                        $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                        optionStr = "<option value=\"0\">-</option>";
                        recursiveRenderAclModuleSelect(aclModuleList, 1);
                        $("#aclModuleForm")[0].reset();
                        $("#parentId").html(optionStr);
                        $("#aclModuleId").val(aclModuleId);
                        // 填写信息
                        var targetAclModule = aclModuleMap[aclModuleId];
                        if (targetAclModule) {
                            $("#parentId").val(targetAclModule.parentId);
                            $("#aclModuleName").val(targetAclModule.name);
                            $("#aclModuleSeq").val(targetAclModule.seq);
                            $("#aclModuleStatus").val(targetAclModule.status);
                            $("#aclModuleRemark").val(targetAclModule.remark);
                        }
                    },
                    buttons : {
                        "更新": function(e) {
                            e.preventDefault();
                            updateAclModule(false, function (data) {
                                $("#dialog-aclModule-form").dialog("close");
                                //loadAclModuleList(lastClickDeptId);
                            }, function (data) {
                                showMessage("编辑权限模块", data.msg, false);
                            })
                        },
                        "取消": function () {
                            $("#dialog-aclModule-form").dialog("close");
                        }
                    }
                });
            });

            // 处理权限名称选中操作
            function handleAclModuleSelected(aclModuleId) {
                if (lastClickAclModuleId != -1) {
                    var lastAclModule = $("#aclModule_" + lastClickAclModuleId + " .dd2-content:first");
                    lastAclModule.removeClass("btn-yellow");
                    lastAclModule.removeClass("no-hover");
                }
                var currentAclModule = $("#aclModule_" + aclModuleId + " .dd2-content:first");;
                currentAclModule.addClass("btn-yellow");
                currentAclModule.addClass("no-hover");

                // 记录最后一次点击的权限模块ID
                lastClickAclModuleId = aclModuleId;
                // 加载用户
                loadAclList(aclModuleId);
            }
        }

        // 加载权限列表
        function loadAclList(aclModuleId) {
            var pageSize = $("pageSize").val();
            var url = "/sys/acl/page.json?aclModuleId=" + aclModuleId;
            var pageNo = $("#aclPage .pageNo").val() || 1;
            $.ajax({
                url : url,
                data : {
                    pageSize: pageSize,
                    pageNo: pageNo
                },
                success : function (result) {
                    renderAclListAndPage(result, url);
                }
            });
        }
        
        function renderAclListAndPage(result, url) {
            if (result.ret) {
                if (result.data.total > 0) {
                    var rendered = Mustache.render(aclListTemplate, {
                        aclList : result.data.data,
                        "showAclModuleName" : function () {
                            console.log(aclModuleMap[this.aclModuleId].name);
                            return aclModuleMap[this.aclModuleId].name;
                        },
                        "showStatus" : function () {
                            return this.status == 1 ? "有效" : "无效";
                        },
                        "showType" : function () {
                            return this.type == 1 ? "菜单" : (this.type == 2 ? "按钮" : "其他");
                        },
                        "bold": function () {
                            return function (text, render) {
                                // 取出render中的值
                                var status = render(text);
                                if (status == '有效') {
                                    return "<span class='label label-sm label-success'>有效</span>"
                                } else if(status == '无效') {
                                    return "<span class='label label-sm label-warning'>无效</span>"
                                } else {
                                    return "<span class='label'>删除</span>"
                                }
                            }
                        }
                    });
                    // 渲染到元素上
                    $("#aclList").html(rendered);
                    bindAclClick();
                    $.each(result.data.data, function(i, acl){
                        // 更新当前页面中aclMap的值
                        aclMap[acl.id] = acl;
                    });
                } else {
                    $("#aclList").html('');
                }
                // 渲染分页信息
                var pageSize = $("#pageSize").val();
                var pageNo = $("#aclPage .pageNo").val() || 1;
                renderPage(url,result.data.total, pageNo, pageSize,
                    result.data.total > 0 ? result.data.data.length : 0,
                    "aclPage", renderAclListAndPage);

            } else {
                showMessage("获取权限点列表", result.msg, false);
            }
        }

        //权限点的编辑
        function bindAclClick() {
            $(".acl-edit").click(function (e) {
                e.preventDefault();
                e.stopPropagation();
                var aclId = $(this).attr("data-id");
                $("#dialog-acl-form").dialog({
                    model: true,
                    title: "编辑权限",
                    open: function(event, ui) {
                        $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                        optionStr = "";
                        recursiveRenderAclModuleSelect(aclModuleList, 1);
                        $("#aclForm")[0].reset();
                        $("#aclModuleSelectId").html(optionStr);
                        // 取出当前的编辑权限点
                        var targetAcl = aclMap[aclId];
                        if (targetAcl) {
                            $("#aclId").val(aclId);
                            $("#aclModuleSelectId").val(targetAcl.aclModuleId);
                            $("#aclStatus").val(targetAcl.status);
                            $("#aclType").val(targetAcl.type);
                            $("#aclName").val(targetAcl.name);
                            $("#aclUrl").val(targetAcl.url);
                            $("#aclSeq").val(targetAcl.seq);
                            $("#aclRemark").val(targetAcl.remark);
                        }
                    },
                    buttons : {
                        "更新": function(e) {
                            e.preventDefault();
                            updateAcl(false, function (data) {
                                $("#dialog-acl-form").dialog("close");
                                //loadAclModuleList(lastClickDeptId);
                            }, function (data) {
                                showMessage("编辑权限", data.msg, false);
                            })
                        },
                        "取消": function () {
                            $("#dialog-acl-form").dialog("close");
                        }
                    }
                });
            });
        }
        
        $(".acl-add").click(function () {
            $("#dialog-acl-form").dialog({
                model: true,
                title: "新增权限",
                open: function(event, ui) {
                    $(".ui-dialog-titlebar-close", $(this).parent()).hide();
                    optionStr = "";
                    recursiveRenderAclModuleSelect(aclModuleList, 1);
                    $("#aclForm")[0].reset();
                    $("#aclModuleSelectId").html(optionStr);
                },
                buttons : {
                    "添加": function(e) {
                        e.preventDefault();
                        updateAcl(true, function (data) {
                            $("#dialog-acl-form").dialog("close");
                            //loadAclModuleList(lastClickDeptId);
                        }, function (data) {
                            showMessage("新增权限", data.msg, false);
                        })
                    },
                    "取消": function () {
                        $("#dialog-acl-form").dialog("close");
                    }
                }
            });
        });

        function updateAcl(isCreate, successCallBack, failCallback) {
            $.ajax({
                url: isCreate ? "/sys/acl/save.json" : "/sys/acl/update.json",
                data: $("#aclForm").serializeArray(),
                type: "POST",
                success: function(result) {
                    if (result.ret) {
                        loadAclList(lastClickAclModuleId);
                        if (successCallBack) {
                            successCallBack(result);
                        }
                    } else {
                        if (failCallback) {
                            failCallback(result);
                        }
                    }
                }
            })
        }
    });
</script>
</body>
</html>
