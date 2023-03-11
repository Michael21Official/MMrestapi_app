package Michael21Official.GitHub.pl.MMrestapi_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        DatabaseClient databaseClient = new DatabaseClient();

        databaseClient.clearDatabase();

        System.out.println("Wywołujemy metodę getUsers()");
        System.out.println(databaseClient.getUsers());

        System.out.println();
        System.out.println("-----------------");
        System.out.println("Tworzymy użytkowników kamilbrzezinski i kamilb");
        System.out.println("Wywołujemy metodę addUser() dla obu tych użytkowników");
        System.out.println("Wywołujemy metodę getUsers()");
        User user1 = new User("michalmatsalak", 31, "Michał Matsalak");
        User user2 = new User("michalm", 31, "Michal M.");
        databaseClient.addUser(user1);
        databaseClient.addUser(user2);
        System.out.println(databaseClient.getUsers());

        System.out.println();
        System.out.println("-----------------");
        System.out.println("Wywołujemy metodę getUser(michalmatsalak)");
        System.out.println(databaseClient.getUser("michalmatsalak"));

        System.out.println();
        System.out.println("-----------------");
        System.out.println("Wywołujemy metody deleteUser(michalmatsalak)");
        System.out.println("Wywołujemy metodę getUsers()");
        databaseClient.deleteUser("michalmatsalak");
        System.out.println(databaseClient.getUsers());


        System.out.println();
        System.out.println("-----------------");
        System.out.println("Wywołujemy metodę getPosts()");
        System.out.println(databaseClient.getPosts());

        System.out.println();
        System.out.println("-----------------");
        System.out.println("Tworzymy dwa posty");
        System.out.println("Wywołujemy metodę addPost() dla obu tych postów");
        System.out.println("Wywołujemy metodę getPosts()");
        Post post1 = new Post("dominik", "Super film, daję łapkę w górę");
        Post post2 = new Post("mariusz", "Mega kanał, daję suba");
        databaseClient.addPost(post1);
        databaseClient.addPost(post2);
        System.out.println(databaseClient.getPosts());

        System.out.println();
        System.out.println("-----------------");
        System.out.println("Wywołujemy metodę deletePost(1)");
        System.out.println("Wywołujemy metodę getPosts()");
        databaseClient.deletePost(1);
        System.out.println(databaseClient.getPosts());

    }
}