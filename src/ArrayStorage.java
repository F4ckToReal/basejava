
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10];

    void clear() {
        Arrays.fill(storage,null);
    }

    void save(Resume r) {
        try {
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
        } catch (NullPointerException ex){
            ex.printStackTrace();
        }
    }

    Resume get(String uuid) {
        Resume resum = new Resume();
        try {
            for (Resume resume : storage) {
                if (resume.toString() == null)
                    break;
                if (resume.toString().equals(uuid)) {
                    resum = resume;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return resum;
    }

    void delete(String uuid) {
        int size = size();
        try  {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].toString().equals(uuid)) {
                    storage[i] = null;
                        for (int k = i; k < storage.length - 1; k++) {
                            if (storage[k + 1] != null) {
                                storage[i] = storage[k + 1];
                                storage[k + 1] = null;
                                break;
                            }
                        }
                    }
                }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }



    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        ArrayList <Resume> list = new ArrayList<>();
        for (Resume resume : storage) {
            if (resume != null) {
                list.add(resume);
            }
        }
        return list.toArray(new Resume[0]);
    }

    int size() {
        int count = 0;
        for (Resume resume: storage) {
            if (resume != null){
                count++;
            }
        }
        return count;
    }
}
