package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_AGE = 18;
    private static final int MIN_LOGIN_PASSWORD_LENGTH = 6;
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getLogin() == null) {
            throw new RegistrationException("Login can't be null");
        }
        if (user.getLogin().length() < MIN_LOGIN_PASSWORD_LENGTH) {
            throw new RegistrationException("Login is too short. Min allowed length is "
                    + MIN_LOGIN_PASSWORD_LENGTH);
        }
        if (user.getPassword() == null) {
            throw new RegistrationException("Password can't be null");
        }
        if (user.getPassword().length() < MIN_LOGIN_PASSWORD_LENGTH) {
            throw new RegistrationException("Password is too short. Min allowed length is "
                    + MIN_LOGIN_PASSWORD_LENGTH);
        }
        if (user.getAge() == null) {
            throw new RegistrationException("Age can't be null");
        }
        if (user.getAge() < MIN_AGE) {
            throw new RegistrationException("Not valid age " + user.getAge()
                    + ". Min allowed age is " + MIN_AGE);
        }
        if (Storage.people.contains(user)) {
            throw new RegistrationException("User is already exist");
        }
        return storageDao.add(user);
    }
}
