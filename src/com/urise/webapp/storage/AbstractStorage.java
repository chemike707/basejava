package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            updateResume(resume, index);
        } else throw new NotExistStorageException(resume.getUuid());
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else saveResume(resume, index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteResume(index);
        } else throw new NotExistStorageException(uuid);
    }

    public void clear() {
        clearStorage();
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(index);
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    protected abstract void clearStorage();

    protected abstract Resume getResume(int index);

    protected abstract Resume[] getAllResume();
}
