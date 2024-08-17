public class User {
    public String id;
    public String email;
    public String password;

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User: {: id: %s, email: %s, password: %s}".formatted(id, email, password);

    }
}
