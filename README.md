### 基于SpringBoot + Vue的学生选课系统 

#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios
报表：Spread.js

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok

教员，学生账号注册；管理员，教员，学生账号登陆，系统公告管理，系统动态管理，系统数据统计（学生统计，教员统计，课程统计， 缺席统计）

学生专业分布状况统计图，访问记录统计图，课程预约人数统计图，参加课程学生情况，学生选课信息，教员代课信息

管理员：课程管理，学生管理，教员管理，选课管理，系统用户管理，系统公告管理，系统动态管理，系统账户封禁

教员：教员信息管理，查看学生列表信息，教学任务管理，选课或停课，查看当前课程学生情况

学生：个人信息修改，课程预约，学生课程管理


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 后端启动方式

1.首先启动redis，进入redis目录终端。输入redis-server回车
2.导入sql文件，修改数据库与redis连接配置
3.idea中启动后端项目

#### 默认后台账户密码
[管理员]
admin
1234qwer

[教员]
test1
1234qwer

[学生]
fkkk
1234qwer
#### 项目截图

|  |  |
|---------------------|---------------------|
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1683871197230.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1683871129482.png) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1683871161577.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1683871113078.png) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1683871151715.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1683871089978.jpg) |
|![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1683871141327.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1683871207415.png) |


#### 演示视频

[项目视频：基于SpringBoot的学生选课系统](https://www.bilibili.com/video/BV1ui4y1Q7oS/)

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解`

#### 其它资源

[2024年答辩顺利通过](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年答辩顺利通过](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年答辩通过率100%](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)


#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

More info: [悲伤的橘子树](https://berserker287.github.io/)
