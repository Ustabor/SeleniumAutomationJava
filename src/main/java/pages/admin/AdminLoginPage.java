package pages.admin;

import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.devtools.v137.log.Log;
import org.openqa.selenium.devtools.v137.network.Network;
import org.openqa.selenium.devtools.v137.network.model.Headers;
import org.openqa.selenium.support.FindBy;
import utils.Config;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AdminLoginPage extends BaseAdminPage {

    @FindBy(xpath = "//input[@id='form_data_login']")
    private WebElementFacade loginInput;

    @FindBy(xpath = "//input[@id='form_data_password']")
    private WebElementFacade passwordInput;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElementFacade loginBtn;

    public void openPage() {
        var url = (Config.getAdminUrl() + "login?url=%2F");

        var devTools = getDevTools();
        var auth = "admin:U*H>Fdc~aDE1";
        var encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        Map<String, Object> headers = new HashMap<>();
        headers.put("Authorization", "Basic " + encodedAuth);

        devTools.createSession();
        devTools.send(Log.disable());
        devTools.send(
                Network.enable(
                        java.util.Optional.empty(),
                        java.util.Optional.empty(),
                        java.util.Optional.empty()));
        devTools.send(
                Network.setExtraHTTPHeaders(new Headers(headers)));

        getDriver().get(url);
    }

    public void enterLogin(String login) {
        loginInput.sendKeys(login);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginBtn() {
        loginBtn.click();
    }
}
