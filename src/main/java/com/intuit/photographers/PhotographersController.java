package com.intuit.photographers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photographers")
/*json type*/
public class PhotographersController {

    @Autowired
    PhotographersRepository photographersRepository;
    /*
    GET /api/photographers - returns the list of all photographers.
    */
    @RequestMapping(value = "", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Photographer> retrievePhotographers() {
        return photographersRepository.findAll();
    }

    /*
    * GET /api/photographers/{photographerID} - returns a single photographer by ID.
    * */
    @RequestMapping(value = "/{photographerID}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Photographer retrievePhotographerById(@PathVariable int photographerID){
        return photographersRepository.findById(photographerID);
    }

    /*
    * GET /api/photographers/event/{eventType} - returns a list of photographers for the specified event type.
     * */
    @RequestMapping(value = "/event/{eventType}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Photographer> retrievePhotographersByEvent(@PathVariable String eventType){
        return photographersRepository.findByEvenType(eventType);
    }
}
