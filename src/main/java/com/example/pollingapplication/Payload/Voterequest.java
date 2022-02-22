package com.example.pollingapplication.Payload;

import javax.validation.constraints.NotNull;

public class Voterequest {

    @NotNull
    private Long choiceId;

    public Long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }
}
