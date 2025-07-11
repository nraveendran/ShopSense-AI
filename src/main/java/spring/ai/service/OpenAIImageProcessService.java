package spring.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import spring.ai.dto.StoreReceiptDTO;

@Service
public class OpenAIImageProcessService {

    @Autowired
    ChatClient openAiChatClient;
    @Value("${spring.ai.service.receipt.path}")
    String filePath;
    private StoreReceiptDTO storeReceiptDTO;

    public StoreReceiptDTO extractTextFromImage(String imageName) {

        var imagePath = filePath + "/" + imageName;

        var imageResource = new FileSystemResource(imagePath);

        Message message = UserMessage.builder().text("The attached image is a store receipt which " +
                        "contains store name, store address, purchase date, items purchased with quantity " +
                        "and price and total price.  Read the text from the image. " +
                        "Categorize the store into a storeCategory. e.g : grocery store, restaurant" +
                        "Categorize items into a itemCategory. Give Items a generic item name (genericItemName)"
                ).
                media(new Media(MimeTypeUtils.IMAGE_PNG, imageResource)).build();

        Prompt prompt = Prompt.builder().messages(message).build();

        StoreReceiptDTO storeReceiptDTO = openAiChatClient.prompt(prompt).
                advisors(new SimpleLoggerAdvisor()).call().entity(StoreReceiptDTO.class);
        return storeReceiptDTO;
    }

    public StoreReceiptDTO extractTextFromImage(byte[] data) {

        var imageResource = new ByteArrayResource(data);

        Message message = UserMessage.builder().text("The attached image is a store receipt which " +
                        "contains store name, store address, purchase date, items purchased with quantity " +
                        "and price and total price.  Read the text from the image. " +
                        "Categorize the store into a storeCategory. e.g : grocery store, restaurant" +
                        "Categorize items into a itemCategory. Give Items a generic item name (genericItemName)"
                ).
                media(new Media(MimeTypeUtils.IMAGE_PNG, imageResource)).build();

        Prompt prompt = Prompt.builder().messages(message).build();

        StoreReceiptDTO storeReceiptDTO = openAiChatClient.prompt(prompt).
                advisors(new SimpleLoggerAdvisor()).call().entity(StoreReceiptDTO.class);
        return storeReceiptDTO;
    }

}
