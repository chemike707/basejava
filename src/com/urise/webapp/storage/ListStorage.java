package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    protected List<Resume> storage = new ArrayList<>();

    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Integer searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateResume(Resume resume, Integer searchKey) {
        storage.set(searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Integer index) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(Integer index) {
        storage.remove((int) index);
    }

    @Override
    protected Resume getResume(Integer index) {
        return storage.get(index);
    }

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }
}
