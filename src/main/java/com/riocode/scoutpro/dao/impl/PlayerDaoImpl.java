package com.riocode.scoutpro.dao.impl;

import com.riocode.scoutpro.dao.PlayerDao;
import com.riocode.scoutpro.model.Player;
import java.util.List;
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
    public Player create(Player player) {
        entityManager.persist(player);
        return player;
    }

    @Override
    public List<Player> getAll() {
        return entityManager.createNamedQuery("Player.findAll").getResultList();
    }
         
    @Override
    public Player getById(int id) {
        return entityManager.find(Player.class, id);
    }
    
    @Override
    public Player update(Player player) { 
        return entityManager.merge(player);
    }

    @Override
    public void delete(Player player) {
        entityManager.remove(player);
    }

    

}
