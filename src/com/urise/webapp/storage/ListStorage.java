package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    protected Integer getSearchKey(String  uuid) {
        Resume resume = new Resume(uuid);
        for (int i = 0; i < storage.size(); i++) {
            if (resume.getUuid().equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean existIndex(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void updateResume(Resume resume, Object searchKey) {
        storage.set((int) searchKey, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove((int) index);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((int) index);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = storage.toArray(new Resume[0]);
        return Arrays.copyOf(resumes, storage.size());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
