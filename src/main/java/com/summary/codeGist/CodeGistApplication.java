import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatbotResponse {
	private List<Content> content;
	private List<Object> sources;
	private ChatbotData chatbotData;

	@Data
	public static class Content {
		private String type;
		private String value;
	}

	@Data
	public static class ChatbotData {
		private ChatbotMetadata chatbotMetadata;
	}

	@Data
	public static class ChatbotMetadata {
		private String agentType;
		private ModelMetadata modelMetadata;
		private PromptMetadata promptMetadata;
		private String conversationId;
		private String questionId;
		private String chatbotId;
	}

	@Data
	public static class ModelMetadata {
		private String deploymentId;
		private String apiVersion;
	}

	@Data
	public static class PromptMetadata {
		private List<Prompt> prompt;
		private OpenAiChatCompletionParameters openAiChatCompletionParameters;
	}

	@Data
	public static class Prompt {
		private String role;
		private String content;
	}

	@Data
	public static class OpenAiChatCompletionParameters {
		private double temperature;
		private int maxTokens;
		private double presencePenalty;
		private double frequencyPenalty;
		private List<String> stop;

		@JsonProperty("top_p")
		private Double topP;
	}
}
