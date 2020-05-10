package lt.bit.addressbookspringdata.config;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class MyUserDetails implements UserDetailsService {
    private final static Log log = LogFactory.getLog(MyUserDetails.class);
    @Autowired
    private PasswordEncoder pe;
//    @Autowired
//    private UsersDAO usersDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails u = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                ArrayList<GrantedAuthority> a = new ArrayList<>();
                a.add(new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "ADMIN";
                    }
                });
                return a;
            }
            @Override
            public String getPassword() {
                return "$2a$10$RLN8PgvVi/8l9ZdCAQkSweGROuL08DwDGSblDWxs53620NRjzsMVa";
            }
            @Override
            public String getUsername() {
                return "admin";
            }
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }
            @Override
            public boolean isAccountNonLocked() {
                return true;
            }
            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }
            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        return u;
//        List<Users> users = usersDAO.findByName(username);
//        if(users.size() > 0) {
//            return users.get(0);
//        } else {
//            throw new UsernameNotFoundException(username);
//        }
    }
}