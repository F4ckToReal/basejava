
import java.util.ArrayList;
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
        if (r.toString() != null) {
            for (int i = 0; i < size + 1; i++) {
                if (storage[i] == null) {
                    storage[i] = r;
                    size++;
                    break;
                }
                if ((storage[i].toString().equals(r.toString()))) {
                    System.out.println("РЕЗЮМЕ С ТАКИМ ИМЕНЕМ УЖЕ ЕСТЬ В БАЗЕ");
                    break;
                }
            }
        } else System.out.println("Введите имя!");
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
            if (!storage[i].toString().equals(uuid)) {
                System.out.println("Нет в базе");
                break;
            }
        }
        return null;
    }


    void delete(String uuid) {
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
        return size;
    }
}

