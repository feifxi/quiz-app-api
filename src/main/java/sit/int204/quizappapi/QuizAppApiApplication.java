package sit.int204.quizappapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sit.int204.quizappapi.utils.ListMapper;

@SpringBootApplication
public class QuizAppApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizAppApiApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public ListMapper listMapper() {
        return ListMapper.getInstance();
    }
}
