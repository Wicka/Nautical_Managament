package wicka.nautical.management.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import wicka.nautical.management.dto.type.TypeUser;

@Entity
@Table(name="user")
public class User {
	


		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id; // Id User Auto-Generated

	 	@Column(name="type")
	    @Enumerated(EnumType.STRING) // IMPORTANT: This converts 0 or 1 to Manager or Admin (INTEGER TO STRING)
	    private TypeUser typeUser;

	    @Column(name="email")
	    private String email; // User Email = Name User

	    @Column(name="password")
	    private String password; // User Password
	    
		public User() {	}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public TypeUser getTypeUser() {
			return typeUser;
		}

		public void setTypeUser(TypeUser typeUser) {
			this.typeUser = typeUser;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		 
		 
}
