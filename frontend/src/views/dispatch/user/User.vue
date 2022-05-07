<template>
  <a-row :gutter="8" style="width: 100%">
    <a-col :span="6">
      <div style="background:#ECECEC; padding:30px;margin-top: 30px">
        <a-card :bordered="false">
          <b style="font-size: 15px">我的信息</b>
        </a-card>
        <a-card :bordered="false">
          <a-form :form="form" layout="vertical">
            <a-row :gutter="20">
              <a-col :span="24">
                <a-form-item label='学生姓名' v-bind="formItemLayout">
                  <a-input v-decorator="[
                  'name',
                  { rules: [{ required: true, message: '请输入学生姓名!' }] }
                  ]"/>
                </a-form-item>
              </a-col>
              <a-col :span="24">
                <a-form-item label='学号' v-bind="formItemLayout">
                  <a-input v-decorator="[
                  'studentId',
                  { rules: [{ required: true, message: '请输入学号!' }] }
                  ]"/>
                </a-form-item>
              </a-col>
              <a-col :span="24">
                <a-form-item label='性别' v-bind="formItemLayout">
                  <a-select v-decorator="[
                    'sex',
                    { rules: [{ required: true, message: '请输入性别!' }] }
                    ]">
                    <a-select-option value="1">男</a-select-option>
                    <a-select-option value="2">女</a-select-option>
                  </a-select>
                </a-form-item>
              </a-col>
              <a-col :span="24">
                <a-form-item label='专业' v-bind="formItemLayout">
                  <a-input v-decorator="[
                  'discipline',
                  { rules: [{ required: true, message: '请输入专业!' }] }
                  ]"/>
                </a-form-item>
              </a-col>
              <a-col :span="24">
                <a-form-item label='班级' v-bind="formItemLayout">
                  <a-input v-decorator="[
                  'adminClass',
                  { rules: [{ required: true, message: '请输入班级!' }] }
                  ]"/>
                </a-form-item>
              </a-col>
              <a-col :span="24">
                <a-form-item>
                  <a-button
                    size="large"
                    type="primary"
                    htmlType="submit"
                    class="register-button"
                    style="width: 35%;float: right;margin-top: 50px"
                    :loading="loading"
                    @click.stop.prevent="handleSubmit"
                    :disabled="loading">立即修改
                  </a-button>
                </a-form-item>
              </a-col>
            </a-row>
          </a-form>
        </a-card>
      </div>
    </a-col>
    <a-col :span="18">
      <div style="background:#ECECEC; padding:30px;margin-top: 30px">
        <a-card :bordered="false">
          <a-spin :spinning="dataLoading">
            <a-calendar>
              <ul slot="dateCellRender" slot-scope="value" class="events">
                <li v-for="item in getListData(value)" :key="item.content">
                  <a-badge :status="item.type" :text="item.content" />
                </li>
              </ul>
            </a-calendar>
          </a-spin>
        </a-card>
      </div>
    </a-col>
  </a-row>
</template>

<script>
import {mapState} from 'vuex'
import moment from 'moment'
moment.locale('zh-cn')
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'User',
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    })
  },
  data () {
    return {
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false,
      courseInfo: [],
      dataLoading: false
    }
  },
  mounted () {
    this.dataInit()
  },
  methods: {
    isDuringDate (beginDateStr, endDateStr, curDataStr) {
      let curDate = new Date(curDataStr)
      let beginDate = new Date(beginDateStr)
      let endDate = new Date(endDateStr)
      if (curDate >= beginDate && curDate <= endDate) {
        return true
      }
      return false
    },
    getListData (value) {
      let listData = []
      this.courseInfo.forEach(item => {
        if (this.isDuringDate(item.startTime, item.endTime, (moment(value).format('YYYY-MM-DD')))) {
          listData.push({type: 'success', content: item.courseName})
        }
      })
      return listData || []
    },
    dataInit () {
      this.dataLoading = true
      this.$get('/cos/student-info/user/info', {userId: this.currentUser.userId}).then((r) => {
        this.rowId = r.data.studentInfo.id
        this.setFormValues(r.data.studentInfo)
        this.courseInfo = r.data.courseInfo
        this.dataLoading = false
      })
    },
    setFormValues ({...student}) {
      this.rowId = student.id
      let fields = ['name', 'studentId', 'sex', 'discipline', 'adminClass']
      let obj = {}
      Object.keys(student).forEach((key) => {
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = student[key]
        }
        if (key === 'sex' && student[key] !== null) {
          obj[key] = student[key].toString()
        }
      })
      this.form.setFieldsValue(obj)
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        this.$get('/cos/student-info/repeat', {studentId: values.studentId, tid: values.id}).then((r) => {
          if (r.data.data === 0) {
            if (!err) {
              this.loading = true
              this.$put('/cos/student-info', {
                ...values
              }).then((r) => {
                this.$message.success('修改信息成功')
                this.loading = false
              }).catch(() => {
                this.loading = false
              })
            }
          } else {
            this.$message.warning('该学号已经存在！')
          }
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
