import React from 'react';
import MarketDepth from './MarketDepth';
import { Divider} from 'antd';

class Commodity extends React.Component {

    render() {
        return (
            <div>
                
                <Divider>Market Depth</Divider>
                <MarketDepth product={this.props.history.location.query.product} period={this.props.history.location.query.period} />
            </div>
        );
    }
}

export default Commodity;