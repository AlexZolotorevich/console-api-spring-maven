package by.htp.news.bean;

import java.util.List;

public class Article {
	private int ID;
	private String title;
	private int status;
	private String date;
	private String heading;
	private String annotation;
	private String text;
	private List<String> textList;

	// constructor for adding

	public Article(String title, String date, String heading, String annotatio, String text) {
		this.title = title;
		this.date = date;
		this.heading = heading;
		this.annotation = annotatio;
		this.text = text;

	}

	// constructor for getting one and map of articles

	public Article(int ID, String title, String date, String heading, String annotatio, List<String> textList) {
		this.ID = ID;
		this.title = title;
		this.date = date;
		this.heading = heading;
		this.annotation = annotatio;
		this.textList = textList;
	}

	public List<String> getTextList() {
		return textList;
	}

	public int getId() {
		return ID;
	}

	public void setId(int ID) {
		this.ID = ID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return ID + ") " + "   ||   " + title + "   ||   " + date + "   ||   " + heading + "   ||   " + annotation;
	}

}
