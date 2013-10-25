package nl.topicus.digdag.listener;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import nl.topicus.digdag.model.POrganisatieEenheid;

@Stateless
public class MyDAO {

	@PersistenceContext(unitName="primary")
	private EntityManager em;
	
	public MyDAO() {
	}

	public void test() {
		em.find(POrganisatieEenheid.class, 5);
	}
}
