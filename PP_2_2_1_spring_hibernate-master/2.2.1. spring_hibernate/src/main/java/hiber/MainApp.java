package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      User user4 = new User("User4", "Lastname4", "user4@mail.ru");

      Car ca1 = new Car("Car1", 1);
      Car ca2 = new Car("Car2", 2);
      Car ca3 = new Car("Car3", 3);
      Car ca4 = new Car("Car4", 4);

      userService.add(user1.setCar(ca1).setUser(user1));
      userService.add(user2.setCar(ca2).setUser(user2));
      userService.add(user3.setCar(ca3).setUser(user3));
      userService.add(user4.setCar(ca4).setUser(user4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }
      for (User user : userService.listUsers()) {
         System.out.println(user + " " + user.getCar());
      }
      User user = userService.getUserByModelAndSeries("Car3", 3);
      System.out.println(user.toString());





      context.close();
   }
}
