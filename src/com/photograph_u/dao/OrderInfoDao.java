package com.photograph_u.dao;

import java.util.List;
import java.util.Map;

public class OrderInfoDao extends BaseDao {
    //add order information
    public boolean addOrderInfo(int userId, int photographerId, String orderTime, String address, String other) {
        String sql = "insert into orderinfo(user_id,photographer_id,order_time,address,other) values(?,?,?,?,?)";
        int count = update(sql, new Object[]{userId, photographerId, orderTime, address, other});
        return count == 1;
    }

    //delete order
    public boolean deleteOrderInfo(int userId, int orderInfoId) {
        String sql = "update orderinfo set is_deleted=1 where id=? and user_id=? and state=0 and is_deleted=0";
        int count = update(sql, new Object[]{orderInfoId, userId});
        return count == 1;
    }

    //find photographer by user id
    public int queryPhotographerId(int userId) {
        String sql = "select id from photographer where user_id=? and is_deleted=0";
        int photographerId = (int) queryToMap(sql, new Object[]{userId}).get("id");
        return photographerId;
    }

    //count photographers
    public int countOrders(int photographerId, int state) {
        String sql = "select count(*) as count from orderinfo where photographer_id=? and state=? and is_deleted=0";
        Map<String, Object> resultMap = queryToMap(sql, new Object[]{photographerId, state});
        Object countObject = resultMap.get("count");
        if (countObject == null) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(countObject));
    }

    
    public List<Map<String, Object>> queryUnTreatedOrders(int photographerId) {
        String sql = "select id,user_id,order_time,address,other,state from orderinfo where photographer_id=? and state=0 and is_deleted=0";
        List<Map<String, Object>> photographerMapList = queryToMapList(sql, new Object[]{photographerId});
        return photographerMapList;
    }

    public List<Map<String, Object>> queryUnTreatedOrdersWithOffset(int photographerId, int size, int offset) {
        String sql = "select a.id,a.user_id,a.order_time,a.address,a.other,a.state from orderinfo a,(select id from orderinfo where photographer_id=? and state=0 and is_deleted=0 limit ? offset ?) b where a.id=b.id";
        List<Map<String, Object>> photographerMapList = queryToMapList(sql, new Object[]{photographerId, size, offset});
        return photographerMapList;
    }

    public List<Map<String, Object>> queryTreatedOrders(int photographerId) {
        String sql = "select id,user_id,order_time,address,other,state from orderinfo where photographer_id=? and state<>0 and is_deleted=0";
        List<Map<String, Object>> photographerMapList = queryToMapList(sql, new Object[]{photographerId});
        return photographerMapList;
    }

    public List<Map<String, Object>> queryTreatedOrdersWithOffset(int photographerId, int size, int offset) {
        String sql = "select a.id,a.user_id,a.order_time,a.address,a.other,a.state from orderinfo a,(select id from orderinfo where photographer_id=? and state<>0 and is_deleted=0 limit ? offset ?) b where a.id=b.id";
        List<Map<String, Object>> photographerMapList = queryToMapList(sql, new Object[]{photographerId, size, offset});
        return photographerMapList;
    }

    public boolean checkOrderInfo(int photographerId, int orderInfoId) {
        String sql = "select count(*) as count from orderinfo where id=? and photographer_id=? and state=0 and is_deleted=0";
        long count = (long) queryToMap(sql, new Object[]{orderInfoId, photographerId}).get("count");
        return count == 1;
    }

    public boolean changeOrderState(int orderInfoId, int state) {
        String sql = "update orderinfo set state=? where id=?";
        int count = update(sql, new Object[]{state, orderInfoId});
        return count == 1;
    }

    public boolean checkOrderInfoByUserIdAndPhotographerId(int userId, int photographerId) {
        String sql = "select count(*) as count from orderinfo where user_id=? and photographer_id=? and state<>0 and is_deleted=0";
        long count = (long) queryToMap(sql, new Object[]{userId, photographerId}).get("count");
        return count > 0;
    }

    public List<Map<String, Object>> queryOrdersByUserId(int userId) {
        String sql = "select id,photographer_id,order_time,address,other,state from orderinfo where user_id=? and is_deleted=0";
        List<Map<String, Object>> photographerMapList = queryToMapList(sql, new Object[]{userId});
        return photographerMapList;
    }

    public List<Map<String, Object>> queryOrdersByUserIdWithOffset(int userId, int size, int offset) {
        String sql = "select id,photographer_id,order_time,address,other,state from orderinfo where user_id=? and is_deleted=0 limit ? offset ?";
        List<Map<String, Object>> photographerMapList = queryToMapList(sql, new Object[]{userId, size, offset});
        return photographerMapList;
    }

    public int countOrders(int userId) {
        String sql = "select count(*) as count from orderinfo where user_id=? and is_deleted=0";
        Map<String, Object> resultMap = queryToMap(sql, new Object[]{userId});
        Object countObject = resultMap.get("count");
        if (countObject == null) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(countObject));
    }
}
