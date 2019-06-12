import React from 'react';
import axios from 'axios';
import { Descriptions,message } from 'antd';

class PersonalInfo extends React.Component {
    state = {
        username: null,
        nickname: null,
        email: null,
        phonenumber: null,
    }

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
            username: response.data.username,
            nickname: response.data.nickname,
            email: response.data.email,
            phonenumber: response.data.phonenumber,
            })
        }
        })
        .catch(function(e){
        console.log(e)
        message.error("can not get user information")
        })
    }

    render() {
        return(
            <div>
                <Descriptions title="Personal Info" bordered size="small">
                    <Descriptions.Item label="Username">{this.state.username}</Descriptions.Item>
                    <Descriptions.Item label="Nickname">{this.state.nickname}</Descriptions.Item>
                    <Descriptions.Item label="Phone number">{this.state.phonenumber}</Descriptions.Item>
                    <Descriptions.Item label="E-mail">{this.state.email}</Descriptions.Item>
                </Descriptions>
            </div>
        );
    }
}

export default PersonalInfo;