package spring.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;

import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;

import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ai.controller.ShopSenseController;

@Service
public class ShopSenseServiceImpl implements ShopSenseService {



    ChatMemoryRepository chatMemoryRepository = new InMemoryChatMemoryRepository();

    ChatMemory chatMemory = MessageWindowChatMemory.builder()
            .chatMemoryRepository(chatMemoryRepository)
            .maxMessages(10)
            .build();

    PromptChatMemoryAdvisor chatMemoryAdvisor =  PromptChatMemoryAdvisor.builder(chatMemory).build();

    @Autowired
    ChatClient openAiChatClient;

    @Autowired
    VectorStore simpleVectorStore;

    @Autowired
    StoreAnalyticsService storeAnalyticsService;

    public ShopSenseServiceImpl() {
    }


    @Override
    public String getPromptResponse(String model, String userInput) {
//
//        ChatClient chatClient;
//
//        if (dto.equals("openai")) {
//            chatClient = openAiChatClient;
//        } else {
//            chatClient = mistralChatClient;
//        }
//
//        String response = chatClient.prompt()
//                .user("tell me a joke")
//                .call()
//                .content();
        return "response";
    }

    @Override
    public ShopSenseController.StructuredJoke generateJoke(String userInput) {


        ShopSenseController.StructuredJoke structuredJoke = openAiChatClient.prompt()
                .user(u -> u.text("""
                                tell me a joke about {userInput} and structure it into subject,
                                setup and punchline. Provide a rating for the joke between 1-100
                                 and also an explanation of the punch line""")
                        .param("userInput", userInput))
                .advisors(new SimpleLoggerAdvisor(),chatMemoryAdvisor)
                .call().entity(ShopSenseController.StructuredJoke.class);
//        Document jokeDocument = Document.builder().id(structuredJoke.subject()).text(structuredJoke.subject() + " " +
//                structuredJoke.setup() + " " + structuredJoke.punchLine() + " ").build();
//        simpleVectorStore.add(List.of(jokeDocument));
        return structuredJoke;
    }

//   One with Chat Memory
//    @Override
//      One with the QuestionAnswerAdvisor
//    public List<String> relatedJokes(String userInput) {
//
//        SearchRequest searchRequest = SearchRequest.builder().
//                query(userInput).similarityThreshold(0.6d).topK(2).build();
//        QuestionAnswerAdvisor questionAnswerAdvisor = QuestionAnswerAdvisor.
//                builder(simpleVectorStore).searchRequest(searchRequest).build();
//
//        List<String> jokes = openAiChatClient.prompt()
//                .user(u -> u.text("""
//                                What jokes have you told so far on {userInput}""")
//                        .param("userInput", userInput))
//                .advisors(new SimpleLoggerAdvisor(), questionAnswerAdvisor)
//                .call().entity(new ParameterizedTypeReference<ArrayList<String>>() {
//                });
//
//
//        return jokes;
//    }

//

    @Override
    public String answerQuestions(String userInput) {



        String dataTime = openAiChatClient.prompt()
                .user(u -> u.text("""
                                You are a helpful assistant specialized in analyzing shopping patterns based on historical receipt data.
                                
                                Your job is to answer the user's question as accurately as possible. You have access to several tools that can help retrieve item or store-related data.
                                
                                Follow these rules:
                                
                                1. **Never make up or fabricate data.** Always attempt to retrieve specific information using the tools provided.
                                2. If the question refers to a **specific item or store**, try retrieving information about it using the available tools.
                                3. If the tools cannot find that specific item or store, then:
                                   - Identify the **item category** or **store category** it most likely belongs to.
                                   - Use category-level tools to provide insights and data.
                                4. You may call tools **multiple times** if needed — don’t assume the first result is enough.
                                5. Provide clear, reasoned answers. If you can't find enough data to answer confidently, say so.
                                
                                Here is the user’s question: \s
                                **{userInput}**
                                
                                """)
                        .param("userInput", userInput))
                .advisors(new SimpleLoggerAdvisor())
                .tools(storeAnalyticsService)
                .call().content() ;



        return dataTime;
    }

}