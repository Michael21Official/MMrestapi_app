package Michael21Official.GitHub.pl.MMrestapi_app;

import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseClient {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    public String getUsers() throws JsonProcessingException {
        List users = jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
        return objectMapper.writeValueAsString(users);
    }

    public String getUser(@PathVariable("username") String username) throws JsonProcessingException {
        List users = jdbcTemplate.query("SELECT * FROM users WHERE username = :username",
                new MapSqlParameterSource()
                        .addValue("username", username), new BeanPropertyRowMapper<>(User.class));

        return objectMapper.writeValueAsString(users.get(0));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody User user) {
        String sql = "INSERT into USERS(username, age, name) VALUES(:username, :age, :name)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", user.getUsername())
                .addValue("age", user.getAge())
                .addValue("name", user.getName());

        jdbcTemplate.update(sql, parameters);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username) {
        String sql = "DELETE FROM users where username = :username";
        MapSqlParameterSource parameters = new MapSqlParameterSource().addValue("username", username);

        jdbcTemplate.update(sql, parameters);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    public String getPosts() throws JsonProcessingException {
        List posts = jdbcTemplate.query("SELECT * FROM posts", new BeanPropertyRowMapper<>(Post.class));

        return objectMapper.writeValueAsString(posts);
    }

    public String getPost(@PathVariable("postId") int postId) throws JsonProcessingException {
        List posts = jdbcTemplate.query("SELECT * FROM posts WHERE id = :id",
                new MapSqlParameterSource()
                        .addValue("id", postId), new BeanPropertyRowMapper<>(Post.class));

        return objectMapper.writeValueAsString(posts);
    }

    @PostMapping("/posts")
    public ResponseEntity addPost(@RequestBody Post post) {
        String sql = "INSERT INTO posts(username, content) VALUES(:username, :content)";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("username", post.getUsername())
                .addValue("content", post.getContent());

        jdbcTemplate.update(sql, parameters);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public void deletePost(@PathVariable("postId") int postId) {
        String sql = "DELETE from posts WHERE id = :id";
        MapSqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("id", postId);

        jdbcTemplate.update(sql, parameters);
    }
}
