const logininfo = {
  admin: {
    password: '123456',
    token: 'admin-token'
  },
  editor: {
    password: '123456',
    token: 'editor-token'
  }
}

const users = {
  'admin-token': {
    roles: ['admin'],
    introduction: 'I am a super administrator',
    avatar:
      'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Super Admin'
  },
  'editor-token': {
    roles: ['editor'],
    introduction: 'I am an editor',
    avatar:
      'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',
    name: 'Normal Editor'
  }
}

export default [
  // user login
  {
    url: '/user/login',
    type: 'post',
    response: config => {
      const { username, password } = config.body
      const info = logininfo[username]

      if (!info) {
        return {
          code: 60204,
          message: 'Account is not exists.'
        }
      }

      if (info.password !== password) {
        return {
          code: 60203,
          message: 'password is incorrect.'
        }
      }

      // mock error
      if (!info.token) {
        return {
          code: 60204,
          message: 'Account and password are incorrect.'
        }
      }

      return {
        success: true,
        code: '0',
        msg: 'success',
        data: {
          token: info.token
        }
      }
    }
  },

  // get user info
  {
    url: '/user/getInfo.*',
    type: 'get',
    response: config => {
      const { token } = config.query
      const info = users[token]

      // mock error
      if (!info) {
        return {
          code: 50008,
          message: 'Login failed, unable to get user details.'
        }
      }

      return {
        code: '0',
        data: info
      }
    }
  },

  // user logout
  {
    url: '/user/logout',
    type: 'post',
    response: _ => {
      return {
        code: '0',
        data: 'success'
      }
    }
  }
]
