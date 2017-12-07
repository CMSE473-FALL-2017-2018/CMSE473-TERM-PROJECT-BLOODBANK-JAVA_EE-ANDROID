package com.emu.bloodbank;

import com.emu.bloodbank.model.User;

public class AppClass {
	private static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		AppClass.user = user;
	}
}
