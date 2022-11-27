package ruhogwartsschool.service;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ruhogwartsschool.model.Avatar;
import ruhogwartsschool.model.Student;
import ruhogwartsschool.repository.AvatarRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    private static final Logger LOG = LoggerFactory.getLogger(AvatarService.class);

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService( StudentService studentService, AvatarRepository avatarRepository ) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public void uploadAvatar( Long studentId, MultipartFile avatarFile ) throws IOException {
        LOG.debug("Method upload avatar");
        Student student = studentService.findStudent(studentId);
        Path filePath = Path.of(avatarsDir, student + "." + getExtentions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar( Long studentId ) {
        LOG.debug("Method find avatar");
        return avatarRepository.findByStudentId(studentId).orElse(new Avatar());
    }

    private String getExtentions( String fileName ) {
        LOG.debug("Method getExtentions");
        return fileName.substring(fileName.lastIndexOf(".")+1);
    }

}
