package school21.spring.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

import javax.sql.DataSource;

@Component("usersService")
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("jdbcTemplate")UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        String password = generatePassword();
        usersRepository.save(new User(4, email, password));
        return password;
    }

    public String generatePassword() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            double v = Math.random() * 4453 % 128;
            if ((int)v >= 40 && (int)v <= 120) {
                builder.append((char)v);
            }
        }

        return builder.toString();
    }
}
