package harelchuk.maxim.throneserver.Friends;


import javax.persistence.*;

@Entity
@Table(name = "friends_confirmed")
public class FriendConfirmed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id_first")
    private int idFirst;

    @Column(name = "user_id_second")
    private int idSecond;

    @Column(name = "create_time")
    private long createTime;

    FriendConfirmed() {
    }

    public FriendConfirmed(int idFirst, int idSecond, long createTime) {
        this.idFirst = idFirst;
        this.idSecond = idSecond;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public int getIdFirst() {
        return idFirst;
    }

    public int getIdSecond() {
        return idSecond;
    }

    public long getCreateTime() {
        return createTime;
    }
}
