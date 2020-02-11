package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        conditionUpdate(resume, index);
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        conditionSave(resume, index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        conditionDelete(uuid, index);
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void conditionUpdate(Resume resume, int index);

    protected abstract void conditionSave(Resume resume, int index);

    protected abstract void conditionDelete(String uuid, int index);
}