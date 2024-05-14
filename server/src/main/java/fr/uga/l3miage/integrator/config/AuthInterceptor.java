package fr.uga.l3miage.integrator.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;


@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("Authorization");
        String authorizationTest = request.getHeader("AuthorizationTest");
        if (authorizationTest != null && authorizationTest.startsWith("Test ")) {
            String token = authorizationTest.substring("Test ".length());
            File file = ResourceUtils.getFile("classpath:accessToken.txt");
            String accesToken = new String(Files.readAllBytes(Paths.get(file.getPath())));
            if(token.equals(accesToken)) {
                return true;
            }
        }
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            FirebaseToken decodeToken = null;
            try {
                String token = accessToken.substring("Bearer ".length());
                decodeToken = FirebaseAuth.getInstance().verifyIdToken(token);
            }catch (FirebaseAuthException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            if(decodeToken == null) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            }
            return true;
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        return false;
    }
}
