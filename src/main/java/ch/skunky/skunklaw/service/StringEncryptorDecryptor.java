package ch.skunky.skunklaw.service;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import org.bouncycastle.util.encoders.Base64Encoder;
import org.bouncycastle.util.encoders.UrlBase64Encoder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

@Service
public class StringEncryptorDecryptor {

    private final Aead aead;

    public StringEncryptorDecryptor() throws GeneralSecurityException {
        AeadConfig.register();
        KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_GCM);
        this.aead = AeadFactory.getPrimitive(keysetHandle);
    }

    public String encrypt(String content, String secretKey) throws GeneralSecurityException, IOException {
        byte[] ciphertext = aead.encrypt(content.getBytes(), secretKey.getBytes());
        String s = new String(ciphertext);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Base64Encoder().encode(s.getBytes(), 0, s.length(), out);
        return out.toString();
    }

    public String decrypt(String content, String secretKey) throws GeneralSecurityException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        new Base64Encoder().decode(content, out);
        String urlDecoded = out.toString();

        byte[] decrypted = aead.decrypt(urlDecoded.getBytes(), secretKey.getBytes());
        String s = new String(decrypted);
        return s;
    }

}
