package com.data.spring;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/guest")
@RestController
public class GuestController {
	
	
	@Autowired
	CheckRepo gr;
	
	
	@Autowired
	GuestRepo cr;
	
	@GetMapping
	public Page<Guest> getAll(@PageableDefault(sort="name" , direction=Sort.Direction.ASC)Pageable page)
	{
	
		
		return cr.findAll(page);
	}
	
	
	
	@GetMapping("/{id}")
	
	public Guest getId(@PathVariable int id)
	{
		return cr.findById(id).orElse(null);
	}
	
	
	
	@PostMapping
	public Guest saveAll(@RequestBody Guest ce)
	{
		

		return cr.save(ce);
		
	}
	
	
	@PutMapping("/{id}")
	public Guest puutId(@PathVariable int id, @RequestBody Guest c)
	{
		
		Guest ce = cr.findById(id).orElseThrow();
		ce.setName(c.getName());
		ce.setCheck(c.getCheck());
		
		return cr.save(ce);
		
	}
	
	
	
	@DeleteMapping("/{id}")
	public void dele(@PathVariable int id)
	{
		cr.deleteById(id);
	}
	
	
	
	
	
	
	
	

}
