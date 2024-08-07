package com.ufund.api.ufundapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.Need;
import com.ufund.api.ufundapi.persistence.NeedDAO;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("needs") 
public class NeedController {
    private static final Logger LOG = Logger.getLogger(NeedController.class.getName());
    private NeedDAO needDao;

    public NeedController(NeedDAO needDao) {
        this.needDao = needDao;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Need> getNeed(@PathVariable String name) {
        LOG.info("GET /needs/" + name);
        try {
            Need need = needDao.getNeed(name);
            if (need != null)
                return new ResponseEntity<Need>(need,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

  
    @GetMapping("")
    public ResponseEntity<Need[]> getNeeds() {
        LOG.info("GET /needs");

        try {
            Need[] need = needDao.getNeeds();
            if (need!= null)
                return new ResponseEntity<Need[]>(need,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Need[]> searchNeeds(@RequestParam String name) {
        LOG.info("GET /needs/?name="+name);
        try {
            Need[] needs = needDao.findNeeds(name);
            if (needs != null)
                return new ResponseEntity<Need[]>(needs,HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        LOG.info("POST /needs " + need);
        try {
            Need[] exists = needDao.getNeeds();
            if (exists != null){
                Need checkNeed = needDao.getNeed(need.getName());
                if(checkNeed != null){
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
                }
            }
            
            Need newNeed = needDao.createNeed(need);
            if (newNeed != null) {
                return new ResponseEntity<>(newNeed, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e){
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the {@linkplain Need Need} with the provided {@linkplain Need need} object, if it exists
     * 
     * @param Need The {@link Need need} to update
     * 
     * @return ResponseEntity with updated {@link Need need} object and HTTP status of OK if updated<br>
     * ResponseEntity with HTTP status of NOT_FOUND if not found<br>
     * ResponseEntity with HTTP status of INTERNAL_SERVER_ERROR otherwise
     */
    @PutMapping("")
    public ResponseEntity<Need> updateNeed(@RequestBody Need incomingNeed) {
        LOG.info("PUT /needs " + incomingNeed);
        try {
            String needName = incomingNeed.getName();
            Need existingNeed = needDao.getNeed(needName);
            if (existingNeed != null) {
                // Update all properties of the existingNeed with the values from incomingNeed
                existingNeed.setName(incomingNeed.getName());
                existingNeed.setCost(incomingNeed.getCost());
                existingNeed.setType(incomingNeed.getType());
                existingNeed.setCurrentQuantity(incomingNeed.getCurrentQuantity());
                existingNeed.setRequiredQuantity(incomingNeed.getRequiredQuantity());
    
                // Now, update the existingNeed in the data store
                Need updatedNeed = needDao.updateNeed(existingNeed);
    
                if (updatedNeed != null) {
                    return new ResponseEntity<>(updatedNeed, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{name}")
    public ResponseEntity<Need> deleteNeed(@PathVariable String name) {
        LOG.info("DELETE /needs/" + name);

        try {
            Need existingNeed = needDao.getNeed(name);
            if (existingNeed != null){
                boolean delete = needDao.deleteNeed(name);
                if (delete){
                  return new ResponseEntity<>(HttpStatus.OK);
                }           
                 else{
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else {
                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }  
    }

}