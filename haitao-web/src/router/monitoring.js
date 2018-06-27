import monitoring from '../views/monitoring/monitoring.vue'
export default {
  path: '/monitoring',
  component: monitoring,
  children: [
    {path: '', component: resolve => require(['../views/monitoring/traffic_flow.vue'], resolve)},
    // { path: '/traffic_list', name: 'traffic_list', component: resolve => require(['../views/monitoring/traffic_list.vue'], resolve) },
    // { path: '/monitor_records', name: 'monitor_records', component: resolve => require(['../views/monitoring/monitor_records.vue'], resolve) },
    // { path: '/video_analysis_process', name: 'video_analysis_process', component: resolve => require(['../views/monitoring/video_analysis_process.vue'], resolve) },
    // { path: '/flow', name: 'flow', component: resolve => require(['../views/monitoring/flow.vue'], resolve) },
    // { path: '/tonnage', name: 'tonnage', component: resolve => require(['../views/monitoring/tonnage.vue'], resolve) },
    { path: '/traffic_flow', name: 'traffic_flow', component: resolve => require(['../views/monitoring/traffic_flow.vue'], resolve) }
    // { path: '/summary_day', name: 'summary_day', component: resolve => require(['../views/monitoring/summary_day.vue'], resolve) },
    // { path: '/summary_month', name: 'summary_month', component: resolve => require(['../views/monitoring/summary_month.vue'], resolve) },
    // { path: '/summary_empty', name: 'summary_empty', component: resolve => require(['../views/monitoring/summary_empty.vue'], resolve) },
    // { path: '/monitor_month_report', name: 'monitor_month_report', component: resolve => require(['../views/monitoring/monitor_month_report.vue'], resolve) },
    // { path: '/ship_outWidth_report', name: 'ship_outWidth_report', component: resolve => require(['../views/monitoring/ship_outWidth_report.vue'], resolve) },
    // { path: '/alarm_statistics', name: 'alarm_statistics', component: resolve => require(['../views/monitoring/alarm_statistics.vue'], resolve) }

  ]
}
