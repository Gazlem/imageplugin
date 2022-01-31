# 网页截图

> 基于  [mirai-selenium-plugin](https://github.com/cssxsh/mirai-selenium-plugin)的 网页截图插件

[![Release](https://img.shields.io/github/v/release/liuqianpan2008/imageplugin)]

**使用前准备**

* [User Manual](https://github.com/mamoe/mirai/blob/dev/docs/UserManual.md)
* [Permission Command](https://github.com/mamoe/mirai/blob/dev/mirai-console/docs/BuiltInCommands.md#permissioncommand)
* [Chat Command](https://github.com/project-mirai/chat-command)

请确保以上文档熟知阅读。

请确保mcl版本在[2.10.0-RC2](https://github.com/mamoe/mirai/releases/tag/v2.10.0-RC2)及其以上。

请确保前置插件的安装以及版本在[Release v2.0.5 ](https://github.com/cssxsh/mirai-selenium-plugin/releases/tag/v2.0.5)及以上

# 指令

### (/) 截图 <url>

描述：会对当前网页进行一次长截图（部分暂响应式布局网站不支持）

权限点：`org.imageplugin:command.截图`

效果图:

![](https://i.bmp.ovh/imgs/2022/01/a65d835b5f9eff90.png)

![](https://s3.bmp.ovh/imgs/2022/01/3abe17f7025fc366.png)

### (/) 站长工具

> 数据来源 chinaz.com

权限点:`org.imageplugin:command.站长工具`

- (/) 站长工具 ping <url>

描述：查询当前网站ping值

效果图：

![](https://s3.bmp.ovh/imgs/2022/01/773fe38e12469ada.png)

![](https://s3.bmp.ovh/imgs/2022/01/5906befad6671c62.png)

- (/) 站长工具 备案 <url>

> 无备案状态后台会进行报错，前台不会输出报错

描述：查询当前网站备案情况（存在缓存）

效果图：

![](https://i.bmp.ovh/imgs/2022/01/435e84464d41755f.png)

![](https://i.bmp.ovh/imgs/2022/01/df7a93e3f7769c8e.png)

- (/) 站长工具 ECO <url>

描述：查询当前网站综合收录情况

效果图：

![img](file:///C:\Users\liuqi\AppData\Roaming\Tencent\Users\2180323481\QQ\WinTemp\RichOle\%`6TR{%JK1]}O}3Z5}BZC09.png)

![](https://i.bmp.ovh/imgs/2022/01/9b2ff267ecb7e5f1.png)

(/) 站长工具 ip <url>

描述：查询当前网站IP地址归宿地

效果图：

![](https://i.bmp.ovh/imgs/2022/01/15c1d04a61984375.png)

![](https://i.bmp.ovh/imgs/2022/01/0af9b334cdfeead9.png)

# 安装

1. 运行 [Mirai Console](https://github.com/mamoe/mirai-console) 生成`plugins`文件夹
2. 从[Releases](https://github.com/liuqianpan2008/imageplugin/releases)下载`jar`并将其放入`plugins`文件夹中

# 夹带私货



QQ群：877894787

插件开发教程：https://blog.fldxz.top/archives/category/mirai
