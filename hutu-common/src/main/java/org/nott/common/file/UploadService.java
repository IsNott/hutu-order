package org.nott.common.file;

import lombok.extern.slf4j.Slf4j;
import org.nott.common.exception.HutuBizException;
import org.nott.common.utils.HutuUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * 文件操作组件
 * @author Nott
 * @date 2024-7-5
 */
@Slf4j
@Service
@Deprecated
public class UploadService {

    public void upload(MultipartFile file, String path) throws Exception {
        if (file.isEmpty()) {
            throw new HutuBizException("请选择需要上传的文件");
        }

        Date date = new Date();
        String md = HutuUtils.FORMAT.MD.format(date);
        String year = HutuUtils.FORMAT.YEAR.format(date);
        String dateTime = HutuUtils.FORMAT.DATETIME.format(date);
        path += File.separator + year + File.separator + md;

        String originalFilename = file.getOriginalFilename();
        HutuUtils.requireNotNull(originalFilename, "上传文件名称为空，请检查");
        String type = getFileExtension(originalFilename);

        String fileName = dateTime + "." + type;

        File readyToUploadPath = new File(path);
        if (!readyToUploadPath.exists()) {
            readyToUploadPath.mkdirs();
        }

        Path uploadPath = Paths.get(path).resolve(fileName);

        Files.copy(file.getInputStream(), uploadPath);

        log.info("上传文件{}至{}，新名称{}", originalFilename, path, fileName);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public void downLoad(HttpServletResponse response, String path) throws Exception {
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        String fileName = path.substring(path.lastIndexOf(File.separator) + 1);
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        // 读取文件
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        FileInputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file);
            // 写入响应流
            outputStream = response.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }


    }


}
