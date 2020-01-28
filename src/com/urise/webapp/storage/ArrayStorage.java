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
        if (checkResume(resume)) {
            for (int i = 0; i < size; i++) {
                if (resume.getUuid().equals(storage[i].getUuid())) {
                    storage[i] = resume;
                }
            }
        } else System.out.println("Resume not found");
    }

    private boolean checkResume(Resume resume) {
        for (int i = 0; i < size; i++) {
            if (resume.getUuid().equals(storage[i].getUuid())) {
                return true;
            }
        }
        return false;
    }



    public void save(Resume r) {
        if (!checkResume(r) && size < 10000) {
            size++;
            for (int i = 0; i < size; i++) {
                if (r != storage[i]) {
                    storage[size - 1] = r;
                    break;
                }
            }
        } else System.out.println("Storage is full");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return storage[i];
            }
        }
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
            }
        }
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