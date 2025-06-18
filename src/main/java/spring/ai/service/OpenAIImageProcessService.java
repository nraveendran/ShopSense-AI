package spring.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.content.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import spring.ai.dto.StoreReceiptDTO;

@Service
public class OpenAIImageProcessService {

    @Autowired
    ChatClient openAiChatClient;

      public StoreReceiptDTO extractTextFromImage(String imageName) {
        var imageResource = new FileSystemResource("/Users/nidhishnair/Downloads/receipt1.jpg");

        Message message = UserMessage.builder().text("The attached image is a store receipt which " +
                        "contains store name, store address, purchase date, items purchased with quantity " +
                        "and price and total price.  Read the text from the image. " +
                        "Categorize the store into a storeCategory. e.g : grocery store, restaurant" +
                        "Categorize items into a itemCategory e.g: Swim Suit, Swim Cap, " +
                        "Toy, Tooth Paste, Pasta, Frozen Pizza etc."
                        ).
                media(new Media(MimeTypeUtils.IMAGE_PNG, imageResource)).build();

        Prompt prompt = Prompt.builder().messages(message).build();

       StoreReceiptDTO storeReceiptDTO =   openAiChatClient.prompt(prompt).
               advisors(new SimpleLoggerAdvisor()).call().entity(StoreReceiptDTO.class);
        return storeReceiptDTO;
    }
}
