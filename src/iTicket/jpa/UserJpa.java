package iTicket.jpa;

import iTicket.dao.UserDao;
import iTicket.entities.DeveloperEntity;
import iTicket.entities.ProductOwnerEntity;
import iTicket.entities.UserEntity;

import java.util.ArrayList;

public class UserJpa implements UserDao {

    @Override
    public UserEntity addUser(UserEntity user) {
        return null;
    }

    @Override
    public UserEntity getByEmailAndPassword(String email, String password) {
        return null;
    }

    @Override
    public ArrayList<UserEntity> getUsers() {
        return null;
    }

    @Override
    public ArrayList<DeveloperEntity> getDevelopers() {
        return null;
    }

    @Override
    public ArrayList<ProductOwnerEntity> getProductOwners() {
        return null;
    }
}
