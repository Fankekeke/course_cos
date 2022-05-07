<template>
  <a-modal v-model="show" title="教员信息详情" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px" v-if="teacherData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>编 号：</b>
          {{ teacherData.code }}
        </a-col>
        <a-col :span="8"><b>姓 名：</b>
          {{ teacherData.name }}
        </a-col>
        <a-col :span="8"><b>性 别：</b>
          <span v-if="teacherData.sex == 1">男</span>
          <span v-if="teacherData.sex == 2">女</span>
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>账户状态：</b>
          <a-tag color="red" v-if="teacherData.status == 0">
            锁定
          </a-tag>
          <a-tag color="green" v-if="teacherData.status == 1">
            有效
          </a-tag>
        </a-col>
        <a-col :span="8"><b>操作账户：</b>{{ teacherData.username !== null ? teacherData.username : '- -' }}</a-col>
        <a-col :span="8"><b>最近时间：</b>{{ teacherData.lastTime !== null ? teacherData.lastTime : '- -' }}</a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">代课信息</span></a-col>
        <a-col :span="24">
          <div style="background:#ECECEC; padding:30px;font-size: 13px;">
            <a-row :gutter="20">
              <a-col :span="24" v-for="(item, index) in courseList" :key="index" style="margin-bottom: 15px">
                <a-card :bordered="false">
                  <a slot="extra" style="font-size: 13px">{{ item.startTime }} - {{ item.endTime }}</a>
                  <span slot="title" style="font-size: 13px">
                    <a-badge status="processing"/>
                    {{ item.courseName }}
                  </span>
                  <div style="font-size: 13px">
                    <a-row :gutter="8">
                      <a-col :span="6"><b>课程性质：</b>{{ item.courseNature }}</a-col>
                      <a-col :span="6"><b>所学课时：</b>{{ item.lessonsNum }}节</a-col>
                      <a-col :span="6"><b>学 分：</b>{{ item.credit }}分</a-col>
                      <a-col :span="6"><b>绩 点：</b>{{ item.gpa }}点</a-col>
                      <br/>
                      <br/>
                      <a-col :span="24">
                        <a-tag>当前预约学生：<span style="font-size: 15px;color: red">{{ item.studentNum }}</span>人</a-tag>
                      </a-col>
                    </a-row>
                  </div>
                </a-card>
              </a-col>
              <a-col :span="24" v-if="courseList.length === 0">
                <a-alert
                  message="提示"
                  description="该教员暂时未接手课程"
                  type="warning"
                />
              </a-col>
            </a-row>
          </div>
        </a-col>
      </a-row>
      <br/>
      <br/>
    </div>
  </a-modal>
</template>

<script>
export default {
  name: 'TeacherView',
  props: {
    teacherShow: {
      type: Boolean,
      default: false
    },
    teacherData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.teacherShow
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      loading: false,
      courseList: []
    }
  },
  watch: {
    teacherShow: function (value) {
      if (value) {
        this.dataInit()
      }
    }
  },
  methods: {
    dataInit () {
      this.$get('/cos/teacher-info/selection', {id: this.teacherData.id}).then((r) => {
        this.courseList = r.data.data
      })
    },
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
