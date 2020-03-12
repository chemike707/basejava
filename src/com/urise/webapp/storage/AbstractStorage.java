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

    public void clear() {
        clearStorage();
    }

    public Resume get(String uuid) {
        return getResume(checkNotExistStorageException(uuid));
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    private int checkNotExistStorageException(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    private int checkExistStorageException(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    protected abstract void clearStorage();

    protected abstract Resume getResume(int index);

    protected abstract Resume[] getAllResume();
}