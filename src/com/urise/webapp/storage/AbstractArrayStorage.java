package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void updateResume(Resume resume, Integer index) {
        storage[ index] = resume;
    }

    protected void saveResume(Resume resume, Integer index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addResume(resume,  index);
        size++;
    }

    protected void deleteResume(Integer index) {
        removeResume( index);
        storage[size - 1] = null;
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected Resume getResume(Integer index) {
        return storage[ index];
    }

    public int size() {
        return size;
    }

    public List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOfRange(storage, 0, size));
    }

    protected boolean isExist(Integer index) {
        return  index >= 0;
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void removeResume(int index);
}