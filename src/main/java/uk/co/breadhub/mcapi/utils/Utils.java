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
            ArrayList<PrevNames> prevnames = new ArrayList<>();
            for (Name name : MojangAPI.getNameHistory(userProfile.getName())) {
                PrevNames prevname = new PrevNames(name.getName(), userProfile.getId(), name.getChangedToAt());
                prevnames.add(prevname);
                prevNamesService.save(prevname);
            }

            System.out.println(String.format("Name: %s, UUID: %s, PrevName: %s", userProfile.getName(), userProfile.getId(), prevnames));

            userService.save(user);
        } catch (APIException | IOException e) {
        }
        return user;
    }
}
