package org.nott.service.oss;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nott.common.config.WebConfig;
import org.nott.common.utils.HutuUtils;
import org.nott.dto.OssFileDTO;
import org.nott.model.OssFile;
import org.nott.service.mapper.oss.OssFileMapper;
import org.nott.service.oss.OssFileService;
import org.nott.vo.OssFileVo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nott
 * @date 2025-3-18
 */

@Service
public class OssFileService extends ServiceImpl<OssFileMapper, OssFile> {

    @Resource
    private WebConfig webConfig;


    public OssFileVo upload(MultipartFile file, Long bizId) throws Exception{
        String basePath = webConfig.getBasePath();
        String uploadPath = webConfig.getUploadPath();
        String proxySuffix = webConfig.getProxySuffix();
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
        ossFile.setDelFlag(false);
        ossFile.setPrefix(type);
        if(HutuUtils.isNotEmpty(bizId)){
            ossFile.setBizId(bizId);
        }
        ossFile.setFileName(storeName);
        ossFile.setUrl(proxySuffix + storeName);
        this.save(ossFile);
        return HutuUtils.transToObject(ossFile, OssFileVo.class);
    }


    public void deleteByBizId(Long bizId) {
        this.deleteByBizId(Collections.singletonList(bizId));
    }


    public void deleteByBizId(List<Long> bizId) {
        this.lambdaUpdate().in(OssFile::getBizId, bizId).set(OssFile::getDelFlag, 1).update();
    }


    public List<OssFileVo> getByBizId(Long bizId) {
       return getByBizId(Collections.singletonList(bizId));
    }


    public List<OssFileVo> getByBizId(List<Long> bizId) {
        if(HutuUtils.isEmpty(bizId)){
            return new ArrayList<>();
        }
        List<OssFile> list = this.lambdaQuery().in(OssFile::getBizId, bizId)
                .orderByAsc(OssFile::getSortOrder)
                .list();
        return HutuUtils.transToVos(list, OssFileVo.class);
    }


    public void deleteById(Long id) {
        OssFile ossFile = this.getById(id);
        HutuUtils.requireNotNull(ossFile, "文件不存在");
        String uploadPath = webConfig.getUploadPath();
        String basePath = webConfig.getBasePath();
        String proxySuffix = webConfig.getProxySuffix();
        String fullPath = basePath + uploadPath;
        File file = new File(fullPath + ossFile.getUrl().replace(proxySuffix, ""));
        file.deleteOnExit();
        super.removeById(id);

    }

    public void relateFile(List<OssFileDTO> fileDTOS, Long bizId) {
        List<OssFileVo> fileVos = this.getByBizId(bizId);
        for(OssFileVo fileVo : fileVos){
            this.deleteById(fileVo.getId());
        }
        if(HutuUtils.isEmpty(fileDTOS)){
            return;
        }
        for (int i = 0; i < fileDTOS.size(); i++) {
            OssFileDTO ossFileDTO = fileDTOS.get(i);
            ossFileDTO.setBizId(bizId);
            if(HutuUtils.isEmpty(ossFileDTO.getSortOrder())){
                ossFileDTO.setSortOrder(i + 1);
            }
            OssFile ossFile = HutuUtils.transToObject(ossFileDTO, OssFile.class);
            this.updateById(ossFile);
        }
    }
}
