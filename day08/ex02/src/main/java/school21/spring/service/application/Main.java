package school21.spring.service.application;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.ApplicationConfig;
import school21.spring.service.services.UsersService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UsersService userService = context.getBean("usersService", UsersService.class);
        String tempPassword = userService.signUp("newEmail@mail.ru");
        System.out.println(tempPassword);

        context.close();
    }
}
