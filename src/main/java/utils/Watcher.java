package utils;

import entities.Master;
import entities.Category;
import entities.User;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Watcher extends TestWatcher {

    public List<User> users = new ArrayList<>();
    public User customer;
    public Category category;

    @Override
    protected void finished(Description description) {
        try {
            cleanUp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void cleanUp() throws IOException {
        users.forEach((user) -> {
            if (user.getProfileId() != null) {

                switch (user.getClass().getSimpleName()) {
                    case "Master":
                        try {
                            Admin.getInstance().deleteMaster(user.getProfileId());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    case "User":
                        try {
                            Admin.getInstance().deleteCustomer(user.getProfileId());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                }
            }
        });

        if (category != null && category.getSystemId() != null) {
            Admin.getInstance().deleteCategory(category.getSystemId());
        }
    }

    public Master getMaster() {
        return getMaster(0);
    }

    public Master getMaster(int index) {
        var masters = users
                .stream()
                .filter(user -> user.getClass().getSimpleName().equals("Master"))
                .toArray(User[]::new);

        return (Master) masters[index];
    }
}
