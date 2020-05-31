package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {
    private Map<String, Resume> storage = new HashMap<>();

    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    protected void updateResume(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void saveResume(Resume resume, Resume searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void deleteResume(Resume resume) {
        storage.remove((resume).getUuid());
    }

    @Override
    protected Resume getResume(Resume resume) {
        return  resume;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> list = new ArrayList<>(storage.values());
        Collections.sort(list);
        return list;
    }

    @Override
    public int size() {
        return storage.size();
    }
}
