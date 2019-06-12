import React from 'react';
import { Table} from 'antd';
import axios from 'axios';

const columns = [
    {
        title:'Level',
        dataIndex:'level',
        key: 'col_1',
    },
    {
        title:'Buy Vol',
        dataIndex:'buyvol',
        key: 'col_2',
    },
    {
        title:'Price',
        dataIndex:'price',
        key: 'col_3',
    },
    {
        title:'Sell Vol',
        dataIndex:'sellvol',
        key: 'col_4',
    },
    {
        title:'Level',
        dataIndex:'level2',
        key: 'col_5',
    },
]

class MarketDepth extends React.Component {
    state = {
        bordered: true,
        data : [
        ],
    };

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

    refresh =()=> {
        // 存储this指针
        const _this = this

        axios.get("/order/marketDepth?product=" + this.props.product + "&period=" + this.props.period)
        .then(function(response) {
            console.log(response)

            let local_data = [];
            let len = response.data.length
            console.log(response.data.length)
            console.log(len)
            for (let i = 0; i < len; i++) {
                if (!response.data[i].sellVol) {
                    local_data.push({price: response.data[i].price, buyvol: response.data[i].buyVol, level: response.data[i].buyLevel,})
                }
                else {
                    local_data.push({price: response.data[i].price, sellvol: response.data[i].sellVol, level2: response.data[i].sellLevel,})    
                }
            }
            
            _this.setState({
                data: local_data,
            })
        })
        .catch(function(e){
            _this.setState({
                data:[],
            })
            console.log(e)
        })
    }

    render() {
        return(
            <div >
                <Table bordered={this.state.bordered} columns={columns} size='small' dataSource={this.state.data} />
            </div>
        );
    }
}

export default MarketDepth;