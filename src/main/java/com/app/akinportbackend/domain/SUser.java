package com.app.akinportbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "s_user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class SUser extends Auditable<String>{
	@Id
	@SequenceGenerator(name = "s_user_seq", sequenceName = "s_user_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(generator = "s_user_seq", strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	private String username;
	private String password;
	private String name;
	private String surname;
	private String address;
	private String email;
	private String phone;
	
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinTable(name="s_user_role",
        joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
        inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
    )
    private List<SRole> roles;
}
