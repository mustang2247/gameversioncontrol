// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import Vuex from 'vuex';
import VueResource from 'vue-resource';
import App from './App';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router';

Vue.use(Vuex);
Vue.use(VueResource);
Vue.use(ElementUI);

Vue.config.productionTip = false;

/* vuex 全局变量 */
const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++;
    }
  }
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store, //把 store 对象提供给 “store” 选项，这可以把 store 的实例注入所有的子组件
	router,
  components: { App },
  template: '<App/>'
})
