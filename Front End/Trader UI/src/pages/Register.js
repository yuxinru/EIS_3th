import React from 'react';
import WrappedRegistrationForm from './component/RegisterForm';

class RegisterPage extends React.Component {
    render() {
        return(
            <div>
                <img src={require('../public/timg2.jpg')} alt="" style={{width:'100%', height:'655px', }} />
                <div className="register-area" >
                    <div style={{marginRight:'5px', marginTop:'20px', marginLeft: '5px'}}>
                        <p style={{ marginLeft:'180px'}}>注册</p>
                        <WrappedRegistrationForm />
                    </div>
                </div>
            </div>
        );
    }
}

export default RegisterPage;