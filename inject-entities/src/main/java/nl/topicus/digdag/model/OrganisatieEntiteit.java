package nl.topicus.digdag.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class OrganisatieEntiteit extends Entiteit
{
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "organisatie", nullable = false)
	private POrganisatieEenheid organisatie;

	public OrganisatieEntiteit()
	{
	}

	public POrganisatieEenheid getOrganisatie()
	{
		return organisatie;
	}

	public void setOrganisatie(POrganisatieEenheid organisatie)
	{
		this.organisatie = organisatie;
	}
}
