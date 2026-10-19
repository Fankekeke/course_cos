### 基于SpringBoot + Vue的学生选课系统

教务管理、抢课辅助系统、排课算法、培养方案管理、教学评测、选课冲突检测、退补选流程

##### 账号体系与公告门户
###### 账号注册与登录： 支持教员与学生自主注册，并提供多角色权限隔离的登录入口，确保校园数据的访问安全。

###### 公告与动态管理： 实时发布选课指南、教务通知及校园动态，通过信息流同步确保师生及时获取关键教学资讯。

##### 统计图表与数据看板
###### 数据统计中心： 全量汇总学生、教员及课程规模，通过缺席统计分析出勤状况，为教学质量评估提供数据支撑。

###### 可视化分析图表： 运用专业分布、访问记录及预约人数统计图，直观展现选课热度与用户行为轨迹，辅助教务决策。

##### 管理员端：全全局调度与管控
###### 课程与选课管理： 统筹全校课程资源配置，监管选课流程节点，动态调整课容量，确保教学资源分配公平高效。

###### 用户与封禁管理： 维护学生与教员档案，管控系统账户状态，通过账号封禁功能处理违规行为，净化校园网络环境。

##### 教员端：教学执行与学生互动
###### 信息与任务管理： 维护个人教学背景并管理教学进度，通过任务系统接收排课指令，实现教学工作的规范化。

###### 选课管理与名单查看： 自主决定课程的开启或停领，实时查看选课学生列表，全面掌握班级学生构成与到课情况。

##### 学生端：自主选课与学业规划
###### 课程预约管理： 学生根据培养方案在线预约心仪课程，实时锁定学位，通过移动端交互提升选课流程的便捷性。

###### 学生课程/信息管理： 自主管理已选课程清单并维护个人学籍资料，实时查看课表动态，实现个人学业进度的自我驱动。

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
| ![](./assets/image%20(1).png) | ![](./assets/image%20(2).png) |
| ![](./assets/image%20(3).png) | ![](./assets/image%20(4).png) |
| ![](./assets/image%20(5).png) | ![](./assets/image%20(6).png) |
| ![](./assets/image%20(7).png) | ![](./assets/image%20(8).png) |

#### 演示视频

[项目视频：基于SpringBoot的学生选课系统](https://www.bilibili.com/video/BV1ui4y1Q7oS/)

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

<div align=center><img align="center" src="./assets/product_word.png" alt="fankekeke" /></div>

#### 其它资源
[2026年-答辩顺利通过-客户评价🀄](https://berserker287.github.io/2026/06/29/2026%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)

#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)

<div align="center"><img align="center" src="./assets/chat_qr_code.png" alt="fankekeke" width=250 /></div>

<p><img align="center" src="./assets/wechat_group.png" alt="fankekeke" /></p>

**感谢查看**

