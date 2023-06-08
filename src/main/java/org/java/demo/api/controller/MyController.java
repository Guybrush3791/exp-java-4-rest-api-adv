package org.java.demo.api.controller;

import java.util.List;

import org.java.demo.pojo.Book;
import org.java.demo.serv.BookServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MyController {

	@Autowired
	private BookServ bookServ;
	
	@GetMapping
	public String getBookIndex(Model model) {
		
		List<Book> books = bookServ.findAll();
		
		model.addAttribute("books", books);
		
		return "books-index";
	}
	@GetMapping("/create")
	public String getBookForm(Model model) {
		
		model.addAttribute("book", new Book());
		
		return "books-create";
	}
	@PostMapping("/create")
	public String storeBook(
			Model model,
			@ModelAttribute Book book
		) {
		
		System.err.println(book);
		
		bookServ.save(book);
		
		return "redirect:/";
	}
}
