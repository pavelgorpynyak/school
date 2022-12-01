package ruhogwartsschool.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.repository.StudentRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
public class InfoService {

    private final StudentRepository studentRepository;

    public InfoService( StudentRepository studentRepository ) {
        this.studentRepository = studentRepository;
    }

    private static final Logger LOG = LoggerFactory.getLogger(InfoService.class);

    public void testParallelStream() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("plaim stream");
        Stream.iterate(1L, a -> a + 1)
                .limit(10_000L)
                .reduce(0L, ( a, b ) -> {
                    long s = 0;
                    for (int i = 0; i < a + b; i++) {
                        s += 1;
                    }
                    return s;
                });
        stopWatch.stop();
        stopWatch.start("parallel stream");
        long sum = Stream.iterate(1L, a -> a + 1)
                .limit(10_000L)
                .parallel()
                .reduce(0L, ( a, b ) -> {
                    long s = 0;
                    for (int i = 0; i < a + b; i++) {
                        s += 1;
                    }
                    return s;
                });
        stopWatch.stop();
        LOG.info("Calculated value is {} {}", sum, stopWatch.prettyPrint());
    }

    public void printStudents() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudents(students.subList(0,2));
        new Thread(()->printStudents(students.subList(2,4))).start();
    }

    private void printStudents( List<Student> students ) {
        for (Student student : students) {
            LOG.info(student.getName());
        }
    }

    private synchronized void printStudentsSynch( List<Student> students ) {
        for (Student student : students) {
            LOG.info(student.getName());
        }
    }

    public void printStudentsSynch() {
        List<Student> students = studentRepository.findAll(PageRequest.of(0, 6)).getContent();

        printStudentsSynch(students.subList(0,2));
        new Thread(()->printStudentsSynch(students.subList(2,4))).start();
    }
}
