package com.epizy.krishjay.controller;

import com.epizy.krishjay.model.AjaxResponse;
import com.epizy.krishjay.model.Users;

import com.epizy.krishjay.repository.UpdateRepo;
import com.epizy.krishjay.repository.UserRepository;
import com.epizy.krishjay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UpdateRepo userRepository;

    @PostMapping("/addUser")
    public ResponseEntity<?> SaveUser(@RequestBody Users u){

        Boolean ans=userService.saveUser(u);
        return ResponseEntity.ok(ans);
    }
    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@RequestBody Users u){
        userRepository.save(u);
        return ResponseEntity.ok(true);
    }
    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestBody Users u){
        userService.deleteit(u.getId());
        return ResponseEntity.ok(true);
    }
    @RequestMapping( value = "/")
    public String viewusers(Model model){

        List<Users> user=userService.listall();
        Iterator iter = user.iterator();
        Users us= (Users) iter.next();

        model.addAttribute("listid",us.getId()+1);
        model.addAttribute("listallusers",userService.listall());
        System.out.println("here it is ++++++++++++ "+userService.listall());
        return "index";
    }
    @RequestMapping("/viewUser/{id}")
    public ResponseEntity<?> viewRequest(@PathVariable long id, Model model) {
        AjaxResponse result=new AjaxResponse();
        Optional<Users> con1=userRepository.findById(id);
        Users con=con1.get();

        String as="";

        as+=" <div class=\"modal-header\">\n" +
                "\n" +
                "                        <h4 class=\"modal-title\">Edit User</h4>\n" +
                "                    </div>\n" +
                "                    <div class=\"modal-body\" >\n" +
                "                        <form  method=\"post\"  name=\"user-form\">\n" +
                "                            <div class=\"form-group\"><label class=\"small mb-1\" for=\"cname\">User Name :</label><input class=\"form-control\" name=\"uname\" id=\"uname\"  type=\"text\" value='"+con.getName() +"' placeholder=\"Enter User Name\" /></div>\n" +
                "                            <div class=\"form-group\"><label class=\"small mb-1\" for=\"url\">Mobile :</label><input class=\"form-control\" name=\"umob\"  id=\"umob\" type=\"text\" value='"+con.getMobile()+"' placeholder=\"Enter Mobile Number\" /></div>\n" +
                "                            <div class=\"form-group\"><label class=\"small mb-1\" for=\"cname\">Plan :</label><input class=\"form-control\" name=\"uplan\" id=\"uplan\"  type=\"text\" value='"+con.getPlan() +"' placeholder=\"Enter Plan\" /></div>\n" +
                "                            <div class=\"form-group\"><label class=\"small mb-1\" for=\"url\">Status ;</label><input class=\"form-control\" name=\"ustat\"  id=\"ustat\" type=\"text\" value='"+con.getStatus()+"' placeholder=\"Enter Status\" /></div>\n" +
                "\n" +
                "\n" +
                "                        </form>\n" +
                "                    </div>\n" +
                "                    <div id='blogupdate' class=\"modal-footer\">\n" +
                "                        <a href=\"#\" id='btnupdate' onclick='demo("+con.getId()+")' data-id='"+con.getId()+"' class=\"btn btn-dark\">Update</a>\n" +
                "                        <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n" +
                "                    </div>";
        result.setMsg(as);
        return  ResponseEntity.ok(result);
    }

}

