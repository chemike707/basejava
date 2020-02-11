package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    protected void conditionUpdate(Resume resume, int index) {
        if (index != -1) {
            storage[index] = resume;
        } else System.out.println("Resume not found " + resume.getUuid());
    }

    protected void conditionSave(Resume resume, int index) {
        if (index == -1) {
            storage[size] = resume;
            size++;
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full " + resume.getUuid());
        } else System.out.println("Resume is already present " + resume.getUuid());
    }

    protected void conditionDelete(String uuid, int index) {
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else System.out.println("Resume not found " + uuid);
    }

}