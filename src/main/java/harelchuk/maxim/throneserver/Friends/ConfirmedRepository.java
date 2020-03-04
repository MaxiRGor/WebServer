package harelchuk.maxim.throneserver.Friends;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ConfirmedRepository extends JpaRepository<FriendConfirmed, Integer> {
    ArrayList<FriendConfirmed> findAllByIdFirstOrIdSecond(int id1, int id2);
}
