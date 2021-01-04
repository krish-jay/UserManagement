package com.epizy.krishjay.service;

import com.epizy.krishjay.model.Users;
import com.epizy.krishjay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Boolean saveUser(Users u){
        Users user=userRepository.save(u);
        if(user != null)
            return true;
        else
            return false;
    }
    public List<Users> listall(){
        return userRepository.findAll(Sort.by("id").descending());
    }
    public Boolean deleteit(long id){
        userRepository.deleteById(id);
        return true;
    }
}
