package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DaoFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBO {
    private CustomerDAO dao= DaoFactory.getInstance()
            .getDao(DaoFactory.DAOType.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        return dao.save(new Customer(dto.getId(),
                dto.getName(),dto.getAddress(),dto.getSalary()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return dao.update(new Customer(dto.getId(),
                dto.getName(),dto.getAddress(),dto.getSalary()));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String id) throws Exception {
        Customer c=dao.get(id);
        return new CustomerDTO(c.getId(),
                c.getName(),c.getAddress(),c.getSalary());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws Exception {
        List<Customer> cList=dao.getAll();
        ArrayList<CustomerDTO> dtoList= new ArrayList();
        for (Customer c : cList) {
            dtoList.add(new CustomerDTO(c.getId(),
                    c.getName(),c.getAddress(),c.getSalary()));
        }
        return dtoList;
    }
}
