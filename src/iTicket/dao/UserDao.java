package iTicket.dao;

import iTicket.entities.DeveloperEntity;
import iTicket.entities.ProductOwnerEntity;
import iTicket.entities.UserEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public interface UserDao extends Serializable {

    UserEntity addUser(UserEntity user);
    DeveloperEntity addDeveloper(DeveloperEntity developer);
    ProductOwnerEntity addProductOwner(ProductOwnerEntity productOwner);
    UserEntity getUserByEmailAndPassword(String email, String password);
    UserEntity getUserByEmail(String email);
    UserEntity getUserById(int user_id);
    List<UserEntity> getUsers();
    List<DeveloperEntity> getDevelopers();
    List<ProductOwnerEntity> getProductOwners();

}