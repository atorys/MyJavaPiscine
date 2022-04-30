package school21.spring.service.application;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println(usersRepository.findAll());

        System.out.println(usersRepository.findByEmail("hdfe@student.21-school.ru") );
        System.out.println(usersRepository.findByEmail("atory@student.21-school.ru") );
        System.out.println(usersRepository.findById(3L));
        System.out.println();

        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);
        System.out.println(usersRepository.findAll() );

        System.out.println(usersRepository.findByEmail("hdfe@student.21-school.ru") );
        System.out.println(usersRepository.findByEmail("atory@student.21-school.ru") );
        System.out.println(usersRepository.findById(3L));
        System.out.println();

        User user = new User(999L, "new_email.ru");
        usersRepository.save(user);
        System.out.println(usersRepository.findByEmail(user.getEmail()));
    }
}
