package spring.ai.configuration;

import org.springframework.ai.document.MetadataMode;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.openai.OpenAiEmbeddingOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.retry.RetryUtils;
import org.springframework.ai.vectorstore.SearchRequest;

import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SimpleVectorStoreConfiguration {

    @Value("${spring.ai.openai.api-key}")
    String openAIApiKey;

    @Bean("openAiEmbeddingModel")
    public EmbeddingModel embeddingModel(){
        var embeddingModel = new OpenAiEmbeddingModel(
                OpenAiApi.builder().apiKey(openAIApiKey).build(),
                MetadataMode.EMBED,
                OpenAiEmbeddingOptions.builder()
                        .model("text-embedding-ada-002")
                        .user("user-6")
                        .build(),
                RetryUtils.DEFAULT_RETRY_TEMPLATE);

        return embeddingModel;
    }


    @Bean("simpleVectorStore")
    public VectorStore vectorStore(@Qualifier("openAiEmbeddingModel") EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }
}