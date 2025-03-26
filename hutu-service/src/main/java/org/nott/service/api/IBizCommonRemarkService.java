package org.nott.service.api;

import com.baomidou.mybatisplus.extension.service.IService;
import org.nott.model.BizCommonRemark;
import org.nott.vo.CommonRemarkVo;

import java.util.List;

public interface IBizCommonRemarkService extends IService<BizCommonRemark> {

    List<CommonRemarkVo> queryCommonRemark(int size);
}
