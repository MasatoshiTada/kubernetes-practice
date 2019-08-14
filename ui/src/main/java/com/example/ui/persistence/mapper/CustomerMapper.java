package com.example.ui.persistence.mapper;

import com.example.ui.persistence.entity.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CustomerMapper {

    @Select("SELECT id, first_name, last_name, email, birthday" +
            " FROM customer ORDER BY id")
    List<Customer> findAll();

    @Select("SELECT id, first_name, last_name, email, birthday" +
            " FROM customer WHERE id = #{id}")
    Optional<Customer> findById(Integer id);

    @Insert("INSERT INTO customer(first_name, last_name, email, birthday)" +
            " VALUES(#{firstName}, #{lastName}, #{email}, #{birthday})")
    void insert(Customer customer);
}
