package org.java.demo.pojo;

import java.io.IOException;
import java.util.Base64;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	private String name;
	private String description;
	
	@PositiveOrZero
	private Integer price;
	
	@Lob
	@Column(length=16777215)
	@JsonIgnore
	private byte[] image;
	
	@Transient
	@JsonIgnore
	MultipartFile mpImage;
	
	public Book() { }
	public Book(String name, String description, Integer price) {
		
		setName(name);
		setDescription(description);
		setPrice(price);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public boolean hasImage() {
		
		return getImage() != null;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public boolean hasMpImage() {
		
		return getMpImage() != null;
	}
	public MultipartFile getMpImage() {
		return mpImage;
	}
	public void setMpImage(MultipartFile mpImage) {
		
		try {
			
			setImage(mpImage.getBytes());
			
			this.mpImage = mpImage;
		} catch (IOException e) { }
	}
	public String getREImage() {
		
		return Base64.getEncoder().encodeToString(getImage());
	}
	@Override
	public String toString() {
		
		return "[" + getId() + "] " + getName()
			+ "\n" + getDescription()
			+ "\nprice: " + getPrice() + "E";
	}
}
