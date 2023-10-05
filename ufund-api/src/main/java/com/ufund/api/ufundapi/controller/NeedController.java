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
        }
        catch(IOException e) {
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
        }
        catch(IOException e) {
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
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseEntity<Need> createNeed(@RequestBody Need need) {
        LOG.info("POST /needs " + need);
        try {
            Need[] exists=needDao.getNeeds();
            for (Need exist:exists){
                if(exist.getName().equals(need.getName())){
                    return new ResponseEntity<Need>(HttpStatus.CONFLICT);
                }
            }
            need = needDao.createNeed(new Need(need.getName(), need.getCost(), need.getQuantity(), need.getType()));
            return new ResponseEntity<>(need,HttpStatus.CREATED);
        }
        catch(IOException e) {
            LOG.log(Level.SEVERE,e.getLocalizedMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseEntity<Need> updateNeed(@RequestBody Need need) {
        LOG.info("PUT /needs " + need);

        try {
            String needName = need.getName();
            Need existingNeed = needDao.getNeed(needName);
            if (existingNeed != null) {
                existingNeed.setName(need.getName());
                Need updatedNeed = needDao.updateNeed(existingNeed);
                if (updatedNeed != null) {
                    return new ResponseEntity<>(updatedNeed, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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