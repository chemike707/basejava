package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {

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

    private SK checkNotExistStorageException(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK checkExistStorageException(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getAll();
        Collections.sort(list);
        return list;
    }

    protected abstract List<Resume> getAll();

    protected abstract SK getSearchKey(String uuid);

    protected abstract void updateResume(Resume resume, SK searchKey);

    protected abstract void saveResume(Resume resume, SK searchKey);

    protected abstract void deleteResume(SK searchKey);

    protected abstract Resume getResume(SK searchKey);

    protected abstract boolean isExist(SK searchKey);
}