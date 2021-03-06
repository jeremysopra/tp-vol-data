package vol.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vol.Application;
import vol.model.Client;

@Repository
@Transactional
public class ClientDaoJpa implements ClientDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(readOnly = true)
	public Client findById(Integer id) {
		return em.find(Client.class, id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		Query query = em.createQuery("select c from Client c");
		return query.getResultList();
	}

	@Override
	public void create(Client obj) {
		em.persist(obj);
	}

	@Override
	public Client update(Client obj) {
		return em.merge(obj);
	}

	@Override
	public void delete(Client obj) {
		Client clientASuppr = null;
		clientASuppr = em.merge(obj);
		em.remove(clientASuppr);
	}

}
