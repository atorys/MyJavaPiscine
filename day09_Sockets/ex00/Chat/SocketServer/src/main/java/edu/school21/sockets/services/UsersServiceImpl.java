package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("usersService")
public class UsersServiceImpl implements UsersService {

    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(@Qualifier("usersRepository")UsersRepository usersRepository, @Qualifier("encoder")PasswordEncoder encoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = encoder;
    }

    public static class AlreadyAuthenticatedException extends RuntimeException {
        public AlreadyAuthenticatedException() {
            System.out.println("This user is already authenticated");
        }
    }

    @Override
    public boolean signUp(String login, String password) {
        Optional<User> user = usersRepository.findByLogin(login);
        if (user.isPresent()) {
            throw new AlreadyAuthenticatedException();
        } else {
            usersRepository.save(new User(login, passwordEncoder.encode(password)));
        }
        return true;
    }
}
