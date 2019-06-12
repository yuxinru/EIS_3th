import { Form, Icon, Input, Button, Checkbox, message } from 'antd';
import React from 'react';
import router from 'umi/router';
import Link from 'umi/link';
import axios from 'axios';


class NormalLoginForm extends React.Component {
  handleSubmit = e => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
        console.log(values.username)
        //发送数据
        axios.post('/user/login', {
          username:values.username,
          password:values.password,
        }).then(function(response){
          if (response.data.status === "error") {
            console.log(response)
            message.error("Failed user login");
          }
          else {
            console.log(e)
            router.push('/Dashboard')
          }
        }).catch(function(e){
          message.error("Failed user login due to Network");
        })
      }
    });
  };

  goToRegister = e => {
    router.push('/Register')
  };

  render() {
    const { getFieldDecorator } = this.props.form;
    return (
        <Form onSubmit={this.handleSubmit} className="login-form">
          <Form.Item>
            {getFieldDecorator('username', {
              rules: [{ required: true, message: 'Please input your username!' }],
            })(
              <Input
                prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />}
                placeholder="Username"
              />,
            )}
          </Form.Item>
          <Form.Item>
            {getFieldDecorator('password', {
              rules: [{ required: true, message: 'Please input your Password!' }],
            })(
              <Input
                prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />}
                type="password"
                placeholder="Password"
              />,
            )}
          </Form.Item>
          <Form.Item>
            {getFieldDecorator('remember', {
              valuePropName: 'checked',
              initialValue: true,
            })(<Checkbox>Remember me</Checkbox>)}
            <Button type="primary" htmlType="submit" className="login-form-button">
              Log in
            </Button>
            Or <Link to='/Register'>register now!</Link>
          </Form.Item>
        </Form>
    );
  }
}

const WrappedNormalLoginForm = Form.create({ name: 'normal_login' })(NormalLoginForm);

export default WrappedNormalLoginForm;