package com.example.want.api.block.domain;

import com.example.want.api.block.dto.BlockDetailRsDto;
import com.example.want.api.block.dto.AddDateBlockRqDto;
import com.example.want.api.project.domain.Project;
import com.example.want.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Block extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String placeName;
    @Enumerated(EnumType.STRING)
    private Category category;

    private Double latitude;
    private Double longitude;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String isActivated;
    private Long heartCount;
    private String isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    public BlockDetailRsDto toDetailDto() {
        return BlockDetailRsDto.builder()
                .blockId(this.id)
                .title(this.title)
                .content(this.content)
                .placeName(this.placeName)
                .category(this.category)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .startTime(this.startTime.toString())
                .endTime(this.endTime.toString())
                .isActivated(this.isActivated)
                .heartCount(this.heartCount)
                .build();
    }

    public void incrementHearts() {
        this.heartCount++;
    }

    public void decrementHearts() {
        this.heartCount--;
    }

    public void updatePlan(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updatePlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void updateCategory(Category category) {
        this.category = category;
    }

    public void changeIsActivated(String isActivated) {
        this.isActivated = isActivated;
    }

    public void updatePoint(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void delete(){
        this.isDeleted = "Y";
    }

    @PrePersist
    public void initializeFields() {
        this.isDeleted = "N";
    }

}
