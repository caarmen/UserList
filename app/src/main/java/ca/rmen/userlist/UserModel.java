package ca.rmen.userlist;

public class UserModel {

	public static class Name {
		String first;
		String last;
	}

	public static class Picture {
		String thumbnail;
	}

	String gender;
	String city;
	Name name;
	Picture picture;
}
