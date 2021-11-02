package fx.spring02;

import fx.spring01.Student;
import lombok.Data;

import java.util.List;

@Data
public class Klass {

    List<Student> students;

    public void dong() {
        System.out.println(this.getStudents());
    }

}
