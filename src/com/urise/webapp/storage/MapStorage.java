package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    SortedMap<String, Resume> storage = new TreeMap<>();

    protected String getSearchKey(String uuid) {
        if (storage.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    @Override
    protected boolean existIndex(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object searchKey) {
        storage.remove((String) searchKey);
    }

    @Override
    protected Resume getResume(Object searchKey) {
        return storage.get((String) searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
