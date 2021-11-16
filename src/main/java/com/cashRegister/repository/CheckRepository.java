package com.cashRegister.repository;

public class CheckRepository {

    private static CheckRepository checkRepository;

    public synchronized static CheckRepository getCheckRepository() {
        if (checkRepository == null) {
            checkRepository = new CheckRepository();
        }
        return checkRepository;
    }



}
