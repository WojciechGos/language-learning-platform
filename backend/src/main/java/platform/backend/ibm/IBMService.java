package platform.backend.ibm;

import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RequiredArgsConstructor
@Service
public class IBMService {

    @Value("${ibm.api.sst.url}")
    String ibmSttUrl;
    @Value("${ibm.api.key}")
    String ibmApiKey;

    public byte[] convertTextToSpeech(String text) {
        System.out.println("api key:" + ibmApiKey);
        IamAuthenticator authenticator = new IamAuthenticator(ibmApiKey);
        TextToSpeech textToSpeech = new TextToSpeech(authenticator);
        System.out.println("ibm  url " + ibmSttUrl);
        textToSpeech.setServiceUrl(ibmSttUrl);


        HttpConfigOptions configOptions = new HttpConfigOptions.Builder()
                .disableSslVerification(true)
                .build();
        textToSpeech.configureClient(configOptions);



        try {
            SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
                    .text("Hello world")
                    .accept("audio/wav")
                    .voice("en-GB_KateVoice")
                    .build();
            InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute().getResult();
            InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            byte[] audioBytes = outputStream.toByteArray();

            outputStream.close();
            in.close();
            inputStream.close();

            return audioBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;


    }

}
