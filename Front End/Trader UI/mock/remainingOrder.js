import { setTimeout } from "timers";

const data = [
    {
        orderId: 123,
        username: 'hrj',
        type: 'market',
        side: 'sell',
        product: 'product',
        period: 'period',
        quantity: 50,
        broker: 'M',
        price: 100,
    }
]

export default {
    'GET /order/myOrder':function(req, res, next){
        setTimeout(()=> {
            res.json({
                result: data,
            })
        }, 250)
    }   
}