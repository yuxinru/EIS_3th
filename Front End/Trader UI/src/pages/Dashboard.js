import React from 'react';
import { Layout, Menu, Icon, Avatar, message} from 'antd';
import Link from 'umi/link';
import axios from 'axios';



const { Header, Footer, Sider, Content } = Layout;


// 引入子菜单组件
const SubMenu = Menu.SubMenu; 

class DashboardPage extends React.Component {
  state = {
    collapsed: false,
    nickname: null,
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
          nickname: response.data.nickname
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
        <Layout>
          <Sider theme="dark" width={256} style={{ minHeight: '100vh' }} collapsible collapsed={this.state.collapsed} onCollapse={this.onCollapse}>
            <Avatar shape="square" size={45} style={{float:'left', marginTop: '10px', marginLeft: '20px', marginBottom: '10px'}}>{this.state.nickname === null ? null : this.state.nickname.slice(0, 1)}</Avatar>
            <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
              <Menu.Item key="1">
                <Link to="/Dashboard">
                  <Icon type="pie-chart" />
                  <span>PersonalInfo</span>
                </Link>
              </Menu.Item>
              <Menu.Item key="2">
                <Link to="/Dashboard/OrderBlotter">
                  <Icon type="pie-chart" />
                  <span>OrderBlotter</span>
                </Link>
              </Menu.Item>
              <SubMenu key="sub1" title={<span><Icon type="dashboard" /><span>Gold</span></span>}>
                <Menu.Item key="sub1_1"><Link to="/Dashboard/Commodity?product=gold&period=SEP16">SEP16</Link></Menu.Item>
                <Menu.Item key="sub1_2"><Link to="/Dashboard/Commodity?product=gold&period=OCT10">OCT10</Link></Menu.Item>
                <Menu.Item key="sub1_3"><Link to="/Dashboard/Commodity?product=gold&period=FEB1">FEB1</Link></Menu.Item>
              </SubMenu>
              <SubMenu key="sub2" title={<span><Icon type="dashboard" /><span>Petro</span></span>}>
                <Menu.Item key="sub2_1"><Link to="/Dashboard/Commodity?product=petro&period=JUL5">JUL5</Link></Menu.Item>
                <Menu.Item key="sub2_2"><Link to="/Dashboard/Commodity?product=petro&period=JUN12">JUN12</Link></Menu.Item>
                <Menu.Item key="sub2_3"><Link to="/Dashboard/Commodity?product=petro&period=SEP16">SEP16</Link></Menu.Item>
              </SubMenu>
              <SubMenu key="sub3" title={<span><Icon type="dashboard" /><span>Steel</span></span>}>
                <Menu.Item key="sub3_1"><Link to="/Dashboard/Commodity?product=steel&period=FEB1">FEB1</Link></Menu.Item>
                <Menu.Item key="sub3_2"><Link to="/Dashboard/Commodity?product=steel&period=AUG13">AUG13</Link></Menu.Item>
                <Menu.Item key="sub3_3"><Link to="/Dashboard/Commodity?product=steel&period=APR7">APR7</Link></Menu.Item>
                <Menu.Item key="sub3_4"><Link to="/Dashboard/Commodity?product=steel&period=NOV12">NOV12</Link></Menu.Item>
              </SubMenu>
            </Menu>
          </Sider>
          <Layout >
            <Header>
              <p className='fff'>Trader UI</p>
            </Header>
            <Content style={{ margin: '24px 16px 0' }}>
              <div style={{ padding: 24, background: '#fff', minHeight: 700 }}>
                {this.props.children}
              </div>
            </Content>
            <Footer style={{ textAlign: 'center' }}>Trader UI ©2019 Created by H.R.J</Footer>
          </Layout>
        </Layout>
      </Layout>
    )
  }
}

export default DashboardPage;