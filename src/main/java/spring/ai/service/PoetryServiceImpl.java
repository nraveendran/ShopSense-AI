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
import spring.ai.controller.PoetryController;
import spring.ai.tools.DayTimeCalculatorTool;

@Service
public class PoetryServiceImpl implements PoetryService {




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

    public PoetryServiceImpl() {
    }


    @Override
    public String getPromptResponse(String model, String userInput) {
//
//        ChatClient chatClient;
//
//        if (model.equals("openai")) {
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
    public PoetryController.StructuredJoke generateJoke(String userInput) {


        PoetryController.StructuredJoke structuredJoke = openAiChatClient.prompt()
                .user(u -> u.text("""
                                tell me a joke about {userInput} and structure it into subject,
                                setup and punchline. Provide a rating for the joke between 1-100
                                 and also an explanation of the punch line""")
                        .param("userInput", userInput))
                .advisors(new SimpleLoggerAdvisor(),chatMemoryAdvisor)
                .call().entity(PoetryController.StructuredJoke.class);
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
    public String relatedJokes(String userInput) {



        String dataTime = openAiChatClient.prompt()
                .user(u -> u.text("""
                                Can you set an alarm for 10 minutes from now?""")
                        .param("userInput", userInput))
                .advisors(new SimpleLoggerAdvisor())
                .tools(new DayTimeCalculatorTool())
                .call().content() ;



        return dataTime;
    }

}