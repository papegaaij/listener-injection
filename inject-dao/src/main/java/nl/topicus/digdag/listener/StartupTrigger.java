package nl.topicus.digdag.listener;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Startup
@Singleton
public class StartupTrigger
{
	@Inject
	private MyDAO testDAO;

	@PostConstruct
	private void update()
	{
		testDAO.test();
	}
}
