package com.petrique.openstack.data;

import com.petrique.openstack.DatabaseHelper;
import com.petrique.openstack.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password, DatabaseHelper db) {

        try {
            // TODO: handle loggedInUser authentication
            /*
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
             */
           boolean res = db.checkUser(username, password);

            if(res == true)
            {
                LoggedInUser userData = new LoggedInUser(java.util.UUID.randomUUID().toString(),username);
                return new Result.Success<>(userData);
            }
            else
            {
                throw new Exception();
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
