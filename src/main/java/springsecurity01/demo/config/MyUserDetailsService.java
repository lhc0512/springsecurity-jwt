package springsecurity01.demo.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashSet;

@Component
public class MyUserDetailsService implements UserDetailsService,Serializable {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUserDetails myUserDetails = new MyUserDetails();
        myUserDetails.setUsername(username);
        //模拟从数据库取出的密码
        myUserDetails.setPassword(new BCryptPasswordEncoder().encode("12345"));

        //模拟从数据库取出的权限
        HashSet<SimpleGrantedAuthority> set = new HashSet<>();
      //  set.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        set.add(new SimpleGrantedAuthority("ROLE_USER"));

        myUserDetails.setAuthorities(set);
        return myUserDetails;
    }
}
