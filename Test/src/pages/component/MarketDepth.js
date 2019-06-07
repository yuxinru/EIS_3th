import React from 'react';
import { Table} from 'antd';

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
            {
                key: 'data1',
                price: '1004',
                sellvol: 20,
                level2: 3,
            },
            {
                key: 'data2',
                price: '1002',
                sellvol: 50,
                level2: 2,
            },
            {
                key: 'data3',
                price: '1000',
                sellvol: 50,
                level2: 1,
            },
            {
                key: 'data4',
                price: '998',
                buyvol: 200,
                level: 1,
            },
            {
                key: 'data5',
                price: '996',
                buyvol: 200,
                level: 2,
            },
            {
                key: 'data6',
                price: '994',
                buyvol: 200,
                level: 3,
            },
        ],
    };


    render() {
        return(
            <div >
                <Table bordered={this.state.bordered} columns={columns} size='small' dataSource={this.state.data} />
            </div>
        );
    }
}

export default MarketDepth;