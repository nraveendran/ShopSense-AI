package spring.ai.configuration;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.NoopApiKey;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaApiHelper;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Value("${spring.ai.openai.api-key}")
    String openAIApiKey;

    @Bean
    public OpenAiChatModel openAIchatModel() {

        OpenAiApi l = OpenAiApi.builder().apiKey(openAIApiKey).build();
        OpenAiChatOptions chatOptions = OpenAiChatOptions.builder().store(true).model("gpt-4o").build();
        return OpenAiChatModel.builder().defaultOptions(chatOptions).openAiApi(l).build();
    }

    @Bean
    public ChatClient openAiChatClient(@Qualifier("openAIchatModel") OpenAiChatModel openAIchatModel) {
        return ChatClient.builder(openAIchatModel).build();
    }




    @Bean
    public OllamaChatModel mistralModel() {
        return OllamaChatModel.builder()
                .ollamaApi(OllamaApi.builder().build())
                .toolCallingManager(ToolCallingManager.builder().build())
                .defaultOptions(OllamaOptions.builder().model("mistral").build())
                .build();
    }

    @Bean
    public ChatClient mistralChatClient(@Qualifier("mistralModel") OllamaChatModel mistralModel) {
        return ChatClient.builder(mistralModel).
                defaultSystem("You are friendly but smart shopping assistant").
                build();
    }
}