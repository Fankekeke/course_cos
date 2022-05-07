<template>
  <div :class="[multipage === true ? 'multi-page':'single-page', 'not-menu-page', 'home-page']">
    <a-row :gutter="8" v-if="newsList.length > 0">
      <a-col :span="23">
        <a-alert
          banner
          :message="newsContent"
          type="info"
        />
      </a-col>
      <a-col :span="1">
        <a-button type="primary" style="margin-top: 2px" @click="newsNext">下一页</a-button>
      </a-col>
    </a-row>
    <a-row :gutter="8" class="head-info" style="margin-top: 8px">
      <a-card class="head-info-card">
        <a-col :span="12">
          <div class="head-info-avatar">
            <img alt="头像" :src="avatar">
          </div>
          <div class="head-info-count">
            <div class="head-info-welcome">
              {{welcomeMessage}}
            </div>
            <div class="head-info-desc">
              <p>{{user.roleName ? user.roleName : '暂无角色'}}</p>
            </div>
            <div class="head-info-time">上次登录时间：{{user.lastLoginTime ? user.lastLoginTime : '第一次访问系统'}}</div>
          </div>
        </a-col>
        <a-col :span="12">
          <div>
            <a-row class="more-info">
              <a-col :span="4"></a-col>
              <a-col :span="4"></a-col>
              <a-col :span="4" @click="studentExport">
                <head-info title="学生数量" :content="studentNum" :center="false" :bordered="false"/>
              </a-col>
              <a-col :span="4" @click="teacherExport">
                <head-info title="教员数量" :content="teacherNum" :center="false" :bordered="false"/>
              </a-col>
              <a-col :span="4">
                <head-info title="课程总量" :content="courseNum" :center="false" />
              </a-col>
              <a-col :span="4">
                <head-info title="缺 席" :content="courseNotTeacherNum" :center="false" />
              </a-col>
            </a-row>
          </div>
        </a-col>
      </a-card>
    </a-row>
    <a-row :gutter="10" class="count-info">
      <a-col :span="16" class="visit-count-wrapper">
        <a-row :gutter="10">
          <a-col :span="14">
            <a-card class="visit-count">
              <apexchart ref="count" type=bar height=300 :options="chartOptions" :series="series" />
            </a-card>
          </a-col>
          <a-col :span="10">
            <a-card class="visit-count">
              <a-skeleton active v-if="loading" />
              <apexchart v-if="!loading" ref="studentRate" height=309 type="donut" :options="studentOptions" :series="studentSeries"></apexchart>
            </a-card>
          </a-col>
        </a-row>
        <a-card class="visit-count" style="margin-top: 10px">
          <a-skeleton active v-if="loading" />
          <apexchart v-if="!loading" ref="courseRate" height="300" type="bar" :options="courseChartOptions" :series="courseSeries"></apexchart>
        </a-card>
      </a-col>
      <a-col :span="8" class="project-wrapper">
        <a-card hoverable :loading="loading" title="公告信息">
          <div>
            <a-list item-layout="vertical" :pagination="pagination" :data-source="bulletinList">
              <a-list-item slot="renderItem" key="item.title" slot-scope="item, index">
                <template slot="actions">
              <span key="message">
                <a-icon type="message" style="margin-right: 8px" />
                {{ item.date }}
              </span>
                </template>
                <a-list-item-meta :description="item.content" style="font-size: 14px">
                  <a slot="title" :href="item.href">{{ item.title }}</a>
                </a-list-item-meta>
              </a-list-item>
            </a-list>
          </div>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>
<script>
import HeadInfo from '@/views/common/HeadInfo'
import {mapState} from 'vuex'
import moment from 'moment'
import { newSpread, fixedForm, floatForm, floatReset, saveExcel } from '@/utils/spreadJS'
moment.locale('zh-cn')

export default {
  name: 'HomePage',
  components: {HeadInfo},
  data () {
    return {
      loading: false,
      pagination: {
        onChange: page => {
          console.log(page)
        },
        pageSize: 3
      },
      newsPage: 0,
      newsContent: '',
      newsList: [],
      bulletinList: [],
      courseNum: 0,
      teacherNum: 0,
      studentNum: 0,
      courseNotTeacherNum: 0,
      studentSeries: [],
      studentOptions: {
        labels: [],
        chart: {
          type: 'donut'
        },
        responsive: [{
          breakpoint: 480,
          options: {
            chart: {
              width: 200
            },
            legend: {
              position: 'bottom'
            }
          }
        }]
      },
      courseChartOptions: {
        xaxis: {
          categories: []
        }
      },
      courseSeries: [
        {
          name: '预约人数',
          data: []
        }
      ],
      series: [],
      chartOptions: {
        chart: {
          toolbar: {
            show: false
          }
        },
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '35%'
          }
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 2,
          colors: ['transparent']
        },
        xaxis: {
          categories: []
        },
        fill: {
          opacity: 1

        }
      },
      todayIp: '',
      todayVisitCount: '',
      totalVisitCount: '',
      userRole: '',
      userDept: '',
      lastLoginTime: '',
      welcomeMessage: ''
    }
  },
  computed: {
    ...mapState({
      multipage: state => state.setting.multipage,
      user: state => state.account.user
    }),
    avatar () {
      return `static/avatar/${this.user.avatar}`
    }
  },
  methods: {
    studentExport () {
      this.$message.loading('正在生成', 0)
      this.$get('/cos/student-info/list').then((r) => {
        let newData = []
        r.data.data.forEach((item, index) => {
          newData.push([(index + 1).toFixed(0), item.code, item.name, item.studentId, item.discipline, item.sex === 1 ? '男' : '女', item.adminClass, item.createTime])
        })
        let spread = newSpread('studentTable')
        spread = fixedForm(spread, 'studentTable', { title: '学生信息表' })
        spread = floatForm(spread, 'studentTable', newData)
        saveExcel(spread, '学生统计表.xlsx')
        floatReset(spread, 'studentTable', newData.length)
        this.$message.destroy()
      })
    },
    teacherExport () {
      this.$message.loading('正在生成', 0)
      this.$get('/cos/teacher-info/list').then((r) => {
        let newData = []
        r.data.data.forEach((item, index) => {
          newData.push([(index + 1).toFixed(0), item.code, item.name, item.jobNumber, item.discipline, item.sex === 1 ? '男' : '女', item.createTime])
        })
        let spread = newSpread('teacherTable')
        spread = fixedForm(spread, 'teacherTable', { title: '教员信息表' })
        spread = floatForm(spread, 'teacherTable', newData)
        saveExcel(spread, '教员统计表.xlsx')
        floatReset(spread, 'teacherTable', newData.length)
        this.$message.destroy()
      })
    },
    newsNext () {
      if (this.newsPage + 1 === this.newsList.length) {
        this.newsPage = 0
      } else {
        this.newsPage += 1
      }
      this.newsContent = `《${this.newsList[this.newsPage].title}》 ${this.newsList[this.newsPage].content}`
    },
    homeData () {
      this.loading = true
      this.$get('/cos/student-info/home', {userId: this.user.userId, userType: this.user.roleId}).then((r) => {
        this.newsList = r.data.news
        if (this.newsList.length !== 0) {
          this.newsContent = `《${this.newsList[0].title}》 ${this.newsList[0].content}`
        }
        this.bulletinList = r.data.bulletin
        this.courseNum = r.data.courseNum
        this.teacherNum = r.data.teacherNum
        this.studentNum = r.data.studentNum
        this.courseNotTeacherNum = r.data.courseNotTeacherNum
        let studentSeries = []
        let studentLabels = []
        r.data.studentDisciplineRate.forEach(item => {
          studentLabels.push(item.discipline)
          studentSeries.push(item.number)
        })
        let courseSeries = []
        let courseLabels = []
        r.data.courseRate.forEach(item => {
          courseLabels.push(item.courseName)
          courseSeries.push(item.number)
        })
        this.courseChartOptions.xaxis.categories = courseLabels
        this.courseSeries[0].data = courseSeries
        setTimeout(() => {
          this.loading = false
          setTimeout(() => {
            this.$refs.studentRate.updateOptions({
              title: {
                text: '学生专业分布统计',
                align: 'left'
              }
            }, true, true)
          }, 200)
          this.studentSeries = studentSeries
          this.studentOptions.labels = studentLabels
        }, 500)
      })
    },
    welcome () {
      const date = new Date()
      const hour = date.getHours()
      let time = hour < 6 ? '早上好' : (hour <= 11 ? '上午好' : (hour <= 13 ? '中午好' : (hour <= 18 ? '下午好' : '晚上好')))
      return `${time}，${this.user.username}`
    }
  },
  mounted () {
    this.homeData()
    this.welcomeMessage = this.welcome()
    this.$get(`index/${this.user.username}`).then((r) => {
      let data = r.data.data
      this.todayIp = data.todayIp
      this.todayVisitCount = data.todayVisitCount
      this.totalVisitCount = data.totalVisitCount
      let sevenVisitCount = []
      let dateArr = []
      for (let i = 6; i >= 0; i--) {
        let time = moment().subtract(i, 'days').format('MM-DD')
        let contain = false
        for (let o of data.lastSevenVisitCount) {
          if (o.days === time) {
            contain = true
            sevenVisitCount.push(o.count)
          }
        }
        if (!contain) {
          sevenVisitCount.push(0)
        }
        dateArr.push(time)
      }
      let sevenUserVistCount = []
      for (let i = 6; i >= 0; i--) {
        let time = moment().subtract(i, 'days').format('MM-DD')
        let contain = false
        for (let o of data.lastSevenUserVisitCount) {
          if (o.days === time) {
            contain = true
            sevenUserVistCount.push(o.count)
          }
        }
        if (!contain) {
          sevenUserVistCount.push(0)
        }
      }
      this.$refs.count.updateSeries([
        {
          name: '您',
          data: sevenUserVistCount
        },
        {
          name: '总数',
          data: sevenVisitCount
        }
      ], true)
      this.$refs.count.updateOptions({
        xaxis: {
          categories: dateArr
        },
        title: {
          text: '近七日系统访问记录',
          align: 'left'
        }
      }, true, true)
    }).catch((r) => {
      console.error(r)
      this.$message.error('获取首页信息失败')
    })
  }
}
</script>
<style lang="less">
  .home-page {
    .head-info {
      margin-bottom: .5rem;
      .head-info-card {
        padding: .5rem;
        border-color: #f1f1f1;
        .head-info-avatar {
          display: inline-block;
          float: left;
          margin-right: 1rem;
          img {
            width: 5rem;
            border-radius: 2px;
          }
        }
        .head-info-count {
          display: inline-block;
          float: left;
          .head-info-welcome {
            font-size: 1.05rem;
            margin-bottom: .1rem;
          }
          .head-info-desc {
            color: rgba(0, 0, 0, 0.45);
            font-size: .8rem;
            padding: .2rem 0;
            p {
              margin-bottom: 0;
            }
          }
          .head-info-time {
            color: rgba(0, 0, 0, 0.45);
            font-size: .8rem;
            padding: .2rem 0;
          }
        }
      }
    }
    .count-info {
      .visit-count-wrapper {
        padding-left: 0 !important;
        .visit-count {
          padding: .5rem;
          border-color: #f1f1f1;
          .ant-card-body {
            padding: .5rem 1rem !important;
          }
        }
      }
      .project-wrapper {
        padding-right: 0 !important;
        .project-card {
          border: none !important;
          .ant-card-head {
            border-left: 1px solid #f1f1f1 !important;
            border-top: 1px solid #f1f1f1 !important;
            border-right: 1px solid #f1f1f1 !important;
          }
          .ant-card-body {
            padding: 0 !important;
            table {
              width: 100%;
              td {
                width: 50%;
                border: 1px solid #f1f1f1;
                padding: .6rem;
                .project-avatar-wrapper {
                  display:inline-block;
                  float:left;
                  margin-right:.7rem;
                  .project-avatar {
                    color: #42b983;
                    background-color: #d6f8b8;
                  }
                }
              }
            }
          }
          .project-detail {
            display:inline-block;
            float:left;
            text-align:left;
            width: 78%;
            .project-name {
              font-size:.9rem;
              margin-top:-2px;
              font-weight:600;
            }
            .project-desc {
              color:rgba(0, 0, 0, 0.45);
              p {
                margin-bottom:0;
                font-size:.6rem;
                white-space:normal;
              }
            }
          }
        }
      }
    }
  }
</style>
