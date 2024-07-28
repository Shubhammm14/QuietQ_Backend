package com.exaple.quiet_Q.controller;

import com.exaple.quiet_Q.exception.ChatException;
import com.exaple.quiet_Q.exception.UserExcepition;
import com.exaple.quiet_Q.modal.Chat;
import com.exaple.quiet_Q.request.GroupChatRequest;
import com.exaple.quiet_Q.request.SingleChatRequest;
import com.exaple.quiet_Q.response.ApiResponse;
import com.exaple.quiet_Q.services.ChatService;
import com.exaple.quiet_Q.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;






import java.util.List;

    @RestController
    @RequestMapping("/api/chats")
    public class ChatController {
        @Autowired
        private UserService userService;
        @Autowired
        private ChatService chatService;

        @PostMapping("/single")
        public ResponseEntity<Chat> chatCreateHandler(@RequestBody SingleChatRequest singleChatRequest, @RequestHeader("Authorization") String jwt) throws UserExcepition, UserExcepition {
            Chat chat=chatService.createChat(userService.findUserByJwt(jwt),singleChatRequest.getUserId());
            return new ResponseEntity<>(chat, HttpStatus.CREATED);
        }
        @PostMapping("/group")
        public ResponseEntity<Chat> groupChatCreateHandler(@RequestBody GroupChatRequest groupeChatRequest, @RequestHeader("Authorization") String jwt) throws UserExcepition, UserExcepition {
            Chat chat=chatService.createGroup(groupeChatRequest,userService.findUserByJwt(jwt));
            return new ResponseEntity<>(chat, HttpStatus.CREATED);
        }
        @GetMapping("/{chatId}")
        public ResponseEntity<Chat> findChatByIdHandler(@PathVariable("chatId") Long chatId) throws ChatException, ChatException {
            Chat chat=chatService.findChatById(chatId);
            return new ResponseEntity<>(chat,HttpStatus.OK);
        }
        @GetMapping("/user")
        public ResponseEntity<List<Chat>> findChatByUserIdHandler(@RequestHeader("Authorization") String jwt) throws UserExcepition, UserExcepition {
            List<Chat> chat=chatService.findAllChatByUserId(userService.findUserByJwt(jwt).getId());
            return new ResponseEntity<>(chat, HttpStatus.CREATED);
        }
        @PutMapping("/{chatId}/add/{userId}")
        public ResponseEntity<Chat> addUserToGroupHandler(@PathVariable("chatId")Long chatId,@PathVariable ("userId")Long userId,@RequestHeader ("Authentication")String jwt) throws UserExcepition, ChatException, UserExcepition {
            Chat chat=chatService.addUserToGroup(chatId,userId,userService.findUserByJwt(jwt));
            return new ResponseEntity<>(chat,HttpStatus.OK);
        }
        @PutMapping("/{chatId}/rename/{groupName}")
        public ResponseEntity<Chat> renameHandler(@PathVariable("chatId")Long chatId,@PathVariable ("groupName")String groupName,@RequestHeader ("Authentication")String jwt) throws UserExcepition, ChatException, UserExcepition {
            Chat chat=chatService.renameGroup(chatId,groupName,userService.findUserByJwt(jwt));
            return new ResponseEntity<>(chat,HttpStatus.OK);
        }

        @PutMapping("/{chatId}/rename/{userId}")
        public ResponseEntity<Chat> removeFromGroupHandler(@PathVariable("chatId")Long chatId,@PathVariable ("userId")Long userId,@RequestHeader ("Authentication")String jwt) throws UserExcepition, ChatException, UserExcepition {
            Chat chat=chatService.removeFromGroup(userId,chatId,userService.findUserByJwt(jwt));
            return new ResponseEntity<>(chat,HttpStatus.OK);
        }
        @DeleteMapping("/delete/{chatId}")
        public ResponseEntity<ApiResponse> chatDeleteHandler(@PathVariable("chatId")Long chatId, @RequestHeader ("Authentication")String jwt) throws UserExcepition, ChatException, UserExcepition {
            chatService.deleteChat(chatId,userService.findUserByJwt(jwt).getId());
            ApiResponse apiResponse=new ApiResponse("Chat deleted Successfully...",true);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }


}
