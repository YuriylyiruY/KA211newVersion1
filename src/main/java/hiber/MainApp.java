package hiber;

import hiber.config.AppConfig;


import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        try {


            Car car1 = new Car("BMV", 1);
            Car car2 = new Car("AUDI", 2);
            Car car3 = new Car("MB", 3);
            Car car4 = new Car("W", 4);


            userService.deleteUsers();


            User user1 = new User("User1", "Lastname1", "user1@mail.ru", car1);

            User user2 = new User("User2", "Lastname2", "user2@mail.ru", car2);
            User user3 = new User("User3", "Lastname3", "user3@mail.ru", car3);
            User user4 = new User("User4", "Lastname4", "user4@mail.ru", car4);


            userService.add(user1);
            userService.add(user2);
            userService.add(user3);
            userService.add(user4);


            List<User> users = userService.listUsers();
            for (User user : users) {
                System.out.println("information about  user and his car = " + user.toString());
            }
            List<Car> cars = userService.listCars();
            for (Car car : cars) {
                System.out.println("Id = " + car.getIdNewCar());
                System.out.println("Model = " + car.getModelCar());
                System.out.println("Series = " + car.getSeries());
            }
            String model = "BMV";
            Integer series = 1;
            List<User> users7 = userService.findOwner(model, series);
            if (!users7.isEmpty()) {
                for (User user : users7) {
                    System.out.println("information about  user with series and model = " + user.toString());
                }
            } else {
                System.out.println("No users found");
            }


        } finally {

            userService.getSessionFactory().close();
        }

    }
}
