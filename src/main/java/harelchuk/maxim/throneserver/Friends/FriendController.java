package harelchuk.maxim.throneserver.Friends;

import harelchuk.maxim.throneserver.User.User;
import harelchuk.maxim.throneserver.User.UserRepository;
import harelchuk.maxim.throneserver.User.UsersMultiRating;
import harelchuk.maxim.throneserver.User.UsersMultiRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


@RestController
@RequestMapping("/friends")
public class FriendController {

    private UserRepository userRepository;
    private UnconfirmedRepository unconfirmedRepository;
    private ConfirmedRepository confirmedRepository;
    private UsersMultiRatingRepository usersMultiRatingRepository;

    @Autowired
    FriendController(UserRepository userRepository,
                     UnconfirmedRepository unconfirmedRepository,
                     ConfirmedRepository confirmedRepository,
                     UsersMultiRatingRepository usersMultiRatingRepository) {
        this.userRepository = userRepository;
        this.unconfirmedRepository = unconfirmedRepository;
        this.confirmedRepository = confirmedRepository;
        this.usersMultiRatingRepository = usersMultiRatingRepository;
    }

    @GetMapping("/find/{uuid}/{unique}")      //now - everybody but not himself
    public @ResponseBody
    ArrayList<Friend> findFriends(@PathVariable String uuid, @PathVariable String unique) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        ArrayList<Friend> newFriends = new ArrayList<>();
        ArrayList<User> usersFound = userRepository.findAllByUniqueNumberAndIdNot(unique, myId);
        int limit = usersFound.size();
        if (usersFound.size() > 5) limit = 5;
        for (int i = 0; i < limit; i++) {
            UsersMultiRating usersMultiRating = usersMultiRatingRepository.findDistinctByIdUser(usersFound.get(i).getId());
            Friend friend = new Friend(usersFound.get(i).getId(), usersFound.get(i).getCurrentIcon(), usersFound.get(i).getName(), usersFound.get(i).getMoney(),
                    usersFound.get(i).getEasyGames(), usersFound.get(i).getEasyWinnings(), usersFound.get(i).getMediumGames(), usersFound.get(i).getMediumWinnings(),
                    usersFound.get(i).getHardGames(), usersFound.get(i).getHardWinnings(),
                    usersMultiRating.getRank(), usersMultiRating.getCrystals(), usersMultiRating.getGames(), usersMultiRating.getWinnings());
            newFriends.add(friend);
        }
        return newFriends;
    }

    //@PutMapping("/decline/{uuid}/{available_friend_id}")
    @GetMapping("/decline/{uuid}/{available_friend_id}")
    public boolean declineAvailableFriend(@PathVariable String uuid,
                                          @PathVariable int available_friend_id) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        ArrayList<FriendUnconfirmed> friendsUnconfirmeds = unconfirmedRepository.findAllByIdFromAndIdTo(available_friend_id, myId);
        unconfirmedRepository.deleteAll(friendsUnconfirmeds);
        return true;
    }

    //@PutMapping("/add/{uuid}/{available_friend_id}")
    @GetMapping("/add/{uuid}/{available_friend_id}")
    public boolean addAvailableFriend(@PathVariable String uuid,
                                      @PathVariable int available_friend_id) {
        long createTime = new Date().getTime();
        int idFrom = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        FriendUnconfirmed friendsUnconfirmed = new FriendUnconfirmed(idFrom, available_friend_id, createTime);
        unconfirmedRepository.save(friendsUnconfirmed);
        return true;
    }

    @GetMapping("/get/unconfirmed/{uuid}")
    public @ResponseBody
    ArrayList<Friend> getUnconfirmed(@PathVariable String uuid) {
        int id_to = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        ArrayList<FriendUnconfirmed> friendUnconfirmeds = unconfirmedRepository.findAllByIdTo(id_to);
        ArrayList<Friend> friends = new ArrayList<>();
        if (friendUnconfirmeds.size() > 0) {
            for (FriendUnconfirmed friendsUnconfirmed : friendUnconfirmeds) {
                int idSecond = friendsUnconfirmed.getIdFrom();
                User user = userRepository.getById(idSecond);
                UsersMultiRating usersMultiRating = usersMultiRatingRepository.findDistinctByIdUser(idSecond);
                Friend friend = new Friend(idSecond, user.getCurrentIcon(), user.getName(), user.getMoney(),
                        user.getEasyGames(), user.getEasyWinnings(), user.getMediumGames(), user.getMediumWinnings(),
                        user.getHardGames(), user.getHardWinnings(),
                        usersMultiRating.getRank(), usersMultiRating.getCrystals(), usersMultiRating.getGames(), usersMultiRating.getWinnings());
                friends.add(friend);
            }
        }
        return friends;
    }

    //@PutMapping("/accept_friendship/{uuid}/{friend_id}")
    @GetMapping("/accept_friendship/{uuid}/{friend_id}")
    public boolean acceptFriendship(@PathVariable String uuid,
                                    @PathVariable int friend_id) {
        long createTime = new Date().getTime();
        int idTo = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        FriendConfirmed friendConfirmed = new FriendConfirmed(idTo, friend_id, createTime);
        confirmedRepository.save(friendConfirmed);
        FriendUnconfirmed friendUnconfirmed = unconfirmedRepository.getByIdFromAndIdTo(friend_id, idTo);
        unconfirmedRepository.delete(friendUnconfirmed);
        return true;
    }


    @GetMapping("/get/confirmed/{uuid}")
    public @ResponseBody
    ArrayList<Friend> getFriendList(@PathVariable String uuid) {
        int myId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        ArrayList<FriendConfirmed> friendsConfirmeds = confirmedRepository.findAllByIdFirstOrIdSecond(myId, myId);
        ArrayList<Friend> friends = new ArrayList<>();
        if (friendsConfirmeds.size() > 0) {
            for (FriendConfirmed friendConfirmed : friendsConfirmeds) {
                int idFriend = friendConfirmed.getIdFirst();
                if (idFriend == myId) idFriend = friendConfirmed.getIdSecond();
                User user = userRepository.getById(idFriend);
                UsersMultiRating usersMultiRating = usersMultiRatingRepository.findDistinctByIdUser(idFriend);
                Friend friend = new Friend(idFriend, user.getCurrentIcon(), user.getName(), user.getMoney(),
                        user.getEasyGames(), user.getEasyWinnings(), user.getMediumGames(), user.getMediumWinnings(),
                        user.getHardGames(), user.getHardWinnings(),
                        usersMultiRating.getRank(), usersMultiRating.getCrystals(), usersMultiRating.getGames(), usersMultiRating.getWinnings());
                friends.add(friend);
            }
        }
        return friends;
    }


    private byte[] getBytesFromUUID(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }
}




