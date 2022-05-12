package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rest.dtos.ResponseDTO;
import service.DailyChallengeService;

import javax.validation.Valid;

@RestController
@RequestMapping("daily/challenge/")
public class DailyChallenge {

    @Autowired
    private DailyChallengeService dailyChallengeService;

    @GetMapping("1/{test}")
    public ResponseEntity<ResponseDTO<String>> getCountVowelsAndConstants(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.countVowelsAndConstants(test)),HttpStatus.OK);
    }

    @GetMapping("2/{test}")
    public ResponseEntity<ResponseDTO<String>> getIsPalindrome(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.isPalindrome(test)),HttpStatus.OK);
    }

    @GetMapping("3/{test}")
    public ResponseEntity<ResponseDTO<String>> getCommonPrefix(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.getCommonPrefix(test)),HttpStatus.OK);
    }

    @GetMapping("4/{test}")
    public ResponseEntity<ResponseDTO<String>> getWithoutDuplicates(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.removeDuplicates(test)),HttpStatus.OK);
    }

    @GetMapping("5/{test}")
    public ResponseEntity<ResponseDTO<String>> getMostAppearances(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.mostAppearances(test)),HttpStatus.OK);
    }

    @GetMapping("6/{test}")
    public ResponseEntity<ResponseDTO<String>> getWithoutNulls(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.checkinNullReferences(test)),HttpStatus.OK);
    }

    @GetMapping("7/{test}")
    public ResponseEntity<ResponseDTO<String>> getAlertOfNullPointer(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.checkinNullPointer(test)),HttpStatus.OK);
    }

    @GetMapping("8/{test}")
    public ResponseEntity<ResponseDTO<String>> getCurrentException(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.checkinNullGetSpecificException(test)),HttpStatus.OK);
    }

    @GetMapping("9/{test}")
    public ResponseEntity<ResponseDTO<String>> getBadData(@PathVariable("test") @Valid final String test) {
        return new ResponseEntity<>(new ResponseDTO<>(Boolean.TRUE,dailyChallengeService.avoidingBadDataInmObjs(test)),HttpStatus.OK);
    }
}
