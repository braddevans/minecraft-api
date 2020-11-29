package uk.co.breadhub.mcapi.utils;

import me.kbrewster.exceptions.APIException;
import me.kbrewster.mojangapi.MojangAPI;
import me.kbrewster.mojangapi.profile.Name;
import me.kbrewster.mojangapi.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.breadhub.mcapi.model.PrevNames;
import uk.co.breadhub.mcapi.model.User;
import uk.co.breadhub.mcapi.service.PrevNamesService;
import uk.co.breadhub.mcapi.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Utils implements UtilsApi {

    public static int requestsDone = 0;
    public static int maxRequests = 250;
    public static int queueSize = 0;
    public static List<String> queueName = new ArrayList<>();
    public static List<UUID> queueUUID = new ArrayList<>();

    @Autowired
    private UserService userService;

    @Autowired
    private PrevNamesService prevNamesService;

    public User newUser(String str) {

        User user = new User();

        if (requestsDone >= 500) {
            System.out.println("Adding Name: " + str + " to Queue");
            queueSize += 1;
            queueName.add(str);
        }
        else {

            try {
                Profile userProfile = MojangAPI.getProfile(str);
                user.setName(userProfile.getName());
                user.setUuid(userProfile.getId());
                for (Name name : MojangAPI.getNameHistory(userProfile.getName())) {
                    PrevNames prevname = new PrevNames(name.getName(), userProfile.getId(), name.getChangedToAt());
                    prevNamesService.save(prevname);
                }
                System.out.println(String.format("Name: %s, UUID: %s", userProfile.getName(), userProfile.getId()));

                userService.save(user);
            } catch (APIException | IOException e) {
            }
            requestsDone += 1;
        }
        return user;
    }

    public User newUser(UUID str) {

        User user = new User();

        if (requestsDone >= 500) {
            queueSize += 1;
            System.out.println("Adding UUID: " + str + " to Queue");
            queueUUID.add(str);
        }
        else {
            try {
                Profile userProfile = MojangAPI.getProfile(str);
                user.setName(userProfile.getName());
                user.setUuid(userProfile.getId());
                for (Name name : MojangAPI.getNameHistory(userProfile.getName())) {
                    PrevNames prevname = new PrevNames(name.getName(), userProfile.getId(), name.getChangedToAt());
                    prevNamesService.save(prevname);
                }
                System.out.println(String.format("Name: %s, UUID: %s", userProfile.getName(), userProfile.getId()));

                userService.save(user);
            } catch (APIException | IOException e) {
            }
            requestsDone += 1;
        }
        return user;
    }

    public void processQueue() {
        if (queueSize >= maxRequests) {
            if (queueName.size() < 150 && queueUUID.size() < 150) {
                loopNames(queueName.size(), queueName);
                loopUUIDS(queueUUID.size(), queueUUID);
            }
            else {
                loopNames(Math.min(queueName.size(), 75), queueName);
                loopUUIDS(Math.min(queueUUID.size(), 75), queueUUID);
            }
        }
        else {
            loopNames(queueName.size(), queueName);
            loopUUIDS(queueUUID.size(), queueUUID);
        }
        //wait 5 mins
    }

    private void loopNames(int size, List<String> queue) {
        for (int i = 0; i < size; i++) {
            User user = new User();
            try {
                Profile userProfile = MojangAPI.getProfile(queue.get(i));
                user.setName(userProfile.getName());
                user.setUuid(userProfile.getId());
                for (Name name : MojangAPI.getNameHistory(userProfile.getName())) {
                    PrevNames prevname = new PrevNames(name.getName(), userProfile.getId(), name.getChangedToAt());
                    prevNamesService.save(prevname);
                }
                System.out.println(String.format("Name: %s, UUID: %s", userProfile.getName(), userProfile.getId()));

                userService.save(user);
            } catch (APIException | IOException e) {
            }
            requestsDone += 1;
        }
    }

    private void loopUUIDS(int size, List<UUID> queue) {
        for (int i = 0; i < size; i++) {
            User user = new User();
            try {
                Profile userProfile = MojangAPI.getProfile(queue.get(i));
                user.setName(userProfile.getName());
                user.setUuid(userProfile.getId());
                for (Name name : MojangAPI.getNameHistory(userProfile.getName())) {
                    PrevNames prevname = new PrevNames(name.getName(), userProfile.getId(), name.getChangedToAt());
                    prevNamesService.save(prevname);
                }
                System.out.println(String.format("Name: %s, UUID: %s", userProfile.getName(), userProfile.getId()));

                userService.save(user);
            } catch (APIException | IOException e) {
            }
            requestsDone += 1;
        }
    }

    public String updatePrevNames(String uuid) {
        String names = "";
        User user = userService.findByUuid(uuid).get();
        prevNamesService.deleteByUuid(uuid);
        try {
            System.out.println("================[ Pinging Mojang Api for names ]=================");
            Profile userProfile = MojangAPI.getProfile(user.getName());
            StringBuilder history = new StringBuilder("[");
            for (Name name : MojangAPI.getNameHistory(userProfile.getName())) {
                PrevNames prevname = new PrevNames(name.getName(), userProfile.getId(), name.getChangedToAt());
                prevNamesService.save(prevname);
            }
        } catch (APIException | IOException ignored) {
        }
        return names;
    }

    public boolean updateName(String uuid) {
        boolean bool = false;
        try {
            System.out.println("================[ Pinging Mojang Api ]=================");
            User user = userService.findByUuid(uuid).get();
            Profile userProfile = MojangAPI.getProfile(user.getName());
            user.setName(userProfile.getName());
            userService.save(user);
            bool = true;
        } catch (APIException | IOException ignored) {
        }
        return bool;
    }

    public String insertDashUUID(String uuid) {
        StringBuffer sb = new StringBuffer(uuid);
        sb.insert(8, "-");

        sb = new StringBuffer(sb.toString());
        sb.insert(13, "-");

        sb = new StringBuffer(sb.toString());
        sb.insert(18, "-");

        sb = new StringBuffer(sb.toString());
        sb.insert(23, "-");

        return sb.toString();
    }
}
