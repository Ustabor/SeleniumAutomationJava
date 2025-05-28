package utils;

import org.seimicrawler.xpath.JXDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.regex.Pattern;

public class NewXmlParser {

    private final JXDocument document;

    public NewXmlParser(String html) {
        document = JXDocument.create(html);
    }

    private static final Logger logger = LoggerFactory.getLogger(NewXmlParser.class);

    public String getUrl(String phoneNumber) {
        var sms = getSmsText(phoneNumber, XmlParser.getTextByKey("SmsServiceUrl"));
        var split = sms.split(": ");

        if (split.length > 1) {
            var firstIteration = sms.split(": ")[1].replace("</td>", "");
            var split1 = firstIteration.split(". ");
            if (split1.length > 0) {
                return split1[0];
            }
        }
        return "";
    }

    public String getSmsCode(String phoneNumber) {
        var sms = getSmsText(phoneNumber, XmlParser.getTextByKey("SmsAuthCode"));

        var result = Pattern.compile("\\d{2,}").matcher(sms);
        if (result.find()) {
            return result.group(0);
        } else {
            return "";
        }
    }

    public String getSmsPassword(String phoneNumber, String text) {
        var sms = getSmsText(phoneNumber, text);
        return sms.substring(sms.lastIndexOf(':') + 2, sms.lastIndexOf('<'));
    }

    public String getSmsText(String phoneNumber, String text) {
        String textXpath = "//tr[./td[contains(text(), '%s')]]//td[contains(text(), '%s')]";
        String xpath = String.format(textXpath, phoneNumber, text);
        var nodes = document.selN(xpath);

        if (nodes.size() == 0) {
            return "";
        }

        return nodes.get(0).toString();
    }

    public String getId(String phoneNumber) {
        String textXPath = "//tr/td/a[contains(text(), '%s')]";
        String xpath = String.format(textXPath, phoneNumber);

        var nodes = document.selN(xpath);

        if (nodes.size() == 0) {
            return "";
        }

        var href = nodes.get(0).asElement().attr("href");
        var arr = href.split("/");

        return arr[arr.length-1];
    }
}
