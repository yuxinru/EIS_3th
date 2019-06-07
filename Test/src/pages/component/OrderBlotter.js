import React from 'react';
import { Divider, Table } from 'antd';
import axios from 'axios';

const columns = [
    {
        title: 'TradeID',
        dataIndex: 'tradeid',
        sorter: (a, b) => a.tradeid - b.tradeid,
    },
    {
        title: 'Broker',
        dataIndex: 'broker',
        filters: [
            {
                text: 'M',
                value: 'M',
            },
            {
                text: 'N',
                value: 'N',
            }
        ],
        onFilter: (value, record) => record.broker.indexOf(value) === 0,
    },
    {
        title: 'Product',
        dataIndex: 'product',
        filters: [
            {
                text: 'Gold Swaps',
                value: 'Gold Swaps',
            },
        ],
        onFilter: (value, record) => record.product.indexOf(value) === 0,
    },
    {
        title: 'Period',
        dataIndex: 'period',
        filters: [
            {
                text: 'SEP16',
                value: 'SEP16',
            },
            {
                text: 'JUL16',
                value: 'JUL16',
            },
        ],
        onFilter: (value, record) => record.period.indexOf(value) === 0,

    },
    {
        title: 'Price',
        dataIndex: 'price',
        sorter: (a, b) => a.price - b.price,
    },
    {
        title: 'Qty',
        dataIndex: 'qty',
        sorter: (a, b) => a.qty - b.qty,
    },
    {
        title: 'Initiator',
        children:[
            {
                title: 'Trader',
                dataIndex: 'trader1',
            },
            {
                title: 'Company',
                dataIndex: 'company1',
            },
            {
                title: 'Side',
                dataIndex: 'side1',
            }
        ]
    },
    {
        title: 'Completion',
        children:[
            {
                title: 'Trader',
                dataIndex: 'trader2',
            },
            {
                title: 'Company',
                dataIndex: 'company2',
            },
            {
                title: 'Side',
                dataIndex: 'side2',
            }
        ]
    },
]

class OrderBlotter extends React.Component {
    state= {
        data: [
            {
                key: 1,
                tradeid: 312345,
                broker: 'M',
                product: 'Gold Swaps',
                period: 'SEP16',
                price: 1246,
                qty: 50,
                trader1: 'Sam Wang',
                company1: 'ABC Corp',
                side1: 'Sell',
                trader2: 'Sixian Liu',
                company2: 'MS',
                side2: 'Buy',
            },
            {
                key: 2,
                tradeid: 312347,
                broker: 'M',
                product: 'Gold Swaps',
                period: 'SEP16',
                price: 1244,
                qty: 10,
                trader1: 'Sam Wang',
                company1: 'ABC Corp',
                side1: 'Sell',
                trader2: 'Sixian Liu',
                company2: 'MS',
                side2: 'Buy',
            },
            {
                key: 3,
                tradeid: 322345,
                broker: 'N',
                product: 'Gold Swaps',
                period: 'JUL16',
                price: 1248,
                qty: 20,
                trader1: 'James Li',
                company1: 'CBA Corp',
                side1: 'Buy',
                trader2: 'Sixian Liu',
                company2: 'MS',
                side2: 'Sell',
            },
        ]
    }

    componentDidMount() {
        axios.get("http://localhost:8080/order/orderBlotter")
        .then(function(response) {
            console.log(response)
        })
    }


    render() {
        return (
            <div>
                <Divider>Order Blotter</Divider>
                <Table columns={columns} dataSource={this.state.data} bordered />
            </div>
        );
    }
}

export default OrderBlotter;