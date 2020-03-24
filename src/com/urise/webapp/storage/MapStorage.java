package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new HashMap<>();

    protected Object getKey(String uuid) {
        Resume resume = new Resume(uuid);
        if (storage.containsKey(resume.getUuid())) {
            return resume.getUuid();
        }
        return null;
    }

    @Override
    protected boolean existIndex(Object index) {
        return index != null;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage.put((String) index, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove(index);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get(index);
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
