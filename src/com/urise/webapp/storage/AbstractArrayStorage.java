package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void updateResume(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    protected void saveResume(Resume resume, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addResume(resume, (Integer) index);
        size++;
    }

    protected void deleteResume(Object index) {
        removeResume((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected Resume getResume(Object index) {
        return storage[(Integer) index];
    }

//    public List<Resume> getAllSorted() {
//        return Arrays.asList(Arrays.copyOf(storage, size()));
//    }

    public int size() {
        return size;
    }

    public List<Resume> GetAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

    protected abstract Object getSearchKey(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void removeResume(int index);
}