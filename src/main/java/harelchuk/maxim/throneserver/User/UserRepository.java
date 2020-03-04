package harelchuk.maxim.throneserver.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByUuidBytes(byte[] uuid_bytes);

/*    ArrayList<User> findUsersByCreditFalseAndEasyWinningsGreaterThanAndMediumWinningsGreaterThanAndHardWinningsGreaterThanAndMoneyGreaterThan
            (int m, int n, int k, long l);*/


    User getById(int id);

    ArrayList<User> findAllByUniqueNumberAndIdNot(String unique, int id);

}
