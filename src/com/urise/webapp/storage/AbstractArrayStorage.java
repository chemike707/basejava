package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void updateResume(Resume resume, int index) {
        storage[index] = resume;
    }

    protected void saveResume(Resume resume, int index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        saveR(resume, index);
        size++;
    }

    protected void deleteResume(int index) {
        deleteR(index);
        storage[size - 1] = null;
        size--;
    }

    protected void clearStorage() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected Resume getResume(int index) {
        return storage[index];
    }

    protected Resume[] getAllResume() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveR(Resume resume, int index);

    protected abstract void deleteR(int index);
}