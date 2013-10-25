package nl.topicus.digdag.listener;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

public class ExcessiveLoadListener
{
	@RequestScoped
	public static class LoadStatistics
	{
		private long count = 0;

		private long triggerSize = LOG_START;

		public long getCount()
		{
			return count;
		}

		public long incCount()
		{
			return ++count;
		}

		public long getTriggerSize()
		{
			return triggerSize;
		}

		public void setTriggerSize(long triggerSize)
		{
			this.triggerSize = triggerSize;
		}

		public void reset()
		{
			this.count = 0;
		}
	}

	public static final long LOG_START = 1 << 14;

	public static final long EXCEPTION_LOG_START = 1 << 16;

	public static final long ABORT = 1 << 17;

	@Inject
	private LoadStatistics statistics;

	@PersistenceContext
	private EntityManager em;

	public ExcessiveLoadListener()
	{
	}

	@PrePersist
	public void prePersist(Object entity)
	{
		inc();
	}

	@PostLoad
	public void postLoad(Object entity)
	{
		inc();
	}

	private void inc()
	{
		try
		{
			long size = statistics.incCount();
			if (size > LOG_START)
			{
				long triggerSize = statistics.getTriggerSize();
				if (size > triggerSize)
				{
					if (size >= ABORT)
					{
						throw new IllegalStateException("Excessive load detected: " + size
							+ " entities loaded");
					}
					statistics.setTriggerSize(triggerSize << 1);
				}
			}
		}
		catch (ContextNotActiveException e)
		{
			// ignore exception, not in the context of a request
		}
	}
}
