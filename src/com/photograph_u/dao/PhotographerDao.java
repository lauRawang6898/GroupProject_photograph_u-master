package com.photograph_u.dao;

import java.util.List;
import java.util.Map;

public class PhotographerDao extends BaseDao {
    //check if photographer exist by userid
    public boolean queryExists(int userId) {
        String sql = "select count(*) as count from photographer where user_id=? and is_deleted=0";
        long count = (long) queryToMap(sql, new Object[]{userId}).get("count");
        return count == 1;
    }

    //check if photographer exist by userid and photographer's id 
    public boolean queryExistsByUserIdAndPhotographerId(int userId, int photographerId) {
        String sql = "select count(*) as count from photographer where user_id=? and id=? and is_deleted=0";
        long count = (long) queryToMap(sql, new Object[]{userId, photographerId}).get("count");
        return count == 1;
    }

    
    public boolean addPhotographer(int userId, String cardNo, String cardImage) {
        String sql = "insert into photographer(user_id,card_no,card_image) values(?,?,?)";
        int count = update(sql, new Object[]{userId, cardNo, cardImage});
        return count == 1;
    }

    public Map<String, Object> queryPhotographerByUserId(int userId) {
        String sql = "select id,user_id,price,server_content,introduce,star_value from photographer where user_id=? and is_deleted=0";
        Map<String, Object> photographerMap = queryToMap(sql, new Object[]{userId});
        return photographerMap;
    }

    public Map<String, Object> queryPhotographerById(int photographerId) {
        String sql = "select id,user_id,price,server_content,introduce,star_value from photographer where id=? and is_deleted=0";
        Map<String, Object> photographerMap = queryToMap(sql, new Object[]{photographerId});
        return photographerMap;
    }

    public boolean updatePhotographer(int userId, double price, String serverContent, String introduce) {
        String sql = "update photographer set price=?,server_content=?,introduce=? where user_id=? and is_deleted=0";
        int count = update(sql, new Object[]{price, serverContent, introduce, userId});
        return count == 1;
    }

    //根据学校查询摄影师
    public List<Map<String, Object>> queryPhotographerBySchool(String school) {
        String sql = "select a.id,a.user_id,b.nickname,b.head_image,a.price,a.server_content,a.introduce,a.star_value from photographer a inner join (select id,nickname,head_image from user where school=? and is_deleted=0) b on a.user_id=b.id order by a.star_value desc";
        List<Map<String, Object>> photographerMapList = queryToMapList(sql, new Object[]{school});
        return photographerMapList;
    }

    public int countPhotographerBySchool(String school) {
        String sql = "select count(*) as count from photographer a inner join (select id,nickname,head_image from user where school=? and is_deleted=0) b on a.user_id=b.id";
        Map<String, Object> resultMap = queryToMap(sql, new Object[]{school});
        Object countObject = resultMap.get("count");
        if (countObject == null) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(countObject));
    }

    public List<Map<String, Object>> queryPhotographerBySchoolWithOffset(String school, int size, int offset) {
        String sql = "select a.id,a.user_id,b.nickname,b.head_image,a.price,a.server_content,a.introduce,a.star_value from photographer a inner join (select id,nickname,head_image from user where school=? and is_deleted=0) b on a.user_id=b.id order by a.star_value desc limit ? offset ?";
        List<Map<String, Object>> photographerMapList = queryToMapList(sql, new Object[]{school, size, offset});
        return photographerMapList;
    }

    public int queryUserIdByPhotographerId(int photographerId) {
        String sql = "select user_id from photographer where id=? and is_deleted=0";
        int userId = (int) queryToMap(sql, new Object[]{photographerId}).get("userId");
        return userId;
    }

    public int queryPhotographerIdByUserId(int userId) {
        String sql = "select id from photographer where user_id=? and is_deleted=0";
        int photographerId = (int) queryToMap(sql, new Object[]{userId}).get("id");
        return photographerId;
    }

    public boolean updateStarValue(int photographerId, int changeStartValue) {
        String sql = "update photographer set star_value=star_value+? where id=? and is_deleted=0";
        int count = update(sql, new Object[]{changeStartValue, photographerId});
        return count == 1;
    }
}
