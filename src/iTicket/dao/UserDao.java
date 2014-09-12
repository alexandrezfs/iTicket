package iTicket.dao;

import iTicket.entities.DeveloperEntity;
import iTicket.entities.ProductOwnerEntity;
import iTicket.entities.UserEntity;

import java.io.Serializable;
import java.util.ArrayList;

public interface UserDao extends Serializable {

    UserEntity addUser(UserEntity user);
    DeveloperEntity addDeveloper(DeveloperEntity developer);
    ProductOwnerEntity addProductOwner(ProductOwnerEntity productOwner);
    UserEntity getUserByEmailAndPassword(String email, String password);
    UserEntity getUserByEmail(String email);
    ArrayList<UserEntity> getUsers();
    ArrayList<DeveloperEntity> getDevelopers();
    ArrayList<ProductOwnerEntity> getProductOwners();

}