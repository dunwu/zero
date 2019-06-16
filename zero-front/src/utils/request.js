/**
 * @file 封装支持 promise 的 http 请求工具
 * @author Zhang Peng
 * @see https://github.com/mzabriskie/axios
 * @see http://www.jianshu.com/p/df464b26ae58
 */
// import qs from 'qs'
import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

axios.defaults.withCredentials = true
const service = axios.create({
  baseURL:
    process.env.NODE_ENV === 'development'
      ? process.env.VUE_APP_BASE_API
      : 'http://localhost:8080', // url = base url + request url
  withCredentials: true, // send cookies when cross-domain requests
  timeout: 1000 * 60 * 3 // TODO 临时设置
})

// request interceptor
service.interceptors.request.use(
  config => {
    // do something before request is sent
    console.info('[request info]', config)
    if (store.getters.token) {
      // let each request carry token
      // ['X-Token'] is a custom headers key
      // please modify it according to the actual situation
      config.headers['X-Token'] = getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * If you want to get http information such as headers or status
   * Please return  response => response
   */

  /**
   * Determine the request status by custom code
   * Here is just an example
   * You can also judge the status by HTTP Status Code
   */
  response => {
    const res = response.data
    console.info('[response info]', response.data)
    // if the custom code is not 0, it is judged as an error.
    if (res.code !== '0') {
      Message({
        message: res.msg || 'Error',
        type: 'error',
        duration: 5 * 1000
      })

      // 50008: Illegal token; 50012: Other clients logged in; 50014: Token expired;
      if (
        res.code === '50008' ||
        res.code === '50012' ||
        res.code === '50014'
      ) {
        // to re-login
        MessageBox.confirm(
          'You have been logged out, you can cancel to stay on this page, or log in again',
          'Confirm logout',
          {
            confirmButtonText: 'Re-Login',
            cancelButtonText: 'Cancel',
            type: 'warning'
          }
        ).then(() => {
          store.dispatch('user/resetToken').then(() => {
            location.reload()
          })
        })
      }
      return Promise.reject(new Error(res.msg || 'Error'))
    } else {
      return res
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.msg,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service