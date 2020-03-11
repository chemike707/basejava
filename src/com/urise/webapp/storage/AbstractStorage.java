package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        if (checkIndex(resume.getUuid())) {
            updateResume(resume, getIndex(resume.getUuid()));
        } else throw new NotExistStorageException(resume.getUuid());
    }

    public void save(Resume resume) {
        if (checkIndex(resume.getUuid())) {
            throw new ExistStorageException(resume.getUuid());
        } else saveResume(resume, getIndex(resume.getUuid()));
    }

    public void delete(String uuid) {
        if (checkIndex(uuid)) {
            deleteResume(getIndex(uuid));
        } else throw new NotExistStorageException(uuid);
    }

    public void clear() {
        clearStorage();
    }

    public Resume get(String uuid) {
        if (!checkIndex(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return getResume(getIndex(uuid));
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    protected boolean checkIndex(String uuid) {
        int index = getIndex(uuid);
        return index >= 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume resume, int index);

    protected abstract void saveResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

    protected abstract void clearStorage();

    protected abstract Resume getResume(int index);

    protected abstract Resume[] getAllResume();
}
