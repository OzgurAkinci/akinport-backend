package com.app.akinportbackend.controller;

import com.app.akinportbackend.domain.CustomListCollection;
import com.app.akinportbackend.domain.SParameter;
import com.app.akinportbackend.domain.SUser;
import com.app.akinportbackend.repository.SParameterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("/parameter")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Component
public class SParameterController {
	@Autowired
	protected SParameterDao parameterDao;
	
	@GetMapping("/get/{id}")
	public SParameter get(@PathVariable("id") Integer id) {
		return parameterDao.getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<SParameter> list() {
		return parameterDao.findAll().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/listLazy/{lazyPage}/{lazyCount}")
	public CustomListCollection<SParameter> listLazy(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount, 
			@RequestParam(required=false, name="name") String name, 
			@RequestParam(required=false, name="groupCode") String groupCode) throws Exception {
		CustomListCollection<SParameter> c = new CustomListCollection<SParameter>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(parameterDao.findParameterByFilters(name, groupCode, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(parameterDao.getTotalCount());
		return c;
	}
	
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody SParameter model) throws Exception {
		if(model == null || model.getName() == null || model.getGroupCode() == null) // required field
			return new ResponseEntity<String>("Fill in the required fields.", HttpStatus.BAD_REQUEST);
		
		SParameter u = parameterDao.save(model);
		if(u != null) {
			return new ResponseEntity<SParameter>(u, HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SUser> deleteById(@PathVariable("id") Integer id) {
		try {
			parameterDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SUser> (HttpStatus.ACCEPTED);
	}
    
}
