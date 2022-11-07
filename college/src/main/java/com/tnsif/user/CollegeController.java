package com.tnsif.user;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class CollegeController {
	
	@Autowired
	private CollegeService service;
	
	@GetMapping("/retrive")
	public List<College> list(){
		return service.listAll();
	}

	@GetMapping("/retrive/{id}")
	public ResponseEntity<College> get (@PathVariable Integer id){
		
		try {
			College user =service.get(id);
			return new ResponseEntity<College>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<College>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping("/insert")
	 public void add(@RequestBody College user) 
	 {
	 service.save(user);
	 }

	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@RequestBody College user , @PathVariable Integer id){		
		try {
			College existPlacement=service.get(id);
			service.save(existPlacement);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("delete/{id}")
	public void delete(@PathVariable Integer id) {
		service.delete(id);
	}
}

    