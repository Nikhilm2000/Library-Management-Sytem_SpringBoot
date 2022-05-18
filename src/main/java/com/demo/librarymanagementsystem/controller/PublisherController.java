package com.demo.librarymanagementsystem.controller;

import java.math.BigDecimal;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.demo.librarymanagementsystem.entity.Publisher;
import com.demo.librarymanagementsystem.entity.Contact;
import com.demo.librarymanagementsystem.service.PublisherService;

@Controller
public class PublisherController {

	private final PublisherService publisherService;

	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@RequestMapping("/publishers")
	public String findAllPublishers(Model model) {
		final List<Publisher> publishers = publisherService.findAllPublishers();

		model.addAttribute("publishers", publishers);
		return "list-publishers";
	}

	@RequestMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable("id") Long id, Model model) {
		final Publisher publisher = publisherService.findPublisherById(id);

		model.addAttribute("publisher", publisher);
		return "list-publisher";
	}

	@GetMapping("/addPublisher")
	public String showCreateForm(Publisher publisher) {
		return "add-publisher";
	}

	@RequestMapping("/add-publisher")
	public String createPublisher(Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}

		publisherService.createPublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@GetMapping("/updatePublisher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) {
		final Publisher publisher = publisherService.findPublisherById(id);

		model.addAttribute("publisher", publisher);
		return "update-publisher";
	}

	@RequestMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			publisher.setId(id);
			return "update-publishers";
		}

		publisherService.updatePublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@RequestMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable("id") Long id, Model model) {
		publisherService.deletePublisher(id);

		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}
	
	@RequestMapping("/contact/{id}")
	public String viewPublisher(@PathVariable("id") Long id, Model model) {
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}
	@RequestMapping("/publisher/contact/{pub_id}")
	public ModelAndView viewPublisherContact(@PathVariable(name="pub_id") Long pub_id) {
		ModelAndView mav = new ModelAndView("view_contact");
		ResponseEntity<Contact> res = new RestTemplate().getForEntity("http://localhost:8090/contact/publisher/{pub_id}",Contact.class,pub_id);
		Contact contact = res.getBody();
		mav.addObject("contact",contact);
		return mav;
	}
//	@GetMapping("/publisher/contact/{id}")
//	  public ModelAndView viewContact(@PathVariable(name="id") int id) {
//		ModelAndView mav = new ModelAndView("view_contact");
//		    Map<String,String> uriVariables = new HashMap<String, String>();
//		    uriVariables.put("id", Integer.toString(id));
//		    ResponseEntity<PublisherContact> responseEntity = new RestTemplate().getForEntity(
//		    		"http://localhost:9090/contact/{id}", PublisherContact.class,
//		            uriVariables);
//		    PublisherContact contact = responseEntity.getBody();
//		    mav.addObject("contact",contact);
//		    return mav;
//		    
//		    //PublisherContact  response = responseEntity.getBody();
//		    //return new PublisherContact (id,response.getPub_id(),response.getContact_num(), response.getEmail());
//	 }
//	@RequestMapping("/publisher/contact/{id}/{pub_id}/{contact_num}/{email}")
//	public ModelAndView viewPublisherContact(@PathVariable(name="id") int id,@PathVariable(name="pub_id") int pub_id,@PathVariable(name="contact_num") String contact_num,@PathVariable(name="email") String email) {
//		ModelAndView mav = new ModelAndView("view_contact");
//		PublisherContact contact1 = new PublisherContact();
//		ResponseEntity<PublisherContact> res = new RestTemplate().getForEntity("http://localhost:9090/contact/"+contact1.getPub_id(),PublisherContact.class);
//		PublisherContact contact = res.getBody();
//		mav.addObject("contact",contact);
//		return mav;
//	}
//	@GetMapping("/publisher/contact/{id}")
//	  public PublisherContact viewPublisherContact(@PathVariable(name="id") int id) {
//		PublisherContact contact1 = new PublisherContact();
//		ResponseEntity<PublisherContact> responseEntity = new RestTemplate().getForEntity(
//		    		"http://localhost:9090/contact/"+contact1.getPub_id(),PublisherContact.class);
//		    PublisherContact response = responseEntity.getBody();
//		    return new PublisherContact(response.getPub_id(),id, response.getContact_num(), response.getEmail());
//	 }
//	
}
