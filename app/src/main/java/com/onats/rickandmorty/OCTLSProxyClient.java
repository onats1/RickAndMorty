package com.onats.rickandmorty;

import android.os.Build;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SNIHostName;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import io.reactivex.rxjava3.core.Observable;
import timber.log.Timber;

public class OCTLSProxyClient {

    public Observable<String> sendRxMessage(byte[] messageToSend) {
        return Observable.<String>create(emitter -> {
                    SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

// Initialize with the system's default trust store
                    sslContext.init(
                            null, // default key managers
                            null, // default trust managers (uses system trust store)
                            new java.security.SecureRandom()
                    );

                    SSLSocketFactory factory = sslContext.getSocketFactory();

                    try (SSLSocket client = (SSLSocket) factory.createSocket("atm.dcprocessing.net", 5549)) {
                        // Set supported protocols
                        client.setEnabledProtocols(new String[]{"TLSv1.2"});

                        // ✅ Explicitly set hostname for SNI + verification
                        SSLParameters sslParams = client.getSSLParameters();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            sslParams.setServerNames(Collections.singletonList(new SNIHostName("atm.dcprocessing.net")));
                        }
                        client.setSSLParameters(sslParams);

                        Timber.i("Initiating TLS 1.2 connection...");
                        client.startHandshake(); // triggers full SSL handshake + cert validation

                        Timber.i("Connected with %s", Arrays.toString(client.getEnabledProtocols()));

                        try (OutputStream out = client.getOutputStream();
                             InputStream inStream = client.getInputStream();
                             BufferedInputStream bufferedIn = new BufferedInputStream(inStream)) {

                            // Send message
                            out.write(messageToSend);
                            out.flush();
                            Timber.i("Message sent");

                            // Read response
                            byte[] buffer = new byte[1024];
                            StringBuilder response = new StringBuilder();
                            int bytesRead;
                            while ((bytesRead = bufferedIn.read(buffer)) != -1) {
                                String chunk = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                                response.append(chunk);
                                if (bufferedIn.available() == 0) break;
                            }

                            String finalOutput = response.toString();
                            Timber.i("Message received: %s", finalOutput);

                            emitter.onNext(finalOutput);
                        }

                        emitter.onComplete();
                        Timber.i("Connection closed");

                    } catch (SSLHandshakeException e) {
                        Timber.e(e, "TLS Handshake failed. Possible missing CA or invalid cert chain.");
                        emitter.onError(e);
                    } catch (IOException e) {
                        Timber.e(e);
                        emitter.onError(e);
                    }
                }).timeout(60000, TimeUnit.MILLISECONDS)
                .onErrorComplete(bool -> false);
    }

    public Observable<String> sendBackgroundMessage(byte[] messageToSend) {
        return Observable.<String>create(emitter -> {
                    SSLContext sslContext = SSLContext.getInstance("TLSv1.2");

// Initialize with the system's default trust store
                    sslContext.init(
                            null, // default key managers
                            null, // default trust managers (uses system trust store)
                            new java.security.SecureRandom()
                    );

                    SSLSocketFactory factory = sslContext.getSocketFactory();

                    try (SSLSocket client = (SSLSocket) factory.createSocket("atm.dcprocessing.net", 5549)) {
                        // Set supported protocols
                        client.setEnabledProtocols(new String[]{"TLSv1.2"});

                        // ✅ Explicitly set hostname for SNI + verification
                        SSLParameters sslParams = client.getSSLParameters();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            sslParams.setServerNames(Collections.singletonList(new SNIHostName("atm.dcprocessing.net")));
                        }
                        client.setSSLParameters(sslParams);

                        Timber.i("Initiating TLS 1.2 connection...");
                        client.startHandshake(); // triggers full SSL handshake + cert validation

                        Timber.i("Connected with %s", Arrays.toString(client.getEnabledProtocols()));

                        try (OutputStream out = client.getOutputStream();
                             InputStream inStream = client.getInputStream();
                             BufferedInputStream bufferedIn = new BufferedInputStream(inStream)) {

                            // Send message
                            out.write(messageToSend);
                            out.flush();
                            Timber.i("Message sent");

                            // Read response
                            byte[] buffer = new byte[1024];
                            StringBuilder response = new StringBuilder();
                            int bytesRead;
                            while ((bytesRead = bufferedIn.read(buffer)) != -1) {
                                String chunk = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
                                response.append(chunk);
                                if (bufferedIn.available() == 0) break;
                            }

                            String finalOutput = response.toString();
                            Timber.i("Message received: %s", finalOutput);

                            emitter.onNext(finalOutput);
                        }

                        emitter.onComplete();
                        Timber.i("Connection closed");

                    } catch (SSLHandshakeException e) {
                        Timber.e(e, "TLS Handshake failed. Possible missing CA or invalid cert chain.");
                        emitter.onError(e);
                    } catch (IOException e) {
                        Timber.e(e);
                        emitter.onError(e);
                    }


                })
                .timeout(60000, TimeUnit.MILLISECONDS)
                .onErrorComplete(bool -> false);
    }

}