import React from 'react';
import {InputNumber, Button, notification} from 'antd';

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
        //axios

        // success
        notification.open({
            message: 'Notification',
            description:
              'Cancel Order successsfully created.',
            duration: 3,
            onClick: () => {
              console.log('Notification Clicked!');
            },
        });

        //reset
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