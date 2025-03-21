package com.example.spring15.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.spring15.dto.GeminiRequest;
import com.example.spring15.dto.GeminiResponse;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import reactor.core.publisher.Mono;

@Service
public class GeminiService {
    private final WebClient webClient;
    private final Gson gson = new Gson();
    private final String apiKey;
    private final String url;
    
    //생성자에 필요한 data 3 개 전달받기 
    public GeminiService(WebClient.Builder builder, 
    		@Value("${gemini.key}") String apiKey,
    		@Value("${gemini.url}") String url  //서비스 객체가 생성되는 시점에 @Value 가 읽어와 지지 않는다.
    ) {
    	//전달 받은 내용을 필드에 저장하기 
	   this.apiKey=apiKey;
	   this.url=url;
	   this.webClient=builder.baseUrl(url).build();
    }
    public Mono<String> quiz2(Map<String, String> map){
    	/*
    	 * map 에는 quiz 와 answer 가 담겨있다
    	 * 
    	 * Gemini api 의 요청을 통해서 해당 answer 가 맞으면
    	 * { "isCorrect" : true , "comment" : "code 피드백"}
    	 * 을리면
    	 * {"isCorrect" : false , "comment" : "틀린 이유 설명"}
    	 * 형식의 json 문자열을 Gemini 가 응답하도록 프로그램밍 해보세요
    	 */
    	String str = """
    		질문: %s
		    답변: %s
		    
		    주어진 질문과 답변을 평가하고 반드시 JSON 형식으로만 응답하세요.
		    
		    질문에 대한 답변이 정확하면 다음 JSON을 그대로 반환하세요.단, "code 피드백"은
		    답변에 대한 피드백을 넣어주세요:
		    {
		      "isCorrect": true,
		      "comment": "code 피드백"
		    }
		    
		    오답이라면 다음 JSON을 그대로 반환하세요:
		    {
		      "isCorrect": false,
		      "comment": "틀린 이유 설명"
		    }
		
		    오직 위의 JSON 형식만 출력하세요.
		    JSON 이외의 텍스트를 포함하지 마세요.
		    Markdown 형식으로 출력하지 마세요.
		    "틀린 이유 설명" 에는 수정된 코드도 제시하세요.
            """.formatted(map.get("quiz"), map.get("answer"));
    	return getChatResponse(str);
    }
    
    
    public Mono<String> quiz(Map<String, String> map){
    	/*
    	 * map 에는 quiz 와 answer 가 담겨있다
    	 * 
    	 * Gemini api 의 요청을 통해서 해당 answer 가 맞으면
    	 * { "isCorrect" : true }
    	 * 을리면
    	 * {"isCorrect" : false}
    	 * 형식의 json 문자열을 Gemini 가 응답하도록 프로그램밍 해보세요
    	 */
    	String str = """
    		문제의 답이 맞는지 확인해서 맞으면 true, 틀리면 false 라고 형식에 맞게 대답해봐.
            언어의 종류는 javascript 이고, 이 문법을 엄격하게 확인해.
            맞거나 틀린 이유는 설명하지마.
            내가 만든 형식을 정확하게 따라 대답해야해.
            markdown 형식으로 응답하지 마
                
            문제: "%s"
                
            답: "%s"
                
            형식: {"isCorrect": true or false}
                
            """.formatted(map.get("quiz"), map.get("answer"));
    	return getChatResponse(str);
    }
    

    public Mono<String> getFoodCategory(String food) {
        String str = """
            클라이언트가 입력한 음식: "%s"
            
            해당 음식의 카테고리를 JSON 형식으로 반환해.
            응답은 아래 형식을 따라야 해:
            { "category": "한식" }
            
            ["한식", "중식", "일식", "양식", "기타"] 중 하나만 "category" 값으로 넣어줘.
            설명 없이 JSON 객체만 반환해.
            markdown 형식으로 응답하면 안되.
        """.formatted(food);

        return getChatResponse(str);
    }
    
    public String getChatSync(String prompt) {
    	//GeminiRequest 구성하기 
        GeminiRequest request = new GeminiRequest();
        GeminiRequest.Content content = new GeminiRequest.Content();
        GeminiRequest.Part part = new GeminiRequest.Part();
        //part 에 질문을 담는다. 
        part.setText(prompt);
        content.setParts(List.of(part));
        request.setContents(List.of(content));

        String result =  webClient.post()
                .uri(uriBuilder -> uriBuilder.path(":generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request) // Map 객체 대신에 GeminiRequest 객체를 넣어주면 json 으로 변경된다.
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(responseBody -> System.out.println(responseBody))
                .flatMap(responseBody -> {
                    try {
                        return Mono.just(extractResponse(responseBody));
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("JSON 파싱 오류", e));
                    }
                }).block(); //block 메소드를 호출하면서 결과 문자열을 받아올 때 까지 기다린다.
        return result;
    } 
    
    public Mono<String> getChatResponse(String prompt) {
    	//GeminiRequest 구성하기 
        GeminiRequest request = new GeminiRequest();
        GeminiRequest.Content content = new GeminiRequest.Content();
        GeminiRequest.Part part = new GeminiRequest.Part();
        //part 에 질문을 담는다. 
        part.setText(prompt);
        content.setParts(List.of(part));
        request.setContents(List.of(content));

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path(":generateContent")
                        .queryParam("key", apiKey)
                        .build())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request) // Map 객체 대신에 GeminiRequest 객체를 넣어주면 json 으로 변경된다.
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(responseBody -> System.out.println(responseBody))
                .flatMap(responseBody -> {
                    try {
                    	String result = extractResponse(responseBody);
                    	//만일 문자열의 암과 뒤에 ```json, ``` 이 존재한다면 제거하기
                    	String escaped = result.replaceAll("^```json\\s*","")
                    			.replaceAll("\\s*```$","");
                        return Mono.just(escaped);
                    } catch (Exception e) {
                        return Mono.error(new RuntimeException("JSON 파싱 오류", e));
                    }
                });
    }
    //응답된 json 을 파싱해서 문자열 얻어내는 메소드 
    private String extractResponse(String responseJson) {
        try {
            GeminiResponse geminiResponse = gson.fromJson(responseJson, GeminiResponse.class);

            return Optional.ofNullable(geminiResponse)
                    .map(GeminiResponse::getCandidates)
                    .filter(candidates -> !candidates.isEmpty())
                    .map(candidates -> candidates.get(0))
                    .map(GeminiResponse.Candidate::getContent)
                    .map(GeminiResponse.Content::getParts)
                    .filter(parts -> !parts.isEmpty())
                    .map(parts -> parts.stream().map(GeminiResponse.Part::getText).collect(Collectors.joining("\n")))
                    .orElse("응답 없음");

        } catch (JsonSyntaxException e) {
            return "JSON 파싱 오류: " + e.getMessage();
        }
    }
}