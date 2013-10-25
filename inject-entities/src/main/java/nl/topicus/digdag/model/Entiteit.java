/*

 * Copyright (c) 2007, Topicus B.V.
 * All rights reserved.
 */
package nl.topicus.digdag.model;

import java.util.Date;

import javax.persistence.*;

@MappedSuperclass()
public abstract class Entiteit
{
	@Id()
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
	@SequenceGenerator(name = "id_sequence", sequenceName = "id_sequence", allocationSize = 100,
			initialValue = 100)
	@Access(AccessType.PROPERTY)
	private Long id;

	@Version
	@Column(nullable = false)
	private Long version;

	@Column(updatable = false, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedAt;

	public Entiteit()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getVersion()
	{
		return version;
	}

	public void setVersion(Long version)
	{
		this.version = version;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt()
	{
		return lastModifiedAt;
	}

	public void setLastModifiedAt(Date lastModifiedAt)
	{
		this.lastModifiedAt = lastModifiedAt;
	}

	public boolean isSaved()
	{
		return id != null;
	}

	public void touch()
	{
		setLastModifiedAt(new Date());
	}

	@Override
	public String toString()
	{
		return getClass().getSimpleName() + ": " + String.valueOf(id);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj instanceof Entiteit)
		{
			Entiteit other = (Entiteit) obj;
			if (getId() == null && other.getId() == null)
				return false;
			else if (getId() != null)
				return getId().equals(other.getId());
			// else fallthrough
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		if (id != null)
			return id.hashCode();
		return super.hashCode();
	}
}
