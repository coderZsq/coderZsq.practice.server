package com.sq.mongo.repository;

import com.sq.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

//jpa
public interface UserRepository extends MongoRepository<User, String> {
    /*User findByNameAndAge(String daname, int age);
    List<User> findByNameOrAge(String name, int age);
    List<User> findByAgeBetween(int i, int i1);
    List<User> findByAgeLessThan(int i);
    List<User> findByAgeLessThanEqual(int age);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByAgeGreaterThanEqual(int i);
    User findByNameIsNull();
    List<User> findByNameIsNotNull();
    List<User> findByNameLike(String name);
    List<User> findByNameNotLike(String name);
    List<User> findByNameStartingWith(String da);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContaining(String a);
    List<User> findByIdOrderByAge(Long id);
    List<User> findByNameNot(String daname);
    List<User> findBySexTrue();
    User findByNameIgnoreCase(String daname);*/
}
