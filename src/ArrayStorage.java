
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
        try {

            for (Resume resume : storage) {
                if(!resume.toString().equals(uuid)){
                    System.out.println("Нет в базе");
                    break;
                }
                if (resume.toString().equals(uuid)) {
                    return resume;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    void delete(String uuid) {
        int size = size();
        int i;
        try  {
            for (i = 0; i < size; i++) {
                if (storage[i].toString().equals(uuid))
                    break;
                }
                        for (int k = i; k < storage.length- 1; k++) {
                                storage[k] = storage[k + 1];
                                size--;
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
