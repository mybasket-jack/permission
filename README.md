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

# 权限点开发
- 编写权限点的 (AclParam AclController AclService AclMapper)
- 下载font-awesome包，并引入（使用mvc:resource 加载fonts文件夹）

# 角色模块
- 新建角色控制类 RoleController RoleService
- 新建角色参数类 RoleParam
- 编写 save update service逻辑
- 编写RoleController
- 编写角色管理页面，新增和修改的交互

# 角色与权限
- 角色权限树结构接口开发
- role.jsp 引入zTree
- 权限树生成

# 角色与用户
- 使用 duallistbox 管理用户
- 注意 id 判空和重复判断

# 附加功能
- 部门删除
- 获取权限点分配的角色和用户接口实现
- 获取用户分配的权限和角色接口实现

# 权限拦截
- AclControllerFilter

# 权限缓存
- RedisPool
- Redis.xml Redis.properties

#权限操作记录
- SysLogController
- SearchLogDto SearchLogParam
- SysLogService

![用户管理](https://upload-images.jianshu.io/upload_images/3944205-89ac80243b7e2503.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![角色管理](https://upload-images.jianshu.io/upload_images/3944205-868b016f61fce729.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![权限管理](https://upload-images.jianshu.io/upload_images/3944205-8ba30e52c6dc207b.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![权限拦截](https://upload-images.jianshu.io/upload_images/3944205-d4b6997d1f740fe7.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![权限更新记录](https://upload-images.jianshu.io/upload_images/3944205-03ec07ffa29f5087.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

