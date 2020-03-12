package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected List<Resume> storage = new ArrayList<>();

    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        for (int i = 0; i < storage.size(); i++) {
            if (resume.hashCode() == storage.get(i).hashCode()) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void updateResume(Resume resume, int index) {
        storage.set(index, resume);
    }

    @Override
    protected void saveResume(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected void deleteResume(int index) {
        storage.remove(index);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected Resume[] getAllResume() {
        Resume[] resumes = storage.toArray(new Resume[0]);
        return Arrays.copyOf(resumes, storage.size());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
