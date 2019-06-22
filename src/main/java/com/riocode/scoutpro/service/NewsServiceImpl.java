package com.riocode.scoutpro.service;

import com.riocode.scoutpro.model.News;
import com.riocode.scoutpro.model.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewsServiceImpl implements NewsService {
    @Override
    public News save(News news) {
        return null;
    }

    @Override
    public List<News> getByPlayer(Player player) {
        return null;
    }
}
