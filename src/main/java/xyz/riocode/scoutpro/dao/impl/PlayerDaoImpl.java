package xyz.riocode.scoutpro.dao.impl;

import org.springframework.stereotype.Repository;
import xyz.riocode.scoutpro.dao.PlayerDao;
import xyz.riocode.scoutpro.model.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 * @author Nikola Cvetkovic
 */
@Repository
public class PlayerDaoImpl implements PlayerDao {

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
    public List<Player> getByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> criteria = cb.createQuery(Player.class);
        Root<Player> r = criteria.from(Player.class);
        criteria.select(r).where(cb.like(r.get("transfermarktInfo").get("playerName"), "%" + name +"%"));
        
        return entityManager.createQuery(criteria).getResultList();
    }

    @Override
    public List<Player> getByTransfermarktUrl(String transfermarktUrl) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Player> criteria = cb.createQuery(Player.class);
        Root<Player> r = criteria.from(Player.class);
        criteria.select(r).where(cb.equal(r.get("transfermarktUrl"), transfermarktUrl));
        
        return entityManager.createQuery(criteria).getResultList();
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
