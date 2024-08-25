package com.wg.bookmyshow.model;
import java.util.UUID;
public class AccountModel {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String role;
    private int age;
    private static String accountId;
	private String organizerId;

    public AccountModel() {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.age=age;
        this.accountId=accountId;
        this.accountId = UUID.randomUUID().toString(); // Generate UUID
    }

    // Getters and setters for each field

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

	public void setAge(int age) {
		 this.age = age;
	}
	
	public int getAge() {
        return age;
    }

	public static String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setOrganizerId(String accountId2) {
		// TODO Auto-generated method stub
		this.organizerId= accountId;
		
	}
}
