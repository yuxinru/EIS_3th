import React from 'react';
import { Select, InputNumber, Button, message} from 'antd';
import axios from 'axios';

const { Option } = Select;

class SendOrder extends React.Component {
    state = {
        OrderType: "Market Order",
        BuyOrSell: "buy",
        Name: "MyName",
        Price: null,
        PriceDisabled: true,
        Quantity: null,
        strategy: 'none',
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
                Price: null,
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

    handleChange5 = e => {
        this.setState({
            strategy: e
        })
    }

    Submit = e => {
        e.preventDefault();
        //检查数据
        if ((this.state.OrderType === "Market Order" && this.state.Quantity === null)){
            message.error("Quantity can't be empty");
            return;
        }
        if (this.state.OrderType === "Limit Order" || this.state.OrderType === "Stop Order"){
            if (this.state.Quantity === null){
                message.error("Quantity can't be empty");
                return;
            }
            if (this.state.Price === null){
                message.error("Price can't be empty");
                return;
            }
        }

        //数据
        if (this.state.OrderType === "Market Order") {
            axios.post('/order/send', {
                type: 'market',
                side: this.state.BuyOrSell,
                quantity: this.state.Quantity,
                broker: "",
                price: this.state.Price,
                product: this.props.product,
                period: this.props.period,
                strategy: this.state.strategy,
            })
            .then(function(response) {
                console.log(response)
                message.success('Order creation success');
            })
            .catch(function(e) {
                console.log(e)
                message.error('Order creation failure')
            })
        }
        else if (this.state.OrderType === "Limit Order") {
            axios.post('/order/send', {
                type: 'limit',
                side: this.state.BuyOrSell,
                quantity: this.state.Quantity,
                broker: "",
                price: this.state.Price,
                product: this.props.product,
                period: this.props.period,
                strategy: this.state.strategy,
            })
            .then(function(response) {
                console.log(response)
                message.success('Order creation success');
            })
            .catch(function(e) {
                console.log(e)
                message.error('Order creation failure')
            })
        }
        else {
            axios.post('/order/send', {
                type: 'stop',
                side: this.state.BuyOrSell,
                quantity: this.state.Quantity,
                broker: "",
                price: this.state.Price,
                product: this.props.product,
                period: this.props.period,
                strategy: this.state.strategy,
            })
            .then(function(response) {
                console.log(response)
                message.success('Order creation success');
            })
            .catch(function(e) {
                console.log(e)
                message.error('Order creation failure')
            })
        }

        // 重置数据
        this.setState({
            OrderType: "Market Order",
            BuyOrSell: "buy",
            Name: "MyName",
            Price: null,
            PriceDisabled: true,
            Quantity: null,
            strategy: 'none',
        })
    }

    render() {
        return(
            <div style={{'marginTop':'40px', 'marginBottom': '60px'}}>
                <span style={{margin:'15px'}}>OrderType:</span>
                <Select value={this.state.OrderType} style={{ width: 200 }} onChange={this.handleChange1}>
                    <Option value="Market Order">Market Order</Option>
                    <Option value="Limit Order">Limit Order</Option>
                    <Option value="Stop Order">Stop Order</Option>
                </Select>
                <span style={{marginLeft:'40px'}}>Side:</span>
                <Select value={this.state.BuyOrSell} style={{ width: 150, 'marginLeft':'20px' }} onChange={this.handleChange2}>
                    <Option value="buy">Buy</Option>
                    <Option value="sell">Sell</Option>
                </Select>
                <br />
                <div style={{marginTop:'20px'}}/>
                <span style={{margin:'15px'}}>Price:</span>
                <InputNumber min={1} max={1000000} value={this.state.Price} onChange={this.handleChange3} disabled={this.state.PriceDisabled}/>
                <span style={{marginLeft:'40px', marginRight:'15px'}}>Quantity:</span>
                <InputNumber min={1} max={1000000} value={this.state.Quantity} onChange={this.handleChange4}/>
                <span style={{marginLeft:'40px', marginRight: '15px'}}>Strategy:</span>
                <Select value={this.state.strategy} style={{ width: 150 }} onChange={this.handleChange5}>
                    <Option value="none">None</Option>
                    <Option value="iceburg">Iceburg</Option>
                    <Option value="vwap">VWAP</Option>
                    <Option value="twap">TWAP</Option>
                </Select>
                <Button type="primary" onClick={this.Submit} style={{marginRight: '120px', float: 'right'}}>Submit</Button>
            </div>
        );
    }
}

export default SendOrder;