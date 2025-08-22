package utils;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Executor;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.ssl.SSLContextBuilder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class RequestsExecutor {
    public static Executor create() {
        try {
            var redirect = new LaxRedirectStrategy();
            var ssl = new SSLContextBuilder()
                    .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
                    .build();
            var config = RequestConfig
                    .custom()
                    .setCookieSpec(CookieSpecs.STANDARD)
                    .build();
            var credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(
                    AuthScope.ANY,
                    new UsernamePasswordCredentials("admin", "U*H>Fdc~aDE1"));
            return Executor.newInstance(
                    HttpClientBuilder
                            .create()
                            .setDefaultCredentialsProvider(credentialsProvider)
                            .setRedirectStrategy(redirect)
                            .setSSLContext(ssl)
                            .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                            .setDefaultRequestConfig(config)
                            .build());
        } catch (KeyManagementException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
