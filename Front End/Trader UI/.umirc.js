
// ref: https://umijs.org/config/
export default {
  treeShaking: true,
  history:"hash",
  plugins: [
    // ref: https://umijs.org/plugin/umi-plugin-react.html
    ['umi-plugin-react', {
      antd: true,
      dva: true,
      dynamicImport: false,
      title: 'Test',
      dll: false,
    }],
  ],
  proxy: {
    '/order': {
      target: 'http://localhost:30231',
      changeOrigin:true,
    },
    '/user': {
      target: 'http://localhost:30231',
      changeOrigin:true,
    }
  },
  routes: [
    {
      path: '/', component: './Login'
    },
    {
      path: '/Dashboard', component: './Dashboard',
      routes: [
        {
          path: '/Dashboard', component: './component/PersonalInfo',
        },
        {
          path: '/Dashboard/Commodity', component: './component/Commodity',
        },
        {
          path: '/Dashboard/OrderBlotter', component: './component/OrderBlotter',
        }
      ]
    },
    {
      path: '/Register', component: './Register'
    }
  ],
}
