package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.News;
import com.riocode.scoutpro.model.Player;

import java.util.List;

public interface NewsService {
    News save(News news);
    List<News> getByPlayer(Player player);
}
