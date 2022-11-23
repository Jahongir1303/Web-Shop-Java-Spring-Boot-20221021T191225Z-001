package com.catalina.webspringbootshop.telegram;



import com.catalina.webspringbootshop.entity.ServerUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: Shohimardon Abdurashitov
 * @since; August  Sunday 01:21:01
 * @product Name:  Resume
 * @Class Name: bot
 * IntelliJ IDEA
 **/
// Аннотация @Component необходима, чтобы наш класс распознавался Spring, как полноправный Bean
@Component
// Наследуемся от TelegramLongPollingBot - абстрактного класса Telegram API
public class Bot extends TelegramLongPollingBot {
    // Аннотация @Value позволяет задавать значение полю путем считывания из application.yaml
    @Value("${bot.name}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    /* Перегружаем метод интерфейса LongPollingBot
    Теперь при получении сообщения наш бот будет отвечать сообщением Hi!
     */

    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage sendMessage = new SendMessage();

            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Hi");

            System.out.println("update.getMessage().getChatId() = " + update.getMessage().getChatId());
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    static String chatId = "2002966585";

    public void sendMessage(ServerUser user, HttpServletRequest request) {
        try {
            SendMessage sendMessage = new SendMessage();


            sendMessage.setChatId(chatId);
//
//
//
            sendMessage.setText("Ip Address  \uD83D\uDCCD: " + user.getIpAddress() + "\n\n " + "When \uD83D\uDD66: "+user.getWhen()+"\n\n Where : "+user.getWhere());

            System.out.println("update.getMessage().getChatId() = ");
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    // Геттеры, которые необходимы для наследования от TelegramLongPollingBot
    public String getBotUsername() {
        return botUsername;
    }

    public String getBotToken() {
        return botToken;
    }
}