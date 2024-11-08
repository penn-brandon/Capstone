package com.psugv.capstone.chat.controller;

import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.chat.service.IChatService;
import com.psugv.capstone.exception.NoQueryResultException;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"userModel", "chatRoomName"})
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    IChatService chatService;

    @Autowired
    ILoginService loginService;

    @GetMapping(path = "/chat")
    public String toChatPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        LOGGER.trace("Storing user to session: " + username);
        LOGGER.trace("Storing user to session: " + authentication.getCredentials());
        UserModel userModel = loginService.getUserByUsername(username);
        model.addAttribute("userModel", userModel);

        chatService.deselectChatRoom(userModel);

        return "/main/chat";
    }

    /**
     * @param inputMap key: message, value: message send by user
     *                 key: room, value: chat room ID
     * @return
     */
    @PostMapping(path = "/send", consumes = "application/json")
    public @ResponseBody String sendMessage(@RequestBody Map<String, String> inputMap, @SessionAttribute UserModel userModel) {

        if (inputMap.get("message") == null || inputMap.get("message").isEmpty() || inputMap.get("room") == null || inputMap.get("room").isEmpty()) {

            LOGGER.debug("message: " + inputMap.get("message"));
            LOGGER.debug("room: " + inputMap.get("room"));

            return "MESSAGE SENDING FAILURE";
        }

        Boolean result = chatService.sendMessage(inputMap.get("message"), userModel, inputMap.get("room"));

        return result.toString();
    }

    @GetMapping(path = "/select")
    public String selectChatBox(@RequestHeader String chatRoomID, @SessionAttribute("userModel") UserModel userModel, Model model) {

        ChatRoomName result;

        LOGGER.debug("Incoming chat room ID is: " + chatRoomID);

        try {
            result = chatService.selectChatRoom(chatRoomID, userModel);

            if (result == null) {

                LOGGER.error("Chat room name not found for chat room ID " + chatRoomID + ", and user ID " + userModel.getId());

                return "redirect:/error";
            }
            model.addAttribute("chatRoomName", result);

            return "redirect:/loadMessage";

        } catch (NoQueryResultException e) {

            return "redirect:/error";
        }
    }

    @GetMapping(path = "/loadMessage", produces = "application/json")
    public @ResponseBody List<Message> loadHistoryMessage(@SessionAttribute("chatRoomName") ChatRoomName chatRoomName) {

        List<Message> result;

        Integer chatRoomId = chatRoomName.getChatRoom().getId();

        result = chatService.loadHistoryMessage(chatRoomId);

        if (result == null) {

            return new ArrayList<>();
        }
        LOGGER.debug("MESSAGE LENGTH" + result.size());
        return result;
    }

    @GetMapping(path = "/loadAllChatRoomName", produces = "application/json")
    public @ResponseBody List<ChatRoomName> loadAllChatRoomName(UserModel userModel) {

        return chatService.getAllChatRoomName(userModel);
    }

    @GetMapping(path = "/searchUsers", produces = "application/json")
    public @ResponseBody List<UserModel> searchUser(@RequestParam String username) {

        List<UserModel> result = chatService.searchUser(username);

        return result;
    }

    /**
     * @param inputMap key: id, value: user id
     *                 key: name, value: name of user
     *                 key username, value username
     */
    @PostMapping(path = "/createNewChatRoom", consumes = "application/json", produces = "application/json")
    public @ResponseBody ChatRoomName createNewChatRoom(@RequestBody Map<String, String> inputMap, @SessionAttribute("userModel") UserModel userModel, Model model) {

        ChatRoomName newchatRoomName = chatService.createChatRoom(inputMap, userModel);

        model.addAttribute("chatRoomName", newchatRoomName);

        return newchatRoomName;
    }
}
