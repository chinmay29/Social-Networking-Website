package com.connect.models.Friends;

public class Friends {
	private int id;
	private String name;
	private String location;
	private String status;
	private StringBuilder sb;
	private String mutuals;
	private int id2;

	public int getId2() {
		return id2;
	}
	public void setId2(int id2) {
		this.id2 = id2;
	}
	public String getMutuals() {
		return mutuals;
	}
	public void setMutuals(String mutuals) {
		this.mutuals = mutuals;
	}
	public StringBuilder getSb() {
		return sb;
	}
	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Friends(){

	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Friends(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return "Friends [id=" + id + ", name=" + name + "]";
	}

}
