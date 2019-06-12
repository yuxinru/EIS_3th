import React from 'react';
import axios from 'axios';
import { Table} from 'antd';

const columns = [
    {
        title: 'OrderID',
        dataIndex: 'orderid',
    },
    {
        title: 'Type',
        dataIndex: 'type',
    },
    {
        title: 'Side',
        dataIndex: 'side',
    },
    {
        title: 'Qty',
        dataIndex: 'qty',
    },
    {
        title: 'Price',
        dataIndex: 'price',
    },
]



class RemainingOrder extends React.Component {
    state = {
        data: [],
    }

    componentDidMount() {
        this.timerID = setInterval(
            ()=>this.getOrder(),
            3000
        )
    }

    //组件卸载之前
    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    getOrder= ()=> {
        const _this = this
        axios.get("/order/myOrder?product=" + this.props.product + "&period=" + this.props.period)
        .then(function(response) {
            console.log(response)
            let local_data = [];
            let len = response.data.length
            console.log(len)
            for (let i = 0; i < len; i++) {
                local_data.push({
                    orderid: response.data[i].orderId,
                    type: response.data[i].type,
                    side: response.data[i].side,
                    qty: response.data[i].quantity,
                    price: response.data[i].price,
                })
            }

            _this.setState({
                data: local_data,
            })
        })
        .catch(function(e) {
            console.log(e)
        })
    }

    render() {
        return (
            <Table columns={columns} dataSource={this.state.data} size='small' bordered />
        );
    }
}

export default RemainingOrder;