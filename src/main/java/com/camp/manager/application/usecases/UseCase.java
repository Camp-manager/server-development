package com.camp.manager.application.usecases;

public interface UseCase<IN, OUT> {
    OUT execute(IN input);
}
