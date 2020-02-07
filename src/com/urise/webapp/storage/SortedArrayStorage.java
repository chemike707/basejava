package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        sorting(storage);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    protected void sorting(Resume[] storage) {
        for (int i = 1; i < size; i++) {
            Resume newElement = storage[i];
            int index = Arrays.binarySearch(storage, 0, i, newElement);
            if (index < 0) {
                index = -(index) - 1;
            }
            System.out.println(index);
            System.arraycopy(storage, index, storage, index + 1, i - index);
            storage[index] = newElement;
        }
    }

    protected void sortAfterAction() {
        sorting(storage);
    }

}
