package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    protected Object getKey(String  uuid) {
        Resume resume = new Resume(uuid);
        for (int i = 0; i < storage.size(); i++) {
            if (resume.getUuid() == storage.get(i).getUuid()) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean existIndex(Object index) {
        return index != null;
    }

    @Override
    protected void updateResume(Resume resume, Object index) {
        storage.set((Integer) index, resume);
    }

    @Override
    protected void saveResume(Resume resume, Object index) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(Object index) {
        storage.remove((Integer) index);
    }

    @Override
    protected Resume getResume(Object index) {
        return storage.get((Integer) index);
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
