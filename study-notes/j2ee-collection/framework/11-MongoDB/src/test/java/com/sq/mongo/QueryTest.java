package com.sq.mongo;

import com.alibaba.fastjson.JSON;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.sq.mongo.entity.User;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class QueryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    //分页查询文档，显示第2页，每页显示3个，按照id升序排列
    @Test
    public void testQuery() {
        Query query = new Query(); // 创建查询对象
        query.skip(3).limit(3);// 设置分页信息
        query.with(Sort.by(Sort.Direction.ASC, "id"));// 设置排序规则
        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }

    // 查询所有name为dafei的文档
    @Test
    public void testQuery2() throws Exception {
        // 构建限制条件 {"name": "dafei"}
        Criteria criteria = Criteria.where("name").is("dafei");
        Query query = new Query();
        query.addCriteria(criteria);
        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }

    @Test
    public void testQuery2Doc() throws Exception {
        // 构建限制条件 {"name": "dafei"}
        //DBObject object = new BasicDBObject("name", "dafei");
        //BasicQuery query = new BasicQuery(object.toString());
        BasicQuery query = new BasicQuery("{name:'dafei'}");
        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }

    // 查询所有name为dafei或者age<30的文档
    @Test
    public void testQuery3() throws Exception {
        // 构建限制条件 { "$or": [{"name": "dafei"}, {"age": {"$lt": 30}}] }
        Criteria criteria = new Criteria().orOperator(
                Criteria.where("name").is("dafei"),
                Criteria.where("age").lt(30));
        Query query = new Query();
        query.addCriteria(criteria);
        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }

    @Test
    public void testQuery3Doc() throws Exception {
        // 构建限制条件 { "$or": [{"name": "dafei"}, {"age": {"$lt": 30}}] }
       /* BasicDBList basicDBList = new BasicDBList();
        basicDBList.add(new BasicDBObject("name", "dafei"));
        basicDBList.add(new BasicDBObject("age",new BasicDBObject("$lt", 30)));
        DBObject obj = new BasicDBObject();
        obj.put("$or", basicDBList);`
        BasicQuery query = new BasicQuery(obj.toString());*/
        BasicQuery query = new BasicQuery("{$or:[{name:'dafei'}, {age:{$lt:30}}]}");
        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }

    // 查询所有name含有fei并且30<=age<=32的文档
    @Test
    public void testQuery4() throws Exception {
        // 构建限制条件 { "$and" : [{"name": {"$regex": ".*fei.*"} }, {"age": {"$gte": 30, "$lte": 32 } }] }
        Criteria criteria = new Criteria().andOperator(
                Criteria.where("name").regex(".*fei.*"),
                Criteria.where("age").gte(30).lte(32)
        );
        Query query = new Query();
        query.addCriteria(criteria);
        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }

    @Test
    public void testQuery4Doc() throws Exception {
        BasicQuery query = new BasicQuery("{$and:[{name:{$regex:'.*fei.*'}}, {age:{$gte:30, $lte:32}}]}");
        List<User> list = mongoTemplate.find(query, User.class, "users");
        list.forEach(System.err::println);
    }


    @Test
    public void testQuery3Bson() {


        Bson bson = Filters.or(Arrays.asList(Filters.eq("name", "dafei"), Filters.lt("age", 20)));
        MongoCursor<Document> cursor = null;
        try {
            FindIterable<Document> findIterable = mongoTemplate.getCollection("users").find(bson);
            cursor = findIterable.iterator();
            List<User> list = new ArrayList<>();
            while (cursor.hasNext()) {
                Document object = cursor.next();
                User entity = JSON.parseObject(object.toJson(), User.class);
                list.add(entity);
            }
            list.forEach(System.out::println);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
