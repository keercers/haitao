import axios from './../util/axios.js'

export const enumTypeList = (name) => axios.setAxiosGetPromise('sys/enum/listEnumValues', name)
