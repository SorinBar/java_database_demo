public class User {
    public long id;
    public String email;
    public String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(long id, String email, String password) {
        this(email, password);
        this.id = id;
    }

    @Override
    public String toString() {
        return "User: {: id: %s, email: %s, password: %s}".formatted(id, email, password);

    }
}
