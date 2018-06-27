import axios from '../util/axios'

export const queryListRoleMoudle = (options) => axios.setAxiosGetPromise('sys/rolemoudle/ListRoleMoudleByRoleId', options)

export const saveRoleMoudel = (options) => axios.setAxiosGetPromise('sys/rolemoudle/save', options)
