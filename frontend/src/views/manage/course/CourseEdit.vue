<template>
  <a-modal v-model="show" title="修改课程" @cancel="onClose" :width="800">
    <template slot="footer">
      <a-button key="back" @click="onClose">
        取消
      </a-button>
      <a-button key="submit" type="primary" :loading="loading" @click="handleSubmit">
        修改
      </a-button>
    </template>
    <a-form :form="form" layout="vertical">
      <a-row :gutter="20">
        <a-col :span="12">
          <a-form-item label='课程性质' v-bind="formItemLayout">
            <a-input v-decorator="[
            'courseNature',
            { rules: [{ required: true, message: '请输入课程性质!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='课程名称' v-bind="formItemLayout">
            <a-input v-decorator="[
            'courseName',
            { rules: [{ required: true, message: '请输入课程名称!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='开始时间' v-bind="formItemLayout">
            <a-date-picker style="width: 100%" v-decorator="[
            'startTime',
            { rules: [{ required: true, message: '请输入开始时间!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='结束时间' v-bind="formItemLayout">
            <a-date-picker style="width: 100%" v-decorator="[
            'endTime',
            { rules: [{ required: true, message: '请输入结束时间!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item label='绩点' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
              'gpa', { rules: [{ required: true, message: '请输入绩点!' }] }
              ]"
                            :formatter="value => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
            />
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item label='学分' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
              'credit', { rules: [{ required: true, message: '请输入学分!' }] }
              ]"
                            :formatter="value => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
            />
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item label='节课数量' v-bind="formItemLayout">
            <a-input-number style="width: 100%" v-decorator="[
              'lessonsNum', { rules: [{ required: true, message: '请输入节课数量!' }] }
              ]"
                            :formatter="value => `${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
            />
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item label='所在教室' v-bind="formItemLayout">
            <a-input v-decorator="[
            'classroom',
            { rules: [{ required: true, message: '请输入所在教室!' }] }
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12">
          <a-form-item label='选择指导教师' v-bind="formItemLayout">
            <a-select v-decorator="['teacherId', { rules: [{ required: true, message: '请输入所在教室!' }] }]">
              <a-select-option :value="item.id" v-for="(item, index) in teacherList" :key="index">{{ item.name }}</a-select-option>
            </a-select>
          </a-form-item>
        </a-col>
        <a-col :span="24">
          <a-form-item label='备注' v-bind="formItemLayout">
            <a-textarea :rows="4" v-decorator="[
            'content'
            ]"/>
          </a-form-item>
        </a-col>
        <a-col :span="12"></a-col>
      </a-row>
    </a-form>
  </a-modal>
</template>

<script>
import {mapState} from 'vuex'
import moment from 'moment'
const formItemLayout = {
  labelCol: { span: 24 },
  wrapperCol: { span: 24 }
}
export default {
  name: 'CourseEdit',
  props: {
    courseEditVisiable: {
      default: false
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    show: {
      get: function () {
        return this.courseEditVisiable
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      teacherList: [],
      rowId: null,
      formItemLayout,
      form: this.$form.createForm(this),
      loading: false
    }
  },
  mounted () {
    this.getTeacherList()
  },
  methods: {
    getTeacherList () {
      this.$get('/cos/teacher-info/list').then((r) => {
        this.teacherList = r.data.data
      })
    },
    setFormValues ({...course}) {
      this.rowId = course.id
      let fields = ['courseNature', 'courseName', 'startTime', 'endTime', 'gpa', 'credit', 'lessonsNum', 'content', 'classroom', 'teacherId']
      let obj = {}
      Object.keys(course).forEach((key) => {
        if (fields.indexOf(key) !== -1) {
          this.form.getFieldDecorator(key)
          obj[key] = course[key]
        }
        if (key === 'startTime') {
          obj[key] = moment(course[key])
        }
        if (key === 'endTime') {
          obj[key] = moment(course[key])
        }
      })
      this.form.setFieldsValue(obj)
    },
    reset () {
      this.loading = false
      this.form.resetFields()
    },
    onClose () {
      this.reset()
      this.$emit('close')
    },
    handleCancel () {
      this.previewVisible = false
    },
    handleSubmit () {
      this.form.validateFields((err, values) => {
        values.id = this.rowId
        if (values.startTime) {
          values.startTime = moment(values.startTime).format('YYYY-MM-DD')
        }
        if (values.endTime) {
          values.endTime = moment(values.endTime).format('YYYY-MM-DD')
        }
        if (!err) {
          this.loading = true
          this.$put('/cos/course-info', {
            ...values
          }).then((r) => {
            this.reset()
            this.$emit('success')
          }).catch(() => {
            this.loading = false
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
