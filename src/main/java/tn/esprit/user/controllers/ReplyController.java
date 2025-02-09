package tn.esprit.user.controllers;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.user.entities.Reply;
import tn.esprit.user.services.Interfaces.IService;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600, allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    IService iService;
    @PostMapping(value = "/saveReply")
    private Reply addReply(@RequestBody Reply reply)  {
      return iService.addReply(reply);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Reply>> getAllReplies() {
        List<Reply> replies = iService.getAllReplies();
        return new ResponseEntity<>(replies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reply> getReplyById(@PathVariable String id) {
        Optional<Reply> reply = iService.getReplyById(id);
        return reply.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Reply> updateReply(@PathVariable String id, @RequestBody Reply updatedReply) {
        Reply updated = iService.updateReply(id, updatedReply);
        if (updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReply(@PathVariable String id) {
        boolean deleted = iService.deleteReply(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
