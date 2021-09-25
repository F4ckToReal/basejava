
import java.beans.beancontext.BeanContextServiceRevokedEvent;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int size = 0;

    void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void save(Resume r) {
        int count = 0;
        if (r.toString() != null) {
            for (int i = 0; i < size; i++) {
                if (storage[i].toString().equals(r.toString())) {
                    System.out.println("Резюме с таким UUID присутствует в базе");
                    count++;
                }
            }
            if (count == 0) {
                storage[size] = r;
                size++;
            }
        } else System.out.println("Введите uuid!");
    }


    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }


    void delete(String uuid) {
        int i;
        for (i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                size--;
                break;
            }
        }
        for (int k = i + 1; k <= size; k++) {
            storage[k - 1] = storage[k];
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, allResume, 0, size);
        return allResume;
    }

    int size() {
        return size;
    }
}

