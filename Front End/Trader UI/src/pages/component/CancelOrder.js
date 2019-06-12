import React from 'react';
import {InputNumber, Button, message} from 'antd';
import axios from 'axios';

class CancelOrder extends React.Component{
    state = {
        OrderType: "Cancel Order",
        OrderID: null,
    };

    handleChange = e => {
        this.setState({
            OrderID: e,
        })
    }

    Submit = e => {
        e.preventDefault();

        //检查数据
        if (this.state.OrderID === null) {
            message.error("OrderID can't be empty");
            return;
        }

        //传输数据
        axios.post('/order/send', {
            type: 'cancel',
            broker: "",
            cancelId: this.state.OrderID,
            product: this.props.product,
            period: this.props.period,
        })
        .then(function(response) {
            console.log(response)
            message.success('Order Cancel success');
        })
        .catch(function(e) {
            console.log(e)
            message.error('Order Cancel failure')
        })

        //重置数据
        this.setState({
            OrderID: null,
        })
    }

    render() {
        return (
            <div>
                <span style={{margin:'15px'}}>OrderID</span>
                <InputNumber min={0} max={10000000000} value={this.state.OrderID} onChange={this.handleChange}/>
                <Button type="primary" onClick={this.Submit} style={{float: 'right', 'marginRight':'120px'}}>Submit</Button>
            </div>
        );
    }
}


export default CancelOrder;