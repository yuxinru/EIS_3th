import React from 'react';
import { Layout, Menu, Icon, message} from 'antd';
import Link from 'umi/link';
import axios from 'axios';



const { Header, Footer, Sider, Content } = Layout;


// 引入子菜单组件
const SubMenu = Menu.SubMenu; 

class DashboardPage extends React.Component {
  state = {
    collapsed: false,
    username: '佚名',
  };

  componentDidMount() {
    const _this = this;
    axios.get('/user/information')
    .then(function(response){
      console.log(response)
      if (response.data.status === "error") {
        message.error("can not get user information")
      }
      else {
        _this.setState({
          username: response.data.username
        })
      }
    })
    .catch(function(e){
      console.log(e)
      message.error("can not get user information")
    })
  }

  onCollapse = collapsed => {
    console.log(collapsed);
    this.setState({ collapsed });
  };


  render() {
    return (
      <Layout>
          <Header>
            <p className='fff'>Broker UI</p>
          </Header>
        <Layout>
          <Sider theme="dark" width={256} style={{ minHeight: '100vh' }} collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
            <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
              <Menu.Item key="1">
                <Link to="/Dashboard">
                  <Icon type="pie-chart" />
                  <span>OrderBlotter</span>
                </Link>
              </Menu.Item>
              <SubMenu key="sub3" title={<span><Icon type="dashboard" /><span>Steel</span></span>}>
                <Menu.Item key="sub3_1"><Link to="/Dashboard/Commodity?product=steel&period=FEB1">FEB1</Link></Menu.Item>
                <Menu.Item key="sub3_2"><Link to="/Dashboard/Commodity?product=steel&period=AUG13">AUG13</Link></Menu.Item>
                <Menu.Item key="sub3_3"><Link to="/Dashboard/Commodity?product=steel&period=APR7">APR7</Link></Menu.Item>
                <Menu.Item key="sub3_4"><Link to="/Dashboard/Commodity?product=steel&period=NOV12">NOV12</Link></Menu.Item>
              </SubMenu>
            </Menu>
          </Sider>
          <Layout >
            <Content style={{ margin: '24px 16px 0' }}>
              <div style={{ padding: 24, background: '#fff', minHeight: 500 }}>
                {this.props.children}
              </div>
            </Content>
            <Footer style={{ textAlign: 'center' }}>Broker UI ©2019 Created by H.R.J</Footer>
          </Layout>
        </Layout>
      </Layout>
    )
  }
}

export default DashboardPage;