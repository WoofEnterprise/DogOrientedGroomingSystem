package org.woofenterprise.dogs.rest.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.woofenterprise.dogs.dto.CustomerCreateDTO;
import org.woofenterprise.dogs.dto.CustomerDTO;
import org.woofenterprise.dogs.facade.CustomerFacade;
import org.woofenterprise.dogs.rest.ApiUris;
import org.woofenterprise.dogs.rest.exceptions.ResourceAlreadyExistsException;
import org.woofenterprise.dogs.rest.exceptions.ResourceNotFoundException;

import javax.inject.Inject;
import java.util.Collection;
import org.springframework.http.HttpStatus;

/**
 * @author Silvia.Vigasova
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_CUSTOMERS)
public class CustomersController {

    final static Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @Inject
    CustomerFacade customerFacade;

    /**
     * Get list of Customers curl -i -X GET
     * http://localhost:8080/pa165/rest/customers
     *
     * @return CustomerDTO
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CustomerDTO> getCustomers() {
        logger.debug("rest getCustomers()");
        return customerFacade.getAllCustomers();
    }

    /**
     * Get Customer by identifier id curl -i -X GET
     * http://localhost:8080/pa165/rest/customers/1
     *
     * @param id identifier for a Customer
     * @return CustomerDTO returned customer
     * @throws ResourceNotFoundException if the customer with given id does not exist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomer(@PathVariable("id") long id) throws Exception {
        logger.debug("rest getCustomer({})", id);
        CustomerDTO customerDTO = customerFacade.findCustomerById(id);
        if (customerDTO != null) {
            return customerDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Get Customer by email curl -i -X GET
     * http://localhost:8080/pa165/rest/customers/email/neco@neco.cz
     *
     * @param email email of a Customer
     * @return CustomerDTO returned customer
     * @throws ResourceNotFoundException if the cusotmer with given email does not exist
     */
    @RequestMapping(value = "/email/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomerByEmail(@PathVariable("email") String email) throws ResourceNotFoundException {
        logger.debug("rest getCustomer({})", email);
        CustomerDTO customerDTO = customerFacade.findCustomerByEmail(email);
        if (customerDTO != null) {
            return customerDTO;
        } else {
            throw new ResourceNotFoundException();
        }

    }

    /**
     * Delete one Customer by id curl -i -X DELETE
     * http://localhost:8080/pa165/rest/customers/1
     *
     * @param id identifier for Customer
     * @throws ResourceNotFoundException if the customer does not exist
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public final void deleteCustomer(@PathVariable("id") long id) throws ResourceNotFoundException {
        logger.debug("rest deleteCustomer({})", id);
        try {
            CustomerDTO customerDTO = customerFacade.findCustomerById(id);
            customerFacade.deleteCustomer(customerDTO);
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Create a new Customer by POST method
     * 
     * curl -X POST -i -H "Content-Type: application/json" --data
     * '{"name":"Feri","surname":"Mrkvicka","email":"feri.mrkvicka@neco.com","addressFirstLine":"bla",
     * "addressCity":"Tramtaria","addressCountry":"Narnia","addressPostalCode":"62400"}'
     * http://localhost:8080/pa165/rest/customers/create
     *
     * @param customer CustomerCreateDTO with required fields for creation
     * @return the created customer CustomerDTO
     * @throws ResourceAlreadyExistsException if the customer already exists
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO createCustomer(@RequestBody CustomerCreateDTO customer) throws ResourceAlreadyExistsException {
        try {
            Long id = customerFacade.createCustomer(customer);
            return customerFacade.findCustomerById(id);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    /**
     * Update customer by patch method 
     * 
     * curl -X PATCH -i -H
     * "Content-Type: application/json" --data '{"email":"neco@neco.cz"}'
     * http://localhost:8080/pa165/rest/customers/1
     *
     * @param id       id of customer to update
     * @return the updated customer CustomerDTO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO updateCustomer(@PathVariable("id") long id, @RequestBody CustomerDTO newValues) {
        
        CustomerDTO current = customerFacade.findCustomerById(id);
        if (newValues.getName()!=null) current.setName(newValues.getName());
        if (newValues.getSurname()!=null) current.setSurname(newValues.getSurname());
        if (newValues.getAddressCity()!=null) current.setAddressCity(newValues.getAddressCity());
        if (newValues.getAddressCountry()!=null) current.setAddressCountry(newValues.getAddressCountry());
        if (newValues.getAddressFirstLine()!=null) current.setAddressFirstLine(newValues.getAddressFirstLine());
        if (newValues.getAddressPostalCode()!=null) current.setAddressPostalCode(newValues.getAddressPostalCode());
        if (newValues.getAddressSecondLine()!=null) current.setAddressSecondLine(newValues.getAddressSecondLine());
        if (newValues.getEmail()!=null) current.setEmail(newValues.getEmail());
        if (newValues.getPhoneNumber()!=null) current.setPhoneNumber(newValues.getPhoneNumber());
        
        customerFacade.update(current);
        return current;
    }
}
