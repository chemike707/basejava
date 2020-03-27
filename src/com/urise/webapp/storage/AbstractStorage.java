package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        updateResume(resume, checkNotExistStorageException(resume.getUuid()));
    }

    public void save(Resume resume) {
        saveResume(resume, checkExistStorageException(resume.getUuid()));
    }

    public void delete(String uuid) {
        deleteResume(checkNotExistStorageException(uuid));
    }

    public Resume get(String uuid) {
        return getResume(checkNotExistStorageException(uuid));
    }

    private Object checkNotExistStorageException(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!existIndex(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object checkExistStorageException(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (existIndex(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object index);

    protected abstract Resume getResume(Object index);

    protected abstract boolean existIndex(Object index);
}