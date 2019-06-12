import WrappedNormalLoginForm from './component/LoginForm';
import React from 'react';



class LoginPage extends React.Component {
  render() {
    return (
      
      <div>
        <img src={require('../public/page1.jpg')} alt="" style={{width:'100%', height:'655px', }} />
        <div className="login-area" >
          <div style={{marginLeft:'10px', marginTop:'30px',}}>
            <p style={{ marginLeft:'130px'}}>登录</p>
            <WrappedNormalLoginForm />
          </div>
        </div>
      </div>
 
    );
  }
}

export default LoginPage;
