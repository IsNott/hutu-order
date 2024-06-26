package org.nott.web.controller;

import org.nott.service.service.IBizCommentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author nott
 * @since 2024-06-13
 */
@RestController
@RequestMapping("/bizComment")
public class BizCommentController {

    @Resource
    private IBizCommentService bizCommentService;

}
