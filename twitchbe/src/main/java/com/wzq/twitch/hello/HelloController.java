package com.wzq.twitch.hello;


import net.datafaker.Faker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {




    @GetMapping("/hello")
    public Person sayHello(@RequestParam(required = false) String name) {
        Faker faker = new Faker();
        if (name == null) {
            name = "Guest";
        }
        String company = faker.company().name();
        String street = faker.address().streetAddress();
        String city = faker.address().city();
        String state = faker.address().state();
        String bookTitle = faker.book().title();
        String bookAuthor = faker.book().author();
        return new Person(
                name,
                company,
                new Address(street, city, state, "US")

        );

    }


}
