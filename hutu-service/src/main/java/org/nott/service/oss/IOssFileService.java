package org.nott.service.oss;

import org.nott.vo.OssFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface IOssFileService {

    /**
     * 上传文件
     * @param file
     * @param bizId
     * @return
     */
    OssFileVo upload(MultipartFile file, Long bizId) throws Exception;

    /**
     * 删除文件
     * @param bizId
     */
    void deleteByBizId(Long bizId);

    void deleteByBizId(List<Long> bizId);

    /**
     * 获取文件
     * @param bizId
     * @return
     */
    List<OssFileVo> getByBizId(Long bizId);

    List<OssFileVo> getByBizId(List<Long> bizId);

    /**
     * 删除文件
     * @param id
     */
    void deleteById(Long id);
}
