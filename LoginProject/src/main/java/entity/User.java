package entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;
    


	
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
	
	public User(String u, String p) {
		this.username = u;
		this.password = p;
	}
	
	 public void setUsername(String u) {
	        this.username = u;
	    }
   
	 public void setPassword(String p) {
	        this.password = p;
	    }
	 
	 public String getUsername() {
	        return this.username;
	    }

	 public String getPassword() {
		 return this.password;
	    }
}
