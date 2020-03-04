package harelchuk.maxim.throneserver.Friends;

import javax.persistence.*;

@Entity
@Table(name = "friends_unconfirmed")
public class FriendUnconfirmed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id_first")
    private int idFrom;

    @Column(name = "user_id_second")
    private int idTo;

    @Column(name = "create_time")
    private long createTime;

    FriendUnconfirmed() {
    }

    public FriendUnconfirmed(int idFrom, int idTo, long createTime) {
        this.idFrom = idFrom;
        this.idTo = idTo;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public long getCreateTime() {
        return createTime;
    }
}
