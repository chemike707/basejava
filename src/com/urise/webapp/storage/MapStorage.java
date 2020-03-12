package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new TreeMap<>();

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        if (storage.containsValue(resume)) {
            return storage.hashCode();
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, int index) {

    }

    @Override
    protected void saveResume(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(int index) {

    }

    @Override
    protected void clearStorage() {

    }

    @Override
    protected Resume getResume(int index) {
        Resume resume = new Resume();
        return storage.get(resume.getUuid());
    }

    @Override
    protected Resume[] getAllResume() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return storage.size();
    }
}
