package paldo_bottle.backend.DTO.challenge;

import lombok.Builder;
import lombok.Data;

@Data
public class AllChallengeParam {
    String name;
    String description;
    Long point;
    Boolean isAchieved;

    @Builder
    public AllChallengeParam(String name, String description, Long point, Boolean isAchieved){
        this.name = name;
        this.description = description;
        this.point = point;
        this.isAchieved = isAchieved;
    }

    public AllChallengeParam() {

    }
}
