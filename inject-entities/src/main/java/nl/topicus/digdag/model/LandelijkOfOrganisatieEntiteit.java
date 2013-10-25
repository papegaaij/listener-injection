package nl.topicus.digdag.model;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LandelijkOfOrganisatieEntiteit extends Entiteit
{
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "organisatie", nullable = true)
	private POrganisatieEenheid organisatie;

	public LandelijkOfOrganisatieEntiteit()
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
