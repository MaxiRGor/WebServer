package harelchuk.maxim.throneserver.User;

import javax.persistence.*;

@Entity
@Table(name = "users_emails")
public class UserEmail {

    public UserEmail() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "email")
    private String email;

    public UserEmail(int userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }
}
