package com.example.CoEduServer.controller;

import com.example.CoEduServer.dto.ChatRoom;
import com.example.CoEduServer.dto.req.ChatReqDTO;
import com.example.CoEduServer.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestBody ChatReqDTO chatReqDTO){
        return chatService.createRoom(chatReqDTO.getFileId(), chatReqDTO.getFileHash());
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}
