package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapUuidStorage extends AbstractStorage<String> {
    private Map<String, Resume> storage = new HashMap<>();

    protected String getSearchKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateResume(Resume resume, String searchKey) {
        storage.put( searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, String searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(String searchKey) {
        storage.remove( searchKey);
    }

    @Override
    protected Resume getResume(String searchKey) {
        return storage.get( searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
