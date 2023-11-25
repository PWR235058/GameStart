package com.game.start.GameStart.REST;

import com.game.start.GameStart.entity.Session;
import com.game.start.GameStart.jpa.SessionRepository;
import com.game.start.GameStart.jpa.UserRepository;
import com.game.start.GameStart.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

@RestController
public class Login {

    @Autowired
    private UserRepository users;
    @Autowired
    private SessionRepository tokens;

    /**
     * endpoint for logging in
     * @param map
     * @return ok:false if error and ok:true,data:{token:"randomchars"} if succes, the token is also saved in session cookies
     */
    @PostMapping("/api/login")
    String login(@RequestBody Map<String,String> map) throws NoSuchAlgorithmException, InvalidKeySpecException {
        User u = null;
        if(!map.containsKey("password"))return "{\"ok\":false,\"data\":{\"message\":\"missing password\"}}";
        if (map.containsKey("login")) {
            u = users.findByLogin(map.get("login"));
            if (u == null)
                u = users.findByEmail(map.get("login"));
        }
        else if (map.containsKey("email")) {
            u = users.findByEmail(map.get("email"));
        }
        if (u == null) return "{\"ok\":false,\"data\":{\"message\":\"missing username or email\"}}";
        if(!u.getPassword().equals(passhash(u.getSalt(),map.get("password"))))return "{\"ok\":false,\"data\":{\"message\":\"invalid password\"}}";
        String token = randomString(40);
        while (tokens.findByToken(token) != null) {
            token = randomString(40);
        }
        tokens.save(new Session(token, u));

        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute("token", token);

        return "{\"ok\":true,\"data\":{\"token\":\""+token+"\"}}";
    }

    /**
     * endpoint for logging out
     * @param key
     */
    @Transactional
    @DeleteMapping("/api/logout")
    public void logout(@RequestParam(required = false) String key) {
        if(key==null){
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession();
            key = (String) session.getAttribute("token");
        }
        if(key==null)return;
        if(tokens.findByToken(key)!=null)
            tokens.removeByToken(key);
    }

    /**
     * endpoint for registering a new user
     * todo: check if to register a normal user or worker
     * @param map
     * @return json with ok:true if succeded or ok:false,data:{message:"error text"} if failed
     */
    @PostMapping("/api/register")
    String register(@RequestBody Map<String,String> map) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(map.isEmpty())return "{\"ok\":false,\"data\":{\"message\":\"Hello there\"}}";
        if(!map.containsKey("login"))return "{\"ok\":false,\"data\":{\"message\":\"missing username\"}}";
        if(!map.containsKey("password"))return "{\"ok\":false,\"data\":{\"message\":\"missing password\"}}";
        if(!map.containsKey("email"))return "{\"ok\":false,\"data\":{\"message\":\"missing email\"}}";
        if(users.findByLogin(map.get("login"))!=null)return "{\"ok\":false,\"data\":{\"message\":\"username is already taken\"}}";
        if(users.findByEmail(map.get("email"))!=null)return "{\"ok\":false,\"data\":{\"message\":\"email is already in use\"}}";
        if(map.get("password").length()<3)return "{\"ok\":false,\"data\":{\"message\":\"password is too short\"}}";
        if(map.get("login").length()<3)return "{\"ok\":false,\"data\":{\"message\":\"username is too short\"}}";
        if(map.get("email").length()<3)return "{\"ok\":false,\"data\":{\"message\":\"email is too short\"}}";
        if(!map.get("email").contains("@"))return "{\"ok\":false,\"data\":{\"message\":\"invalid email\"}}";
        if(!map.get("email").contains("."))return "{\"ok\":false,\"data\":{\"message\":\"invalid email\"}}";
        byte[] salt=new byte[16];
        for(int i=0;i<16;i++)
            salt[i]= ((byte) (Math.random()*256));
        User u =new User(0L,"anonymous","unset",map.get("login"),map.get("email"),passhash(salt,map.get("passwprd")),salt,null,null);
        /*
            todo: imie nazwisko
            czy klientem lub czy pracownikiem
         */
        if(map.containsKey("clientid"))
            u.setClient(null);
        if(map.containsKey("workerid")||map.containsKey("shopid"))
            u.setWorker(null);
        users.save(u);
        return "{\"ok\":true}";
    }

    /**
     * key = null
     * funkcja do użycia, gdy chcemy sprawdzić czy jakikolwiek użytkownik jest zalogowany
    */
    boolean checkfast(String key){
        if(key==null){
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpServletRequest request = attr.getRequest();
            key = (String) request.getSession().getAttribute("token");
            if(key == null){
                key = request.getParameter("key");
            }
        }
        if(key==null)return false;
        return tokens.findByToken(key) != null;
    }

    /**
     * użycie: /testlogin?key=token
     *  endpoint for checking if you are still logged in
     * @param key token given during login, it will load from cookies if null
     * @return json with ok:true/false if user is logged or not
     */
    @GetMapping("/api/testlogin")
    String check(@RequestParam(required = false) String key) {
        if(checkfast(key)){
            return "{\"ok\":true}";
        }
        return "{\"ok\":false}";
    }




    private String randomString(final int i) {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(i);
        for (int j = 0; j < i; j++) {
            sb.append(AB.charAt((int)(Math.random() * AB.length())));
        }
        return sb.toString();
    }
    private String passhash(byte[] salt, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 600000, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = factory.generateSecret(spec).getEncoded();
        return Arrays.toString(hash);
    }
}
