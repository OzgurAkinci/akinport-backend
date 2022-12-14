package com.app.akinportbackend.repository;


import com.app.akinportbackend.domain.SParameter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SParameterDao extends JpaRepository<SParameter, Integer>{
	@Query("SELECT count(id) FROM SParameter")
	public Long getTotalCount() throws Exception;	
	
	@Query("SELECT e FROM SParameter e WHERE e.name like %:name% and e.groupCode like %:groupCode%")
	public List<SParameter> findParameterByFilters(@Param("name") String name, @Param("groupCode") String groupCode, Pageable pageable) throws Exception;
}

