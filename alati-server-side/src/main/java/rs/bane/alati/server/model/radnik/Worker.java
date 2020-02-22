package rs.bane.alati.server.model.radnik;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_worker")
public class Worker {

	public enum ContractType {
		CONTRACT_3_MONTHS, NO_CONTRACT
	}

	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "contract_type")
	private ContractType contractType;

	public Worker() {

	}

	public Worker(String name, String lastName, ContractType contractType) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.contractType = contractType;
	}

	public Worker(Long id, String name, String lastName, ContractType contractType) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.contractType = contractType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Worker other = (Worker) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

}
