<template>
  <a-modal v-model="show" title="课程信息详情" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px" v-if="courseData !== null">
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>项目性质：</b>
          {{ courseData.courseNature }}
        </a-col>
        <a-col :span="8"><b>项目名称：</b>
          {{ courseData.courseName }}
        </a-col>
        <a-col :span="8"><b>教 员：</b>
          {{ courseData.teacherName }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>开始时间：</b>{{ courseData.startTime !== null ? courseData.startTime : '- -' }}</a-col>
        <a-col :span="8"><b>结束时间：</b>{{ courseData.endTime !== null ? courseData.endTime : '- -' }}</a-col>
        <a-col :span="8"><b>课 时：</b>{{ courseData.lessonsNum !== null ? courseData.lessonsNum : '- -' }}</a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>绩 点：</b>{{ courseData.gpa !== null ? courseData.gpa : '- -' }}点</a-col>
        <a-col :span="8"><b>学 分：</b>{{ courseData.credit !== null ? courseData.credit : '- -' }}分</a-col>
        <a-col :span="8"><b>教 室：</b>{{ courseData.classroom !== null ? courseData.classroom : '- -' }}</a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">备注信息</span></a-col>
        <a-col :span="24">{{ courseData.content !== null ? courseData.content : '暂无备注信息' }}</a-col>
      </a-row>
      <br/>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">参加学生 - <span style="color: red">{{ studentList.length }}</span>人</span></a-col>
        <a-col :span="24">
          <div style="background:#ECECEC; padding:30px;font-size: 13px;">
            <a-row :gutter="20">
              <a-col :span="24" v-if="courseData.studentNum < 3">
                <a-alert
                  message="提示"
                  :description="'该课程预约情况 - ' + studentList.length + ' / 3人'"
                  type="success"
                />
              </a-col>
              <a-col :span="24" v-if="courseData.studentNum >= 3">
                <a-alert
                  message="提示"
                  description="该课程已预约满！"
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
  name: 'courseView',
  props: {
    courseShow: {
      type: Boolean,
      default: false
    },
    courseData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.courseShow
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      loading: false,
      studentList: []
    }
  },
  watch: {
    courseShow: function (value) {
      if (value) {
        this.dataInit()
      }
    }
  },
  methods: {
    dataInit () {
      this.$get('/cos/course-info/studentList', {id: this.courseData.id}).then((r) => {
        this.studentList = r.data.data
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
