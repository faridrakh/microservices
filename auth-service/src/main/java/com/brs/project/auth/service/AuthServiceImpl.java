package com.brs.project.auth.service;

import com.brs.project.config.CommonConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    @Qualifier("eurekaClient")
    private EurekaClient eurekaClient;

//    @Override
//    public Map<String,Object> loadUserDetailFromUserName(String userName, String password) {
//        User user = new User();
//        Application app = eurekaClient.getApplication("user-service");
//        //InstanceInfo instanceInfo = app.getInstances().get(0);
//        String uri = "http://localhost:8200/user-for-auth/{userName}";
//        Map<String, String> params = new HashMap<>();
//        params.put("userName", userName);
//
//        RestTemplate rt = new RestTemplate();
//        Map<String,Object> result = rt.getForObject(uri, Map.class, params);
//        HashMap<String,Object> tempUser = (HashMap<String, Object>) result.get("User");
//        HashMap<String,Object> userGroup = (HashMap<String, Object>) result.get("UserGroup");
//        user.setUserName(tempUser.get("userName").toString());
//        user.setEmail(tempUser.get("email").toString());
//        user.setPassword(tempUser.get("password").toString());
//        user.setPassword(tempUser.get("password").toString());
//        user.setUserGrpCd(userGroup.get("role").toString());
//
//        if(userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
//
//        }
//        return null;
//    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Application app = eurekaClient.getApplication("user-service");
        //InstanceInfo instanceInfo = app.getInstances().get(0);
        //String uri = "http://"+instanceInfo.getIPAddr()+":"+instanceInfo.getPort()+"/user-for-auth/{userName}";
        String uri = commonConfig.getUserEndpoint()+"/user-for-auth/{userName}";
        Map<String, String> params = new HashMap<>();
        params.put("userName", userName);

        RestTemplate rt = new RestTemplate();
        Map<String,Object> result = rt.getForObject(uri, Map.class, params);
        String tempUserName = result.get("userName").toString();
        String tempPassword = result.get("password").toString();
        String tempRole = result.get("userRole").toString();

        if(userName.equals(tempUserName)) {
            List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                    .commaSeparatedStringToAuthorityList("ROLE_" + tempRole);
            return new User(tempUserName,encoder.encode(tempPassword),grantedAuthorities);
        }

        throw new UsernameNotFoundException("Username: " + userName + " not found");
    }
}
