package springsecurity01.demo.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import springsecurity01.demo.bean.AjaxResponseBody;
import springsecurity01.demo.config.MyUserDetails;
import springsecurity01.demo.utils.JwtTokenUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();

        responseBody.setStatus("00");
        responseBody.setMsg("Login Success!");

        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();


        String jwtToken = JwtTokenUtil.generateToken(myUserDetails.getUsername(), 300);
        responseBody.setJwtToken(jwtToken);

        response.getWriter().write(JSON.toJSONString(responseBody));
    }
}
