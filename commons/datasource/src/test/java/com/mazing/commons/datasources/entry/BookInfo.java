package com.mazing.commons.datasources.entry;

import java.sql.Date;

public class BookInfo {

	private int id;
	private String book_id;
	private String book_name;
	private String author;
	private String price;
	private String type;
	private String book_description;
	private String translater;
	private String publish_house;
	private String count;
	private String book_page;
	private String author_intro;
	private String pubdate;
	private String binding;
	private int allow_brow_day = 30;
	private Date last_modify;

	public Date getLast_modify() {
		return last_modify;
	}

	public void setLast_modify(Date last_modify) {
		this.last_modify = last_modify;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBook_id() {
		return book_id;
	}

	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBook_description() {
		return book_description;
	}

	public void setBook_description(String book_description) {
		this.book_description = book_description;
	}

	public String getTranslater() {
		return translater;
	}

	public void setTranslater(String translater) {
		this.translater = translater;
	}

	public String getPublish_house() {
		return publish_house;
	}

	public void setPublish_house(String publish_house) {
		this.publish_house = publish_house;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String var) {
		this.count = var;
	}

	public String getBook_page() {
		return book_page;
	}

	public void setBook_page(String book_page) {
		this.book_page = book_page;
	}

	public String getAuthor_intro() {
		return author_intro;
	}

	public void setAuthor_intro(String author_intro) {
		this.author_intro = author_intro;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public int getAllow_brow_day() {
		return allow_brow_day;
	}

	public void setAllow_brow_day(int allow_brow_day) {
		this.allow_brow_day = allow_brow_day;
	}

}
