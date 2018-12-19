package com.cts.springcloud.model;

public class Student {

	private String name;

	private int age;

	private String prvHistory;

	public Student() {

	}

	public Student(String name, int age, String prvHistory) {
		this.name = name;
		this.age = age;
		this.prvHistory = prvHistory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPrvHistory() {
		return prvHistory;
	}

	public void setPrvHistory(String prvHistory) {
		this.prvHistory = prvHistory;
	}

}
