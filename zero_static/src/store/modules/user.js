import { getInfo, login, logout } from '@/api/user';
import { getToken, removeToken, setToken } from '@/utils/auth';
import { resetRouter } from '@/router';

const getDefaultState = () => {
    return {
        token: getToken(),
        name: '',
        avatar: '',
        nick: '',
        platCode: '',
        email: '',
        phone: '',
        introduction: '',
        roles: [],
        privs: []
    };
};

const state = getDefaultState();

const mutations = {
    RESET_STATE: state => {
        Object.assign(state, getDefaultState());
    },
    SET_TOKEN: (state, token) => {
        state.token = token;
    },
    SET_NAME: (state, name) => {
        state.name = name;
    },
    SET_AVATAR: (state, avatar) => {
        state.avatar = avatar;
    },
    SET_NICK: (state, nick) => {
        state.nick = nick;
    },
    SET_PLAT_CODE: (state, platCode) => {
        state.platCode = platCode;
    },
    SET_EMAIL: (state, email) => {
        state.email = email;
    },
    SET_PHONE: (state, phone) => {
        state.phone = phone;
    },
    SET_INTRODUCTION: (state, introduction) => {
        state.introduction = introduction;
    },
    SET_ROLES: (state, roles) => {
        state.roles = roles;
    },
    SET_PRIVS: (state, privs) => {
        state.privs = privs;
    }
};

const actions = {
    // user login
    login({ commit }, userInfo) {
        const { username, password, platCode } = userInfo;
        return new Promise((resolve, reject) => {
            login({ username: username.trim(), password: password, platCode: platCode })
                .then(response => {
                    const { data } = response;
                    commit('SET_TOKEN', data);
                    setToken(data);
                    resolve();
                })
                .catch(error => {
                    reject(error);
                });
        });
    },

    // get user info
    getInfo({ commit }) {
        return new Promise((resolve, reject) => {
            getInfo()
                .then(response => {
                    const { data } = response;
                    if (!data) {
                        return reject('Verification failed, please Login again.');
                    }

                    const { emy, nick, avatar, platCode, email, phone, sysRoleList, sysPrivilegeList } = data;

                    commit('SET_NAME', emy);
                    commit('SET_NICK', nick);
                    commit('SET_AVATAR', avatar);
                    commit('SET_PLAT_CODE', platCode);
                    commit('SET_EMAIL', email);
                    commit('SET_PHONE', phone);
                    commit('SET_ROLES', sysRoleList);
                    commit('SET_PRIVS', sysPrivilegeList);
                    resolve(data);
                })
                .catch(error => {
                    reject(error);
                });
        });
    },

    // user logout
    logout({ commit, state }) {
        return new Promise((resolve, reject) => {
            logout(state.token)
                .then(() => {
                    removeToken(); // must remove  token  first
                    resetRouter();
                    commit('RESET_STATE');
                    resolve();
                })
                .catch(error => {
                    reject(error);
                });
        });
    },

    // remove token
    resetToken({ commit }) {
        return new Promise(resolve => {
            removeToken(); // must remove  token  first
            commit('RESET_STATE');
            resolve();
        });
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions
};
