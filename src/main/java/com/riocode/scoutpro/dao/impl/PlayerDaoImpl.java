package com.riocode.scoutpro.dao.impl;

import com.riocode.scoutpro.dao.PlayerDao;
import com.riocode.scoutpro.model.Player;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nikola Cvetkovic
 */
@Repository
public class PlayerDaoImpl implements PlayerDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Player getById(int id) {
        return entityManager.find(Player.class, id);
    }
    
    @Override
    public Player create(Player player) {
        entityManager.persist(player);
        entityManager.flush();
        return player;
    }

    @Override
    public void update(Player player) {
        entityManager.merge(player);
        entityManager.flush();
    }

    @Override
    public void delete(Player player) {
        entityManager.remove(player);
    }

    

}
