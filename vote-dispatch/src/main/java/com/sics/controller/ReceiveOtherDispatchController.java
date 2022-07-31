package com.sics.controller;

import com.sics.pojo.BasePojoImpl;
import com.sics.pojo.DisPatchToOtherDisPatchOrServer;
import com.sics.service.ReceiveOtherDispatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Accept distribution of other dispatches
 *
 * @author liangjc
 * @version 2022/07/27
 */
@RestController
public class ReceiveOtherDispatchController {
    @Resource
    ReceiveOtherDispatchService receiveOtherDispatchService;
    @PostMapping("/dispatch/cipher-or-password")
    public BasePojoImpl linkServer(@RequestBody DisPatchToOtherDisPatchOrServer disPatchToOtherDisPatchOrServer){
        return receiveOtherDispatchService.linkServer(disPatchToOtherDisPatchOrServer);
    }
}
