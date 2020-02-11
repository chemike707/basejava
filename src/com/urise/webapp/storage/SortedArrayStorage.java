package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void conditionUpdate(Resume resume, int index) {
        if (index >= 0) {
            storage[index] = resume;
            sorting(storage);
        } else System.out.println("Resume not found " + resume.getUuid());
    }

    @Override
    public void conditionSave(Resume resume, int index) {
        if (index < 0) {
            storage[size] = resume;
            size++;
            sorting(storage);
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full " + resume.getUuid());
        } else System.out.println("Resume is already present " + resume.getUuid());
    }

    @Override
    protected void conditionDelete(String uuid, int index) {
        if (index >= 0) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
            sorting(storage);
        } else System.out.println("Resume not found " + uuid);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void sorting(Resume[] storage) {
        for (int i = 1; i < size; i++) {
            Resume newElement = storage[i];
            int index = Arrays.binarySearch(storage, 0, i, newElement);
            if (index < 0) {
                index = -(index) - 1;
            }
//            System.out.println(index);
            System.arraycopy(storage, index, storage, index + 1, i - index);
            storage[index] = newElement;
        }
    }
}
