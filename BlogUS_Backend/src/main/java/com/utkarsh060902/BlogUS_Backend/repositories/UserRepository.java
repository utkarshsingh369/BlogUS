package com.utkarsh060902.BlogUS_Backend.repositories;

import com.utkarsh060902.BlogUS_Backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


//@Component  //never make component here (proxy package ke class directly autowire ho jaate hain)
public interface UserRepository extends JpaRepository<User,Integer> {

}
