import React from 'react';
import { Select, InputNumber, Button, notification} from 'antd';


const { Option } = Select;

class SendOrder extends React.Component {
    state = {
        OrderType: "Market Order",
        BuyOrSell: "Buy",
        Name: "MyName",
        Price: null,
        PriceDisabled: true,
        Quantity: 1,
    };

    handleChange1 = e => {
        console.log({e})
        if ("Market Order" === e) {
            this.setState({
                OrderType: e,
                PriceDisabled: true,
                Price: null,
            })
        }
        else {
            this.setState({
                OrderType: e,
                PriceDisabled: false,
                Price: 1,
            })
        }
    };

    handleChange2 = e => {
        console.log({e})
        this.setState({
            BuyOrSell: e,
        })
    };

    handleChange3 = e => {
        console.log({e})
        this.setState({
            Price: e,
        })
    };

    handleChange4 = e => {
        console.log({e})
        this.setState({
            Quantity: e,
        })
    };

    Submit = e => {
        //axios

        // success
        notification.open({
            message: 'Notification',
            description:
              'Your order has been successfully created.',
            duration: 3,
            onClick: () => {
              console.log('Notification Clicked!');
            },
          });

        // reset
        this.setState({
            OrderType: "Market Order",
            BuyOrSell: "Buy",
            Name: "MyName",
            Price: null,
            PriceDisabled: true,
            Quantity: null,
        })
    }

    render() {
        return(
            <div style={{'marginTop':'40px', 'marginBottom': '60px'}}>
                <Select value={this.state.OrderType} style={{ width: 200 }} onChange={this.handleChange1}>
                    <Option value="Market Order">Market Order</Option>
                    <Option value="Limit Order">Limit Order</Option>
                    <Option value="Stop Order">Stop Order</Option>
                </Select>
                <Select value={this.state.BuyOrSell} style={{ width: 150, 'marginLeft':'20px' }} onChange={this.handleChange2}>
                    <Option value="Buy">Buy</Option>
                    <Option value="Sell">Sell</Option>
                </Select>
                <span style={{margin:'15px'}}>Price:</span>
                <InputNumber min={1} max={1000000} value={this.state.Price} onChange={this.handleChange3} disabled={this.state.PriceDisabled}/>
                <span style={{margin:'15px'}}>Quantity:</span>
                <InputNumber min={1} max={1000000} value={this.state.Quantity} onChange={this.handleChange4}/>
                <span style={{'marginLeft':'150px'}}></span>
                <Button type="primary" onClick={this.Submit} style={{float: 'right', 'marginRight':'120px'}}>Submit</Button>
            </div>
        );
    }
}

export default SendOrder;