package com.ufund.api.ufundapi.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.DonationCart;
import com.model.Need;
import com.ufund.api.ufundapi.persistence.CartDAO;
import com.ufund.api.ufundapi.persistence.NeedDAO;


@RestController
@RequestMapping("cart")
public class DonationCartController {
    private static final Logger LOG = Logger.getLogger(NeedController.class.getName());
    private CartDAO cartDao;
	private NeedDAO needDao; 

    public DonationCartController(CartDAO cartDao, NeedDAO needDao) {
        this.cartDao = cartDao;
		this.needDao = needDao; 
    }


    @GetMapping("/{username}")
	public ResponseEntity<DonationCart> getDonationCart(@PathVariable("username") String usernameString) {
		LOG.info("GET /cart/" + usernameString);
		try {
			DonationCart cart = cartDao.getDonationCart(usernameString);
			return new ResponseEntity<DonationCart>(cart, HttpStatus.OK);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/{username}/add/need")
	public ResponseEntity<DonationCart> addItemToCart(@PathVariable("username") String usernameString, @RequestBody Need need) {
		LOG.info("POST /cart/" + usernameString +"/add/need");
		try {
			Need globalNeed = needDao.getNeed(need.getName());
			if(globalNeed != null){
				need.setCost(globalNeed.getCost());
				need.setRequiredQuantity(globalNeed.getRequiredQuantity());
				need.setType(globalNeed.getType());
				DonationCart cart = cartDao.addItemToDonationCart(usernameString, need);
				return new ResponseEntity<DonationCart>(cart, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/{username}/delete/need")
	public ResponseEntity<DonationCart> deleteItemToCart(@PathVariable("username") String usernameString, @RequestBody Need need) {
		LOG.info("POST /cart/" + usernameString +"/delete/need");
		try {
			DonationCart cart = cartDao.deleteItemFromDonationCart(usernameString, need);
			return new ResponseEntity<DonationCart>(cart, HttpStatus.OK);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/{username}/checkout")
	public ResponseEntity<DonationCart> checkout(@PathVariable("username") String usernameString) {
		LOG.info("POST /cart/" + usernameString + "/checkout");
		try {
			DonationCart cart = cartDao.getDonationCart(usernameString);
			for(Need need : cart.getItemsInDonationCart()){
				int cartNeedQuantity = need.getCurrentQuantity(); 
				needDao.modifyNeedQuantity(need.getName(), cartNeedQuantity);
				cartDao.deleteItemFromDonationCart(usernameString, need);
			}
			return new ResponseEntity<DonationCart>(cart, HttpStatus.OK);
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

