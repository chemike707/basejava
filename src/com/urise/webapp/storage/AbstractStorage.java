package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

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
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object checkExistStorageException(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = GetAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> GetAll();

    protected abstract Object getSearchKey(String uuid);

    protected abstract void updateResume(Resume resume, Object searchKey);

    protected abstract void saveResume(Resume resume, Object searchKey);

    protected abstract void deleteResume(Object searchKey);

    protected abstract Resume getResume(Object searchKey);

    protected abstract boolean isExist(Object searchKey);
}