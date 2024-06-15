package com.data.spring;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/checck")
@RestController
public class CheckController {
	
	
	@Autowired
	CheckRepo cr;
	
	
	@Autowired
	GuestRepo gr;
	
	@GetMapping
	public Page<Checks> getAll(@PageableDefault(sort="name" , direction=Sort.Direction.ASC)Pageable page)
	{
	
		
		return cr.findAll(page);
	}
	
	
	
	@GetMapping("/{id}")
	
	public Checks getId(@PathVariable int id)
	{
		return cr.findById(id).orElse(null);
	}
	
	
	
	@PostMapping
	public Checks saveAll(@RequestBody Checks c)
	{
		Checks ce = new Checks();
		ce.setName(c.getName());
		
		List<Guest> list = new ArrayList<>();
		
		for(Guest g : c.getGuest())
		{
			Guest ge = new Guest();
			ge.setName(g.getName());
			ge.setCheck(ce);
			
			list.add(ge);
		}
		
		ce.setGuest(list);

		return cr.save(ce);
		
	}
	
	
	@PutMapping("/{id}")
	public Checks puutId(@PathVariable int id, @RequestBody Checks c)
	{
		
		Checks ce = cr.findById(id).orElseThrow();
		ce.setName(c.getName());
		
		List<Guest> list = new ArrayList<>();
		
		for(Guest g : c.getGuest())
		{
			if(g.getId()>0) {
				
				Guest ge =  gr.findById(g.getId()).orElseThrow();
				ge.setName(g.getName());
				ge.setCheck(ce);
				
				list.add(ge);
			}else {
			Guest ge = new Guest();
			ge.setName(g.getName());
			ge.setCheck(ce);
			
			list.add(ge);
			}
		}
		
		
		
		ce.getGuest().clear();
		ce.getGuest().addAll(list);
		
		return cr.save(ce);
		
	}
	
	
	
	@DeleteMapping("/{id}")
	public void dele(@PathVariable int id)
	{
		cr.deleteById(id);
	}
	
	
	
	
	
	
	
	

}
