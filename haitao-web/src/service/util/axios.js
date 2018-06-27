import axios from 'axios'

axios.defaults.timeout = window.config.axiosTimeOutDuration
axios.defaults.withCredentials = true
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8'
axios.defaults.headers['If-Modified-Since'] = '0'
axios.defaults.headers['Cache-Control'] = 'no-cache,no-store,must-revalidate,max-age=-1,private'
axios.defaults.baseURL = window.config.axiosBaseURL
axios.defaults.fileUploadUrl = window.config.axiosFileUploadUrl
axios.defaults.fileLoadUrl = window.config.axiosFileDownloadUrl
axios.defaults.headers.common.token = localStorage.getItem('token')

export default {
  setAxiosGetPromise: (url, params = {}) => {
    return axios.get(url, {params: params}).then(response => {
      return response
    }).catch(err => {
      throw err
    })
  },
  setAxiosPostPromise: (url, data) => {
    return axios.post(url, data).then(response => {
      return response
    }).catch(err => {
      throw err
    })
  },
  setReportAxiosGetPromise: (url, params = {}) => {
    return axios.get(url, {params: params, timeout: 50000}).then(response => {
      return response
    }).catch(err => {
      throw err
    })
  }
}
