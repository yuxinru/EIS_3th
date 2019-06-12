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
        data: []
    }

    //组件渲染之后
    componentDidMount() {
        this.timerID = setInterval(
            ()=>this.refresh(),
            1000
        )
    }

    //组件卸载之前
    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    refresh = ()=> {
        const _this=this;
        axios.get("/order/orderBlotter")
        .then(function(response) {
            let local_data = []

            let len = response.data.length;
            for (let j = 0; j < len; j++) {
                let i = response.data[j]
                local_data.push({
                    key: j,
                    tradeid: i.tradeid,
                    broker: i.broker,
                    product: i.productid,
                    period: i.period,
                    price: i.price,
                    qty: i.quantity,
                    trader1: i.iniTrader,
                    company1: null,
                    side1: i.iniSide,
                    trader2: i.cplTrader,
                    company2: null,
                    side2: i.cplSide,
                })
            }
            _this.setState({
                data:local_data,
            })
        })
        .catch(function(e){
            console.log(e)
            _this.setState({
                data:[],
            })
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