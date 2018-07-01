import { addJudge } from '@/api/judges'
import { getToken, setToken, removeToken } from '@/utils/auth'

const judges = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: []
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ROLES: (state, roles) => {
      state.roles = roles
    }
  },

  actions: {
    //添加评委---孙云栋
    addJudge({ commit },judgeInfo) {
      /*const loginName = judgeInfo.loginName.trim();
      const userPriority = 1;
      const role = 2;*/
      return new Promise((resolve, reject) => {
        addJudge(judgeInfo).then(response => {
          const data = response.data;
          resolve();
        }).catch(error => {
          reject(error)
        })
      })
    }
  }
}

export default judges
