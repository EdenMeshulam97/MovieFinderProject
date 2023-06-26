package com.hit.dm;

import java.awt.Image;
import java.util.Objects;

public class Movie {
	
	private String name;
	private String description;
	private String trailerLink;

	public Movie(String name, String description, String trailerLink) {
		this.name = name;
		this.description = description;
		this.trailerLink = trailerLink;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTrailerLink() {
		return this.trailerLink;
	}

	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}
	
	 public boolean validate() {
	        return name != null && !name.trim().isEmpty() &&
	               description != null && !description.trim().isEmpty() &&
	               trailerLink != null && !trailerLink.trim().isEmpty();
	    }
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Movie other = (Movie) obj;
	    return Objects.equals(name, other.name)
	        && Objects.equals(description, other.description)
	        && Objects.equals(trailerLink, other.trailerLink);
	}
	
	@Override
	public int hashCode() {
	    return Objects.hash(name, description, trailerLink);
	}
	
	
}
