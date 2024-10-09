package hiber;

import hiber.config.AppConfig;


import hiber.model.Car;
import hiber.model.User;
import hiber.service.CrService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        CrService carService = context.getBean(CrService.class);
        try {

            User user1 = new User("User1", "Lastname1", "user1@mail.ru");

            User user2 = new User("User2", "Lastname2", "user2@mail.ru");
            User user3 = new User("User3", "Lastname3", "user3@mail.ru");
            User user4 = new User("User4", "Lastname4", "user4@mail.ru");

            Car car1 = new Car(user1, "BMV", 1);
            Car car2 = new Car(user2, "AUDI", 1);
            Car car3 = new Car(user3, "MB", 3);
            Car car4 = new Car(user4, "BMV", 2);


            userService.deleteUsers();
            carService.deleteCars();

            user1.setCar(car1);
            user2.setCar(car2);
            user3.setCar(car3);
            user4.setCar(car4);


            userService.add(user1);
            userService.add(user2);
            userService.add(user3);
            userService.add(user4);


            List<User> users = userService.listUsers();
            for (User user : users) {
                System.out.println("information about  user and his car = " + user.toString());
            }
            List<Car> cars = carService.listCars();
            for (Car car : cars) {
                System.out.println("Id = " + car.getId());
                System.out.println("Model = " + car.getModel_car());
                System.out.println("Series = " + car.getSeries());
            }
            String model = "AUDI";
            Integer series = 1;
            User users7 = userService.findOwner(model, series);

            if (!(users7 == null)) {
                System.out.println("information about  user with series and model = " + users7);

            } else {
                System.out.println("No users found");
            }


        } finally {

            context.close();
        }

    }
}
