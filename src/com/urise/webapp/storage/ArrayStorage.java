package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else System.out.println("Resume not found " + resume.getUuid());

    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            storage[size] = resume;
            size++;
        } else if (size == storage.length) {
            System.out.println("Storage is full " + resume.getUuid());
        } else System.out.println("Resume is already present " + resume.getUuid());
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (getIndex(uuid) != -1) {
            return storage[index];
        }
        System.out.println("Resume not found " + uuid);
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else System.out.println("Resume not found " + uuid);
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}