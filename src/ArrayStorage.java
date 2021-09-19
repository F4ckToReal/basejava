
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        Arrays.fill(storage,null);
    }

    void save(Resume r) {
        for (int i=0; i< storage.length; i++) {
            if (storage[i] == null){
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid)  {
        Resume resum = new Resume();
        for ( Resume resume: storage) {
            if (resume.toString().equals(uuid)) {
                resum = resume;
            }
        }
        return resum;
    }

    void delete(String uuid) {
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
