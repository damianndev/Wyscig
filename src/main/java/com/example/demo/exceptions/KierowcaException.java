package com.example.demo.exceptions;

import com.example.demo.model.TypKierowcy;

import java.text.MessageFormat;

public class KierowcaException extends RuntimeException {
    public KierowcaException(TypKierowcy typKierowcy){
        super(MessageFormat.format("Kierowca {0} nie został stworzony ",  typKierowcy));
    }
}

//TODO: zrobić exception dla samochodu, który będzie łapany przy tworzeniu (samochód serwis)