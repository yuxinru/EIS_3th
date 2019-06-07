import React from 'react';
import MarketDepth from './MarketDepth';
import SendOrder from './SendOrder';
import CancelOrder from './CancelOrder';
import { Divider} from 'antd';

class Commodity extends React.Component {

    click=e =>{
        console.log( this.props.history.location.query.id)
    }
    render() {
        return (
            <div>
                <Divider>Market Depth</Divider>
                <MarketDepth />
                <Divider>Market & Limit & Stop Order</Divider>
                <SendOrder />
                <Divider>Cancel Order</Divider>
                <CancelOrder />
            </div>
        );
    }
}

export default Commodity;