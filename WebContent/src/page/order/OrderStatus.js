export default {
    '01': {
        'status': '已提交待支付',
        'action': ['确认支付','取消订单']
    },
    '02': {
        'status': '支付成功',
        'action': ['确认收货','取消订单']
    },
    '03': {
        'status': '未支付已取消',
        'action': []
    },
    '04': {
        'status': '配送中',
        'action': ['确认收货','取消订单']
    },
    '05': {
        'status': '配送完成',
        'action': ['确认收货','取消订单']
    },
    '06': {
        'status': '已收货',
        'action': ['订单评价']
    },
    '07': {
        'status': '已评价',
        'action': []
    },
    '08': {
        'status': '申请撤销',
        'action': []
    },
    '12': {
        'status': '退款完成',
        'action': []
    },
    '13': {
        'status': '交易关闭',
        'action': []
    }
}