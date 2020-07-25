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

@Component
public class Utils {

    @Autowired
    private UserService userService;

    @Autowired
    private PrevNamesService prevNamesService;

    public User newUser(String str) {
        User user = new User();
        try {
            System.out.println("================[ Pinging Mojang Api ]=================");
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
        return user;
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
}
