package com.app.akinportbackend.controller;

import com.app.akinportbackend.domain.CustomListCollection;
import com.app.akinportbackend.domain.SUser;
import com.app.akinportbackend.repository.SUserDao;
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

@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Component
public class SUserController {
	@Autowired
	protected SUserDao userDao;
	
	@GetMapping("/get/{id}")
	public SUser get(@PathVariable("id") Integer id) {
		return userDao.getOne(id);
	}
	
	@GetMapping("/list")
	public Collection<SUser> list() {
		return userDao.findAll().stream().collect(Collectors.toList());
	}
	
	@GetMapping("/listLazy/{lazyPage}/{lazyCount}")
	public CustomListCollection<SUser> listLazy(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount, 
			@RequestParam(required=false, name="username") String username) throws Exception {
		CustomListCollection<SUser> c = new CustomListCollection<SUser>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(userDao.findUserByFilters(username, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(userDao.getTotalCount());
		return c;
	}
	
	@GetMapping("/listLazyRolesByUserId/{lazyPage}/{lazyCount}")
	public CustomListCollection<SUser> listLazyRolesByUserId(@PathVariable("lazyPage") Integer lazyPage, @PathVariable("lazyCount") Integer lazyCount, 
			@RequestParam(required=false, name="userId") String userId) throws Exception {
		CustomListCollection<SUser> c = new CustomListCollection<SUser>();
		Pageable pageable = PageRequest.of(lazyPage, lazyCount, Sort.by("id").ascending());
		c.setData(userDao.findUserByFilters(userId, pageable).stream().collect(Collectors.toList()));
		c.setTotalCount(userDao.getTotalCount());
		return c;
	}
	
	@PostMapping("/save")
	ResponseEntity<?> save(@RequestBody SUser model) throws Exception {
		SUser u = userDao.save(model);
		if(u != null) {
			return new ResponseEntity<SUser>(u, HttpStatus.CREATED);
		}else {
			return null;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<SUser> deleteById(@PathVariable("id") Integer id) {
		try {
			userDao.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<SUser> (HttpStatus.ACCEPTED);
	}
}
