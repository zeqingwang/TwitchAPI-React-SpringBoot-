
package com.wzq.twitch.favorite;


import com.wzq.twitch.db.entity.UserEntity;
import com.wzq.twitch.model.FavoriteRequestBody;
import com.wzq.twitch.model.TypeGroupedItemList;
import com.wzq.twitch.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/favorite")
public class FavoriteController {


    private final FavoriteService favoriteService;
    private final UserService userService;


    public FavoriteController(
            FavoriteService favoriteService,
            UserService userService
    ) {
        this.favoriteService = favoriteService;
        this.userService = userService;
    }


    @GetMapping
    public TypeGroupedItemList getFavoriteItems(@AuthenticationPrincipal User user) {
        UserEntity userEntity = userService.findByUsername(user.getUsername());
        return favoriteService.getGroupedFavoriteItems(userEntity);
    }


    @PostMapping
    public void setFavoriteItem(@AuthenticationPrincipal User user, @RequestBody FavoriteRequestBody body) {
        UserEntity userEntity = userService.findByUsername(user.getUsername());
        try {
            favoriteService.setFavoriteItem(userEntity, body.favorite());
        } catch (DuplicateFavoriteException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate entry for favorite record", e);
        }
    }


    @DeleteMapping
    public void unsetFavoriteItem(@AuthenticationPrincipal User user, @RequestBody FavoriteRequestBody body) {
        UserEntity userEntity = userService.findByUsername(user.getUsername());
        favoriteService.unsetFavoriteItem(userEntity, body.favorite().twitchId());
    }
}

