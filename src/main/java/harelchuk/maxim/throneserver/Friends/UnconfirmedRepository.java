package harelchuk.maxim.throneserver.Friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UnconfirmedRepository extends JpaRepository<FriendUnconfirmed, Integer> {
    //ArrayList<FriendUnconfirmed> findAllByIdToOrIdFrom(int id1, int id2);
    ArrayList<FriendUnconfirmed> findAllByIdTo(int idTo);

    FriendUnconfirmed getByIdFromAndIdTo(int idFrom, int idTo);

    ArrayList<FriendUnconfirmed> findAllByIdFromAndIdTo(int idFrom, int idTo);
}
