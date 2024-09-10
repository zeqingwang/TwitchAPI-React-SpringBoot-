package com.wzq.twitch.model;


import com.wzq.twitch.db.entity.ItemEntity;


public record FavoriteRequestBody(
        ItemEntity favorite
) {}
