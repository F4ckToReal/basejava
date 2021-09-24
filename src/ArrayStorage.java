
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];
    int size;

    void clear() {
        size = size();
        Arrays.fill(storage, 0, size, null);
    }

    void save(Resume r) {

        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
            if ((storage[i].toString().equals(r.toString()))) {
                System.out.println("РЕЗЮМЕ С ТАКИМ ИМЕНЕМ УЖЕ ЕСТЬ В БАЗЕ");
                break;
            }
        }
    }

    Resume get(String uuid) {
        size = size();
        for (int i=0; i<size; i++) {
            if (!storage[i].toString().equals(uuid)) {
                System.out.println("Нет в базе");
                break;
            }
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int size = size();
        int i;
        for (i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid))
                break;
        }
        for (int k = i; k < storage.length - 1; k++) {
            storage[k] = storage[k + 1];
            size--;
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        ArrayList<Resume> list = new ArrayList<>();
        for (Resume resume : storage) {
            if (resume != null) {
                list.add(resume);
            }
        }
        return list.toArray(new Resume[0]);
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            }
        }
        return count;
    }
}
