package edu.iu.adicher.primesservice.repository;

import edu.iu.adicher.primesservice.model.Customer;

import java.io.IOException;

public interface IAuthenticationRepository {
    boolean save(Customer customer) throws IOException;
        Customer findByUsername(String username) throws IOException;

}
