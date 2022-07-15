package com.homel.demo.project.mapper;

import com.homel.demo.project.dto.ReviewDTO;
import com.homel.demo.project.entity.Review;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO dto(Review entity);
    List<ReviewDTO> dtos(List<Review> entity);
    Review entity(ReviewDTO dto);
    List<Review> entities(List<ReviewDTO> dto);
}
