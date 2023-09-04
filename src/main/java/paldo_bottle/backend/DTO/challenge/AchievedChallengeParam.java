package paldo_bottle.backend.DTO.challenge;

import lombok.Builder;
import lombok.Data;

@Data
public class AchievedChallengeParam {
    String name;
    String description;
    Long point;

    @Builder
    public AchievedChallengeParam(String name, String description, Long point){
        this.name = name;
        this.description = description;
        this.point = point;
    }

    public AchievedChallengeParam() {

    }
}
