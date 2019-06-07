
// ref: https://umijs.org/config/
export default {
  treeShaking: true,
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
  routes: [
    {
      path: '/', component: './Login'
    },
    {
      path: '/Dashboard', component: './Dashboard',
      routes: [
        {
          path: '/Dashboard', component: './component/OrderBlotter',
        },
        {
          path: '/Dashboard/Commodity', component: './component/Commodity',
        }
      ]
    }
  ],
}
