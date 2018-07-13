package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ClientService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @RequestMapping("login")
    @ResponseBody
    public Map<String,Object> login(HttpSession session, String username, String password,String requestUrl){

        Map<String,Object> map=new HashMap<>();

        if(requestUrl==null){
            requestUrl="http://localhost:8080";
        }

        if(clientService.login(username, password)) {

            session.setAttribute("username", username);
            map.put("res",true);
            map.put("requestUrl",requestUrl);

        }else {
            map.put("res",false);
        }

        return map;


    }

    @RequestMapping("logout")
    public String logout(HttpSession session){

        session.invalidate();
        return "redirect:http://localhost:8080/";
    }

    @RequestMapping("register")
    @ResponseBody
    public Map<String,Object> register(HttpSession session,String username,String password,String email,String requestUrl){


        Map<String,Object> map=new HashMap<>();

        if(requestUrl==null){
            requestUrl="http://localhost:8080";
        }

        if(clientService.register(username, password, email)) {

            session.setAttribute("username", username);
            map.put("res",true);
            map.put("requestUrl",requestUrl);

        }else {
            map.put("res",false);
        }

        return map;

    }
}
