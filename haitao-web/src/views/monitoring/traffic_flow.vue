<template>
    <!-- 交通量统计 -->
    <div style="padding: 20px">
        <el-row>
          <div style="float: right">
              <span style="margin-right: 15px">统计时间</span>
              <el-date-picker v-model="date1" type="month" placeholder="选择日期">
              </el-date-picker>
              <span>~</span>
              <el-date-picker v-model="date2" type="month" placeholder="选择日期">
              </el-date-picker>
              <el-button type="primary" @click="getData" icon="search">查询</el-button>
              <el-button type="primary"
                          :plain="true"
                          @click="dialogVisible1 = true">导出
              </el-button>
              <el-button type="primary"
                          :plain="true"
                          @click="printData">打印
              </el-button>
          </div>
          <div class="clear"></div>
        </el-row>
        <el-row>
            <hr style="color: #c7c5c5;"/>
        </el-row>
        <div :style="{height: tableHeightDynamic + 'px'}" style="margin-top: 10px; overflow: auto">
            <div v-if="!loading" id="report" v-html="report"></div>
            <div v-loading="loading" style="position: absolute; top: 400px; left: 55%"></div>
        </div>
        <el-dialog :visible.sync="dialogVisible1" :modal-append-to-body="false" ref="infoDialog">
            <template>
                <el-form :inline="true">
                    <el-form-item label="导出格式" style="margin-left: 20%">
                        <el-select style="width: 100%" v-model="exportFormat">
                            <el-option value="PDF">PDF</el-option>
                            <el-option value="Excel">Excel</el-option>
                            <el-option value="Word">Word</el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" style="height: 40px"
                                   @click="exportData">导出
                        </el-button>
                    </el-form-item>
                </el-form>
            </template>
        </el-dialog>
    </div>
</template>

<script>
  /* eslint-disable */
  import {mapGetters} from 'vuex'
  import axios from 'axios'
  
  export default {
    name: 'traffic_flow',
    data () {
      return {
        tableHeightDynamic: document.body.clientHeight - config.topBarHeight - 120,
        reportName: 'TrafficFlowMaxAvgByMonRp',
        mpId: null,
        date1: null,
        date2: null,
        report: null,
        exportFormat: 'Word',
        printFormat: 'PDF',
        dialogVisible1: false,
        loading: true
      }
    },
    computed: mapGetters({
      reportData: 'reportData', // 报表数据
      reportUrl: 'reportUrl', // 报表导出url
      options: 'monitorPointList' // 全部观测点
    }),
    created () {
      this.date1 = this.getToday(0)
      this.date2 = this.getToday(1)
    },
    methods: {
      getData () {
        var sTime = (typeof this.date1 === 'object') ? this.formatDate(this.date1) : null
        var eTime = (typeof this.date2 === 'object') ? this.formatDate(this.date2) : null
        if (sTime > eTime) {
          this.$message({
            showClose: true,
            message: '结束时间不可小于开始时间！',
            type: 'warning'
          })
          return
        }
        if (sTime === null || eTime === null) {
          this.$message({
            showClose: true,
            message: '时间条件不可为空！',
            type: 'warning'
          })
          return
        }
        this.loading = true
        return this.$store.dispatch('getReportData', {
          reportName: this.reportName,
          startTime: sTime,
          endTime: eTime
        }).then(() => {
          setTimeout(() => {
            this.report = this.reportData
            this.loading = false
          }, 1000)
        }).catch((err) => {
          this.$message({
            message: '查询失败:' + err,
            type: 'error'
          })
        })
      },
      exportData () {
        this.dialogVisible1 = false
        var sTime = (typeof this.date1 === 'object') ? this.formatDate(this.date1) : null
        var eTime = (typeof this.date2 === 'object') ? this.formatDate(this.date2) : null
        if (sTime > eTime) {
          this.$message({
            showClose: true,
            message: '结束时间不可小于开始时间！',
            type: 'warning'
          })
          return
        }
        if (sTime === null || eTime === null) {
          this.$message({
            showClose: true,
            message: '时间条件不可为空！',
            type: 'warning'
          })
          return
        }
        window.location = axios.defaults.baseURL + 'monitoring/reportView/export?exportFormat=' + this.exportFormat + '&preview=' + 0 + '&reportName=' + this.reportName + '&startTime=' + sTime + '&endTime=' + eTime
      },
      printData () {
        var sTime = (typeof this.date1 === 'object') ? this.formatDate(this.date1) : null
        var eTime = (typeof this.date2 === 'object') ? this.formatDate(this.date2) : null
        if (sTime > eTime) {
          this.$message({
            showClose: true,
            message: '结束时间不可小于开始时间！',
            type: 'warning'
          })
          return
        }
        if (sTime === null || eTime === null) {
          this.$message({
            showClose: true,
            message: '时间条件不可为空！',
            type: 'warning'
          })
          return
        }
        let newWindow = window.open('about:blank')   // 打开新窗口
        newWindow.location = axios.defaults.baseURL + 'monitoring/reportView/export?exportFormat=' + this.printFormat + '&preview=' + 1 + '&reportName=' + this.reportName + '&startTime=' + sTime + '&endTime=' + eTime
        return true
      },
      formatDate (date) {
        var year = date.getFullYear()
        var month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
        var dateTime = year + '-' + month
        return dateTime
      },
      getToday (v) {
        var date = new Date()
        var year = date.getFullYear()
        var month = (date.getMonth() + 1) < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
        if (v === 0) {
          month = '01'
        }
        var dateTime = year + '-' + month
        return new Date(dateTime)
      }
    }
  }
</script>

<style scoped>
    #report {
        font-size: 14px;
    }
</style>
