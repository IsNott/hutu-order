package org.nott.service.api.impl;

import org.nott.feign.OssClient;
import org.nott.model.BizComment;
import org.nott.service.mapper.api.BizCommentMapper;
import org.nott.service.api.IBizCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author nott
 * @since 2024-06-13
 */
@Service
public class BizCommentServiceImpl extends ServiceImpl<BizCommentMapper, BizComment> implements IBizCommentService {

}
