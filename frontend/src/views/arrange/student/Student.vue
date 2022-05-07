<template>
  <div style="background:#ECECEC; padding:30px;margin-top: 30px;width: 100%">
    <a-row :gutter="10">
      <a-col :span="6" v-for="(item, index) in studentList" :key="index" style="margin-bottom: 10px">
        <a-card :bordered="false">
          <a slot="extra" style="font-size: 13px">{{ item.discipline }}</a>
          <span slot="title" style="font-size: 13px">
                    <a-badge status="processing"/>
                    {{ item.name }}
                  </span>
          <div style="font-size: 13px">
            <a-row :gutter="8">
              <a-col :span="8"><b>学 号：</b>{{ item.studentId }}</a-col>
              <a-col :span="8"><b>性 别：</b>
                <span v-if="item.sex == 1">男</span>
                <span v-if="item.sex == 2">女</span>
              </a-col>
              <a-col :span="8"><b>行政班：</b>
                {{ item.adminClass }}
              </a-col>
              <br/>
              <br/>
              <a-col :span="24"><b>创建时间：</b>
                {{ item.createTime }}
              </a-col>
            </a-row>
          </div>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
export default {
  name: 'Student',
  data () {
    return {
      studentList: []
    }
  },
  mounted () {
    this.getStudentList()
  },
  methods: {
    getStudentList () {
      this.$get('/cos/student-info/list').then((r) => {
        this.studentList = r.data.data
      })
    }
  }
}
</script>

<style scoped>

</style>
