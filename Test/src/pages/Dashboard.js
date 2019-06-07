import React from 'react';
import { Layout, Menu, Icon, Avatar } from 'antd';
import Link from 'umi/link';



const { Header, Footer, Sider, Content } = Layout;


// 引入子菜单组件
const SubMenu = Menu.SubMenu; 

class DashboardPage extends React.Component {
  state = {
    collapsed: false,
  };

  onCollapse = collapsed => {
    console.log(collapsed);
    this.setState({ collapsed });
  };

  render() {
    return (
      <Layout>
        <Sider width={256} style={{ minHeight: '100vh' }} collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
          <div style={{ height: '80px'}}>
            <Avatar shape="square" size={50} style={{backgroundColor: '#87d068', marginTop:'15px', marginLeft:'15px'}}>U</Avatar>
          </div>
          <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
            <Menu.Item key="1">
              <Link to="/Dashboard">
                <Icon type="pie-chart" />
                <span>OrderBlotter</span>
              </Link>
            </Menu.Item>
            <SubMenu
              key="sub1"
              title={<span><Icon type="dashboard" /><span>Commodities</span></span>}
            >
               <Menu.Item key="2"><Link to="/Dashboard/Commodity?id=1">商品1</Link></Menu.Item>
               <Menu.Item key="3"><Link to="/Dashboard/Commodity?id=2">商品2</Link></Menu.Item>
               <Menu.Item key="4"><Link to="/Dashboard/Commodity?id=3">商品3</Link></Menu.Item>
               <Menu.Item key="5"><Link to="/Dashboard/Commodity?id=4">商品4</Link></Menu.Item>
            </SubMenu>
          </Menu>
        </Sider>
        <Layout >
       

          <Content style={{ margin: '24px 16px 0' }}>
            <div style={{ padding: 24, background: '#fff', minHeight: 700 }}>
              {this.props.children}
            </div>
          </Content>
          <Footer style={{ textAlign: 'center' }}>Trader UI ©2019 Created by H.R.J</Footer>
        </Layout>
      </Layout>
    )
  }
}

export default DashboardPage;