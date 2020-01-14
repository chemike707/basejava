import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage, null);
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume r = null;
        for (int i = 0; i < storage.length; i++) {
            if (uuid.equals(storage[i].uuid)) {
                r = storage[i];
                break;
            }
        }
        return r;
    }

    void delete(String uuid) {
        for (Resume resume : storage) {
            if (uuid.equals(resume.uuid)) {
                resume.uuid = null;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int size = 1;
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] != null) {
                size += i;
            }
        }
        return size;
    }
}