package com.library.library.configuration;

import com.library.library.dto.response.BookResponse;
import com.library.library.entity.Book;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);

        TypeMap<Book, BookResponse> bookTypeMap = modelMapper.createTypeMap(Book.class, BookResponse.class);
        bookTypeMap.addMapping(src->src.getAuthor().getId(), BookResponse:: setAuthorId);
        return modelMapper;
    }
}
