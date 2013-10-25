package nl.topicus.digdag.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity(name = "ORGANISATIEEENHEID")
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_organisatie_code", columnNames = "code"))
public class POrganisatieEenheid extends Entiteit
{
	@Column(nullable = false, length = 15)
	private String code;

	@Column(nullable = false)
	private String naam;

	@Column(nullable = false)
	private boolean actief = true;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "onderdeelVan", nullable = true)
	private POrganisatieEenheid onderdeelVan;

	@OneToMany(mappedBy = "onderdeelVan")
	private List<POrganisatieEenheid> omvat = new ArrayList<>();

	public POrganisatieEenheid()
	{
	}

	public String getCode()
	{
		return code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	public String getNaam()
	{
		return naam;
	}

	public void setNaam(String naam)
	{
		this.naam = naam;
	}

	public POrganisatieEenheid getOnderdeelVan()
	{
		return onderdeelVan;
	}

	public void setOnderdeelVan(POrganisatieEenheid onderdeelVan)
	{
		this.onderdeelVan = onderdeelVan;
	}

	public List<POrganisatieEenheid> getOmvat()
	{
		return omvat;
	}

	public void setOmvat(List<POrganisatieEenheid> omvat)
	{
		this.omvat = omvat;
	}

	public boolean isActief()
	{
		return actief;
	}

	public void setActief(boolean actief)
	{
		this.actief = actief;
	}
}
