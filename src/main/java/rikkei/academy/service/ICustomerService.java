package rikkei.academy.service;

import rikkei.academy.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    Customer findById(Long id);
    void deleteById(Long id);
}
