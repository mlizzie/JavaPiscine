package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.Optional;

@Component("usersService")
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("jdbcStandard")UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String name, String password) {
        usersRepository.save(new User(null, name, password));
        return password;
    }

    @Override
    public Boolean signIn(String name, String password){
        Optional<User> optional;
        optional = usersRepository.findByName(name);
        if (optional.isPresent()) {
            User user = optional.get();
            if (user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
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
