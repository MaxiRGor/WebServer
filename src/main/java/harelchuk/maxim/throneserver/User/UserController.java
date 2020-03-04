package harelchuk.maxim.throneserver.User;

import harelchuk.maxim.throneserver.GameVariables.GameVariablesRepository;
import harelchuk.maxim.throneserver.Leaderboard.LeaderboardRepository;
import harelchuk.maxim.throneserver.Leaderboard.LeaderboardUser;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private LeaderboardRepository leaderboardRepository;
    private UserSinglePointsRepository userSinglePointsRepository;
    private UsersMultiRatingRepository usersMultiRatingRepository;
    private UserIconPurchasesRepository userIconPurchasesRepository;
    private UserSkinPurchasesRepository userSkinPurchasesRepository;
    private GameVariablesRepository gameVariablesRepository;
    private UserEmailRepository userEmailRepository;

    @Autowired
    UserController(UserRepository userRepository,
                   LeaderboardRepository leaderboardRepository,
                   UserSinglePointsRepository userSinglePointsRepository,
                   UsersMultiRatingRepository usersMultiRatingRepository,
                   UserIconPurchasesRepository userIconPurchasesRepository,
                   UserSkinPurchasesRepository userSkinPurchasesRepository,
                   GameVariablesRepository gameVariablesRepository,
                   UserEmailRepository userEmailRepository) {
        this.userRepository = userRepository;
        this.leaderboardRepository = leaderboardRepository;
        this.userSinglePointsRepository = userSinglePointsRepository;
        this.usersMultiRatingRepository = usersMultiRatingRepository;
        this.userIconPurchasesRepository = userIconPurchasesRepository;
        this.userSkinPurchasesRepository = userSkinPurchasesRepository;
        this.gameVariablesRepository = gameVariablesRepository;
        this.userEmailRepository = userEmailRepository;
    }
/*

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getUsers() {
        return userRepository.findAll();
    }
*/


    @GetMapping("/create/{theme_number}")
    public @ResponseBody
    AllUserInfo createUser(@PathVariable int theme_number) {
        byte[] uuid_bytes = getBytesFromUUID(UUID.randomUUID());
        String uuid = getUUIDFromBytes(uuid_bytes).toString();
        String uniqueNumber = RandomStringUtils.random(4, true, true);
        User user = new User(uniqueNumber, uuid_bytes, uuid, theme_number);
        userRepository.save(user);
        int id = userRepository.getByUuidBytes(uuid_bytes).getId();
        UserSinglePoints userSinglePoints = new UserSinglePoints(id);
        userSinglePointsRepository.save(userSinglePoints);
        UsersMultiRating usersMultiRating = new UsersMultiRating(id);
        usersMultiRatingRepository.save(usersMultiRating);
        UserIconsPurchases userIconsPurchases = new UserIconsPurchases(id);
        userIconPurchasesRepository.save(userIconsPurchases);
        UserSkinsPurchases userSkinsPurchases = new UserSkinsPurchases(id, theme_number);
        userSkinPurchasesRepository.save(userSkinsPurchases);
        return getUserByUUID(uuid);
    }


    @GetMapping("/get/{uuid}")
    public @ResponseBody
    AllUserInfo getUserByUUID(@PathVariable("uuid") String uuid) {
        User user = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        if (user == null) return createUser(0);
        else {
            int userId = user.getId();
            UsersMultiRating multiRating = usersMultiRatingRepository.findDistinctByIdUser(userId);
            UserSinglePoints singlePoints = userSinglePointsRepository.getUserSinglePointsByUserId(userId);
            UserSkinsPurchases skinsPurchases = userSkinPurchasesRepository.findDistinctByUserId(userId);
            UserIconsPurchases iconsPurchases = userIconPurchasesRepository.findDistinctByUserId(userId);
            return new AllUserInfo(user, multiRating, singlePoints, skinsPurchases, iconsPurchases);
        }
    }

    @GetMapping("/find/{uuid}/email/{email}")
    public @ResponseBody
    int getEmailIdOrAddNewEmail(@PathVariable("uuid") String uuid, @PathVariable("email") String email) {
        int id;
        UserEmail userEmail = userEmailRepository.findDistinctByEmail(email);
        if (userEmail == null) {
            id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
            userEmail = new UserEmail(id, email);
            userEmailRepository.save(userEmail);
        } else {
            id = userEmail.getUserId();
        }
        return id;
    }

    @GetMapping("/replace/{uuid}/email/{email}")
    public @ResponseBody
    AllUserInfo replaceUserByEmail(@PathVariable("uuid") String uuid, @PathVariable("email") String email) {
        UserEmail userEmail = userEmailRepository.findDistinctByEmail(email);
        int id = userEmail.getUserId();
        User user = userRepository.getById(id);
        UsersMultiRating multiRating = usersMultiRatingRepository.findDistinctByIdUser(id);
        UserSinglePoints singlePoints = userSinglePointsRepository.getUserSinglePointsByUserId(id);
        UserSkinsPurchases skinsPurchases = userSkinPurchasesRepository.findDistinctByUserId(id);
        UserIconsPurchases iconsPurchases = userIconPurchasesRepository.findDistinctByUserId(id);
        return new AllUserInfo(user, multiRating, singlePoints, skinsPurchases, iconsPurchases);
    }


    /*@GetMapping("/insert/{uuid}/email/{email}")
    public @ResponseBody
    AllUserInfo getUserByEmail(@PathVariable("uuid") String uuid, @PathVariable("email") String email) {
        int id;
        UserEmail userEmail = userEmailRepository.findDistinctByEmail(email);
        if (userEmail == null) {
            id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
            userEmail = new UserEmail(id,email);
            userEmailRepository.save(userEmail);
        } else{
            id = userEmail.getUserId();
        }

        User user = userRepository.getById(id);
        UsersMultiRating multiRating = usersMultiRatingRepository.findDistinctByIdUser(id);
        UserSinglePoints singlePoints = userSinglePointsRepository.getUserSinglePointsByUserId(id);
        UserSkinsPurchases skinsPurchases = userSkinPurchasesRepository.findDistinctByUserId(id);
        UserIconsPurchases iconsPurchases = userIconPurchasesRepository.findDistinctByUserId(id);
        return new AllUserInfo(user, multiRating, singlePoints, skinsPurchases, iconsPurchases);
    }*/


    @GetMapping("/get/money/{uuid}")
    public @ResponseBody
    Long getMoney(@PathVariable("uuid") String uuid) {
        return userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getMoney();
    }

    @GetMapping("/get/points/{uuid}")
    public @ResponseBody
    UserSinglePoints getPoints(@PathVariable("uuid") String uuid) {
        int id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        UserSinglePoints singlePoints = userSinglePointsRepository.getUserSinglePointsByUserId(id);
        int points;
        long currentTime = new Date().getTime();
        int oneLap = gameVariablesRepository.findDistinctById(1).getEnergyTimeToRefill();
        if (singlePoints.getTimeWhenFull() == 0) {  //previously checked after long time
            points = singlePoints.getMaxPoints();
            singlePoints.setTimeToFullInSeconds(0);
        } else {
            if (singlePoints.getTimeWhenFull() < currentTime) {     //now checking after long time
                points = singlePoints.getMaxPoints();
                singlePoints.setTimeWhenFull(0);
                singlePoints.setTimeToFullInSeconds(0);
            } else {
                points = (int) (singlePoints.getMaxPoints() - 1 - (singlePoints.getTimeWhenFull() - currentTime) / oneLap);     //already played
                singlePoints.setTimeToFullInSeconds((int) (singlePoints.getTimeWhenFull() - currentTime) / 1000);
            }
        }
        singlePoints.setPoints(points);
        userSinglePointsRepository.save(singlePoints);
        return singlePoints;
    }


    @GetMapping("/update/{uuid}/refill_points/{is_crystal}")
    public @ResponseBody
    Boolean refillPoints(@PathVariable("uuid") String uuid, @PathVariable("is_crystal") boolean is_crystal) {
        int id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        UserSinglePoints singlePoints = userSinglePointsRepository.getUserSinglePointsByUserId(id);

        if (is_crystal) {
            UsersMultiRating rating = usersMultiRatingRepository.findDistinctByIdUser(id);
            rating.subtractCrystals(gameVariablesRepository
                    .findDistinctById(1).getCrystalsForFullEnergy());
            System.out.println(gameVariablesRepository
                    .findDistinctById(1).getCrystalsForFullEnergy());
            usersMultiRatingRepository.save(rating);
            singlePoints.setPoints(singlePoints.getMaxPoints());
            singlePoints.setTimeWhenFull(0);
        } else {
            int oneLap = gameVariablesRepository.findDistinctById(1).getEnergyTimeToRefill();
            singlePoints.setPoints(singlePoints.getPoints() + 1);
            singlePoints.setTimeWhenFull(singlePoints.getTimeWhenFull() - oneLap);
        }
        userSinglePointsRepository.save(singlePoints);
        return true;
    }

    @GetMapping("/get/multi_rating/{uuid}")
    public @ResponseBody
    UsersMultiRating getRating(@PathVariable("uuid") String uuid) {
        int id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        return usersMultiRatingRepository.findDistinctByIdUser(id);
    }

    @GetMapping("/get/icons/{uuid}")
    public @ResponseBody
    UserIconsPurchases getIconsPurchases(@PathVariable("uuid") String uuid) {
        int id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        return userIconPurchasesRepository.findDistinctByUserId(id);
    }

    @GetMapping("/purchase/{uuid}/icon/{number}/{cost_number_from_zero}")
    public @ResponseBody
    Boolean purchaseIcon(@PathVariable("uuid") String uuid,
                         @PathVariable("number") int number,
                         @PathVariable("cost_number_from_zero") int cost_number) {
        User user = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        user.setCurrentIcon(number);
        userRepository.save(user);
        int userId = user.getId();
        int iconCost = gameVariablesRepository.findAllByIconsCostsNot(0).get(cost_number).getIconsCosts();
        UsersMultiRating multiRating = usersMultiRatingRepository.findDistinctByIdUser(userId);
        multiRating.subtractCrystals(iconCost);
        usersMultiRatingRepository.save(multiRating);
        UserIconsPurchases purchase = userIconPurchasesRepository.findDistinctByUserId(userId);
        purchase.setIconPurchased(number);
        userIconPurchasesRepository.save(purchase);
        return true;
    }

    @GetMapping("/get/skins/{uuid}")
    public @ResponseBody
    UserSkinsPurchases getSkinsPurchases(@PathVariable("uuid") String uuid) {
        int id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        return userSkinPurchasesRepository.findDistinctByUserId(id);
    }

    @GetMapping("/purchase/{uuid}/skin/{number}")
    public @ResponseBody
    Boolean purchaseSkin(@PathVariable("uuid") String uuid,
                         @PathVariable("number") int number) {
        User user = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        int userId = user.getId();
        int skinCost = gameVariablesRepository.findAllByIconsCostsNot(0).get(number).getThemeCosts();
        user.subtractUser_money(skinCost);
        user.setCurrentTheme(number);
        userRepository.save(user);
        UserSkinsPurchases purchase = userSkinPurchasesRepository.findDistinctByUserId(userId);
        purchase.setSkinPurchased(number);
        userSkinPurchasesRepository.save(purchase);
        return true;
    }

    @GetMapping("/get/leaderboard")
    public @ResponseBody
    ArrayList<LeaderboardUser> getLeaders() {
        return leaderboardRepository.findOneHundred();
    }

    @GetMapping("/get/leaderboard/place/{uuid}")
    public @ResponseBody
    LeaderboardUser getUserPlace(@PathVariable String uuid) {
        int userId = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        return leaderboardRepository.findDistinctByUserId(userId);
    }

    @PutMapping("/update/{uuid}/user_name/{user_name}")
    public void setUser_name(@PathVariable String uuid,
                             @PathVariable String user_name) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.setName(user_name);
        userRepository.save(updating);
    }

    @PutMapping("/update/{uuid}/add_user_money/{add}")
    public void addUser_money(@PathVariable String uuid,
                              @PathVariable long add) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.addUser_money(add);
        userRepository.save(updating);
    }

    @GetMapping("/update/{uuid}/subtract_crystal")
    public boolean subtractCrystal(@PathVariable String uuid) {
        int id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        UsersMultiRating rating = usersMultiRatingRepository.findDistinctByIdUser(id);
        rating.subtractCrystals(1);
        usersMultiRatingRepository.save(rating);
        return true;
    }

    @GetMapping("/update/{uuid}/subtract_points/{points}")
    public boolean subtractUserPoints(@PathVariable String uuid,
                                      @PathVariable int points) {
        int id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        UserSinglePoints singlePoints = userSinglePointsRepository.getUserSinglePointsByUserId(id);
        long timeWhenFull;
        int oneLap = gameVariablesRepository.findDistinctById(1).getEnergyTimeToRefill();
        if (singlePoints.getTimeWhenFull() == 0) {
            long currentTime = new Date().getTime();
            timeWhenFull = currentTime + points * oneLap;
        } else {
            timeWhenFull = singlePoints.getTimeWhenFull() + points * oneLap;
        }
        singlePoints.setPoints(singlePoints.getPoints() - points);
        singlePoints.setTimeWhenFull(timeWhenFull);
        userSinglePointsRepository.save(singlePoints);
        return true;
    }


    //////////////////////NOT ADDED///////////////////
    @GetMapping("/update/{uuid}/set_50_max_points")
    public boolean setMaxPoints(@PathVariable String uuid) {
        int id = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid))).getId();
        UserSinglePoints updating = userSinglePointsRepository.getUserSinglePointsByUserId(id);
        updating.setMaxPoints(50);
        updating.setTimeWhenFull(0);
        userSinglePointsRepository.save(updating);
        return true;
    }


    @PutMapping("/update/{uuid}/easy_lose/")
    public void easy_lose(@PathVariable String uuid) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.updateEasy_loses();
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/easy_win/{add}")
    public void easy_win(@PathVariable String uuid,
                         @PathVariable int add) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.updateEasy_winnings(add);
        userRepository.save(updating);
    }

    @PutMapping("/update/{uuid}/medium_lose/")
    public void medium_lose(@PathVariable String uuid) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.updateMedium_loses();
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/medium_win/{add}")
    public void medium_win(@PathVariable String uuid,
                           @PathVariable int add) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.updateMedium_winnings(add);
        userRepository.save(updating);
    }

    @PutMapping("/update/{uuid}/hard_lose")
    public void hard_lose(@PathVariable String uuid) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.updateHard_loses();
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/hard_win/{add}")
    public void hard_win(@PathVariable String uuid,
                         @PathVariable int add) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.updateHard_winnings(add);
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/remove_adv}")
    public void remove_adv(@PathVariable String uuid) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.removeAdv();
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/is_books/{is_books}")
    public void setIs_books(@PathVariable String uuid,
                            @PathVariable boolean is_books) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.setBooks(is_books);
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/is_films/{is_films}")
    public void setIs_films(@PathVariable String uuid,
                            @PathVariable boolean is_films) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.setFilms(is_films);
        userRepository.save(updating);
    }

    @PutMapping("/update/{uuid}/currentTheme/{current_theme_number}")
    public void setCurrentTheme(@PathVariable String uuid,
                                @PathVariable int current_theme_number) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.setCurrentTheme(current_theme_number);
        userRepository.save(updating);
    }

    @GetMapping("/update/{uuid}/currentIcon/{current_icon_number}")
    public boolean setCurrentIcon(@PathVariable String uuid,
                                  @PathVariable int current_icon_number) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.setCurrentIcon(current_icon_number);
        userRepository.save(updating);
        return true;
    }


    @PutMapping("/update/{uuid}/getCredit/{get}")
    public void get_credit(@PathVariable String uuid,
                           @PathVariable long get) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.getCredit(get);
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/returnCredit")
    public void return_credit(@PathVariable String uuid) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.returnCredit();
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/addDeposit/{add}")
    public void add_debit(@PathVariable String uuid,
                          @PathVariable long add) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.addDebit(add);
        userRepository.save(updating);
    }


    @PutMapping("/update/{uuid}/returnDeposit")
    public void return_debit(@PathVariable String uuid) {
        User updating = userRepository.getByUuidBytes(getBytesFromUUID(UUID.fromString(uuid)));
        updating.returnDebit();
        userRepository.save(updating);
    }


    private byte[] getBytesFromUUID(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }

    private UUID getUUIDFromBytes(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        Long high = byteBuffer.getLong();
        Long low = byteBuffer.getLong();
        return new UUID(high, low);
    }

}
