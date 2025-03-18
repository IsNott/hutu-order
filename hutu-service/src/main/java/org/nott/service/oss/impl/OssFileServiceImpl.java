package org.nott.service.oss.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.common.config.WebConfig;
import org.nott.common.utils.HutuUtils;
import org.nott.model.OssFile;
import org.nott.service.mapper.oss.OssFileMapper;
import org.nott.service.oss.IOssFileService;
import org.nott.vo.OssFileVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Nott
 * @date 2025-3-18
 */

@Service
public class OssFileServiceImpl extends ServiceImpl<OssFileMapper, OssFile> implements IOssFileService {

    @Resource
    private WebConfig webConfig;

    @Override
    public OssFileVo upload(MultipartFile file, Long bizId) throws Exception{
        String basePath = webConfig.getBasePath();
        String uploadPath = webConfig.getUploadPath();
        String fullPath = basePath + uploadPath;
        File path = new File(fullPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        long id = IdWorker.getId();
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        String storeName = id + "." + type;
        Files.copy(file.getInputStream(), Paths.get(fullPath + File.separator + storeName));
        OssFile ossFile = new OssFile();
        ossFile.setId(id);
        ossFile.setOriginName(file.getOriginalFilename());
        ossFile.setDelFlag(0);
        ossFile.setPrefix(type);
        ossFile.setBizId(bizId);
        ossFile.setFileName(storeName);
        ossFile.setPath(uploadPath + File.separator + storeName);
        this.save(ossFile);
        return HutuUtils.transToObject(ossFile, OssFileVo.class);
    }

    @Override
    public void deleteByBizId(Long bizId) {
        this.lambdaUpdate().eq(OssFile::getBizId, bizId).set(OssFile::getDelFlag, 1).update();
    }

    @Override
    public List<OssFileVo> getByBizId(Long bizId) {
        List<OssFile> list = this.lambdaQuery().eq(OssFile::getBizId, bizId)
                .eq(OssFile::getDelFlag, 0)
                .list();
        return HutuUtils.transToVos(list, OssFileVo.class);
    }
}
