// This is the session bean (POJO) for the user-oriented web pages
// It is created in the UserWelcomeController, and if the user happens
// on another page first, the user is forwarded to that page.
// This bean holds information for the user across the various
// page visits.
package cs636.music.presentation.web;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import cs636.music.domain.*;
import cs636.music.service.data.UserData;
import cs636.music.service.*;
import cs636.music.config.MusicSystemConfig;

public class UserBean {
	
	private UserData loggedInUser = null; 
	private String productCode;  
	private Cart cart = null;
        private String email = null;
	private boolean doingCheckout = false;  
 
        public UserBean() {}

	public UserData getUser() {
		return loggedInUser;
	}

	public void setUser(UserData user) {
		this.loggedInUser = user;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public boolean isDoingCheckout() {
		return doingCheckout;
	}

	public void setDoingCheckout(boolean doingCheckout) {
		this.doingCheckout = doingCheckout;
	}

        public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
