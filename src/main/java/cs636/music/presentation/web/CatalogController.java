package cs636.music.presentation.web;

// TODO: pa2: add methods here to service URLs listed below
// which involve forwarding to JSPs listed below as VIEWs or in some cases
// to URLs

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;

import cs636.music.domain.Cart;
import cs636.music.domain.Product;
import cs636.music.domain.Track;
import cs636.music.presentation.client.PresentationUtils;
import cs636.music.service.ServiceException;
import cs636.music.service.UserService;
import cs636.music.service.data.CartItemData;
import cs636.music.service.data.UserData;

@Controller
@SessionAttributes("cart")
public class CatalogController {
	@Autowired
	private UserService userService;

	// String constants for URL's : please use these!
	private static final String WELCOME_URL = "/welcome.html";
	private static final String WELCOME_VIEW = "/welcome";
	private static final String USER_WELCOME_URL = "/userWelcome.html";
	private static final String CATALOG_URL = "/catalog.html";
	private static final String CATALOG_VIEW = "/catalog";
	private static final String CART_URL = "/cart.html";
	private static final String CART_VIEW = "/cart";
	private static final String PRODUCT_URL = "/product.html";
	private static final String PRODUCT_VIEW = "/product";
	private static final String LISTEN_URL = "/listen.html";
	private static final String SOUND_VIEW = "/sound";
	private static final String DOWNLOAD_URL = "/download.html"; // download.html didn't work
	private static final String REGISTER_FORM_VIEW = "/registerForm";

	@RequestMapping(WELCOME_URL)
	public String handleWelcome() {
		return WELCOME_VIEW;
	}

        @RequestMapping("/download")
	public String showProduct(Model model,@RequestParam(value = "productCode", required=false)
	String productCode, @RequestParam(value = "Quantity", required=false)
	Integer Quantity,HttpServletRequest request) throws ServletException
	{
		String url = null;
		if (productCode.equals("pf01")) {
			productCode = "pf01";
			model.addAttribute("productCode", productCode);
			model.addAttribute("title", "Whiskey Before Breakfast");
			model.addAttribute("src", "/sound/pf01/whiskey.mp3");
			model.addAttribute("title1", "64 corvair");
			model.addAttribute("src1", "/sound/pf01/corvair.mp3");
			url = "/sound/universal"; 
		} else if(productCode.equals("8601")) {
			model.addAttribute("productCode", productCode);
			model.addAttribute("title", "You Are a Star");
			model.addAttribute("src",
					"/sound/8601/star.mp3");
			
			model.addAttribute("title1", "Don't Make No Difference");
			model.addAttribute("src1",
					"/sound/8601/no_difference.mp3");
			url = "/sound/universal"; 
		} else if(productCode.equals("jr01")) {
			model.addAttribute("productCode", productCode);
			model.addAttribute("title", "Filter");
			model.addAttribute("src",
					"/sound/jr01/filter.mp3");
			
			model.addAttribute("title1", "So Long Lazy Ray");
			model.addAttribute("src1",
					"/sound/jr01/so_long.mp3");
			url = "/sound/universal"; 
		} else if(productCode.equals("pf02")) {
			model.addAttribute("productCode", productCode);
			model.addAttribute("title", "Neon Lights");
			model.addAttribute("src",
					"/sound/pf02/neon.mp3");
			
			model.addAttribute("title1", "Tank Hill");
			model.addAttribute("src1",
					"/sound/pf02/tank.mp3");
			url = "/sound/universal"; 
		} 
		model.addAttribute("productCode", productCode);
		return url;
	}

        @RequestMapping("cart")
	public String cart(Model model,@RequestParam(value="productCode",required = false) 
	String productCode, @RequestParam(value="productQuantity",required = false) Integer productQuantity
			,HttpServletRequest request) throws ServletException {
		
		Cart cart = null;
		Product product = new Product();
		try {
			 product = userService.getProduct(productCode);
			 
		}catch(Exception e) {
			throw new ServletException(e);
		}
		long productId = product.getId();
		System.out.println("The product Id is:=" + productId);
		System.out.println("The product Quantity is:=" + productQuantity);
		model.addAttribute("productId", productId);
		model.addAttribute("productCode", productCode);
		model.addAttribute("productQuantity", productQuantity);
		
		try {
			
			cart = (Cart) request.getSession().getAttribute("cart");
			if (checkCart(request) == false) {
				cart = userService.createCart();
			}
			userService.addItemtoCart(product, cart, productQuantity);
			if(checkCart(request) == false) {
			request.getSession().setAttribute("cart", cart);
			}
		}catch(Exception e) {
			throw new ServletException(e);
		}	
		
		return "cart";
	}

	@RequestMapping(CART_URL)
	public String showcart(Model model,HttpServletRequest request) throws ServletException {
		
		Set<CartItemData> setofcartdata = new HashSet<CartItemData>();
		ArrayList<Long> id = new ArrayList<>();
		ArrayList<Integer> quantity = new ArrayList<>();
		
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		try {
			setofcartdata = userService.getCartInfo(cart);
			for(CartItemData a : setofcartdata) {
				 id.add(a.getProductId());
				quantity.add(a.getQuantity());
			}
			
			model.addAttribute("productId", id);
			model.addAttribute("productQuantity", quantity);
		}catch(Exception e) {
			throw new ServletException(e);
		}	
		return "cart";
	}

       private boolean checkCart(HttpServletRequest request) throws IOException {
		 HttpSession session = request.getSession();
			Cart cart = (Cart) session.getAttribute("cart");
			return (cart != null);
		}

}
