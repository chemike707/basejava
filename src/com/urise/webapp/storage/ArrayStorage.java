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
        if (checkResume(resume.getUuid()) != -1) {
            storage[checkResume(resume.getUuid())] = resume;
        } else System.out.println("Resume not found " + resume.getUuid());

    }

    public int checkResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }


    public void save(Resume resume) {
        if (checkResume(resume.getUuid()) == -1 && size < storage.length) {
            size++;
            for (int i = 0; i < size; i++) {
                if (resume != storage[i]) {
                    storage[size - 1] = resume;
                    break;
                }
            }
        } else System.out.println("Storage is full " + resume.getUuid());
    }

    public Resume get(String uuid) {
        if (checkResume(uuid) != -1) {
            return storage[checkResume(uuid)];
        }
        System.out.println("Resume not found " + uuid);
        return null;
    }

    public void delete(String uuid) {
        if (checkResume(uuid) != -1) {
            storage[checkResume(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else System.out.println("Resume not found " + uuid);
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