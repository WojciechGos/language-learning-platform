package platform.backend.ibm;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/stt")
public class STTController {


    private final IBMService sstService;

//    @GetMapping
//    public ResponseEntity<HttpStatus> smt (){
//
//        sstService.convertTextToSpeech("Hello");
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
