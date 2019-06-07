import WrappedNormalLoginForm from './component/LoginForm';
import React from 'react';
import imgURL from '../public/timg.jpg';



class LoginPage extends React.Component {
  render() {
    return (
      <div>
        <div className="login-area">
          <WrappedNormalLoginForm />
        </div>
      </div>
    );
  }
}

export default LoginPage;
