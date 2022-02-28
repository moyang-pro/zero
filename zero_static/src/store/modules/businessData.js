const state = {
    showBmTab: ''
};

const mutations = {
    SET_BMTAB: (state, tab) => {
        state.showBmTab = tab;
    }
};

const actions = {
    changeBMTAB({ commit }, data) {
        commit('SET_BMTAB', data);
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions
};
