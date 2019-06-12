import React from 'react';
import MarketDepth from './MarketDepth';
import SendOrder from './SendOrder';
import CancelOrder from './CancelOrder';
import RemainingOrder from './RemainingOrder'
import { Divider} from 'antd';

class Commodity extends React.Component {

    render() {
        return (
            <div>
                <div style={{height:'20px'}}></div>
                <Divider>Market Depth</Divider>
                <MarketDepth product={this.props.history.location.query.product} period={this.props.history.location.query.period} />
                <Divider>Remaining Order</Divider>
                <RemainingOrder product={this.props.history.location.query.product} period={this.props.history.location.query.period}/>
                <Divider>Send Order</Divider>
                <SendOrder product={this.props.history.location.query.product} period={this.props.history.location.query.period} />
                <Divider>Cancel Order</Divider>
                <CancelOrder product={this.props.history.location.query.product} period={this.props.history.location.query.period} />
            </div>
        );
    }
}

export default Commodity;