# permission
基于RBAC的权限框架
# 用户列表
- 新建userController
- 新建用户参数对象UserParam并添加约束
- 新建service类，相同的方法保持相同的格式,编写用户的保存和更新
- 系统的登录和注销
- 封装分页插件(pageQuery, PageResult, page.jsp)
- 编写分页后台
- 用户分页信息展示
- ThreadLocal, LoginFilter实现登录请求的拦截（获取登录用户信息）
- IPUtil, MailUtil 引入

# 权限模块
- 创建SysAclModuleController 和 SysAclController
- 创建权限模块参数对象 AclModuleParam
- 参考部门编写权限模块的service(save,update)
- 编写权限模块树的实现接口
- 编写权限模块的页面（新增， 编辑）
- 编写权限模块交互功能实现