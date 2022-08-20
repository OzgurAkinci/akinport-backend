package com.app.akinportbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "s_parameter")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class SParameter extends Auditable<String>{
	@Id
    @GeneratedValue(generator = "s_parameter_generator")
    @SequenceGenerator(
            name = "s_parameter_generator",
            sequenceName = "s_parameter_seq",
            initialValue = 1
    )
    private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="group_code")
	private String groupCode;
	
	@Column(name="extra_code")
	private String extraCode;
	
	@Column(name="order_val")
	private Integer orderVal;


}
