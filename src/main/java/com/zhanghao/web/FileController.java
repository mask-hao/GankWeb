package com.zhanghao.web;

import com.sun.javafx.binding.StringFormatter;
import com.zhanghao.entity.User;
import com.zhanghao.model.CommonMessage;
import com.zhanghao.service.UserService;
import com.zhanghao.util.ConfigUtil;
import com.zhanghao.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * Created by 张浩 on 2017/1/29.
 */
@Controller
@RequestMapping(value = "/api/upload")
public class FileController {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserService userService;

    @RequestMapping(value = "/userImage",method = RequestMethod.POST)
    public @ResponseBody
    CommonMessage uploadUserImage(@RequestParam("token")String token, @RequestParam("file")MultipartFile multipartFile, HttpServletRequest request){
        CommonMessage message=new CommonMessage();
        try {
            if (!token.equals("")&&!token.isEmpty()&&userService.checkUser(token)){
                if (!multipartFile.isEmpty()){
                        try {



                            String realPath= ConfigUtil.getFileStorePath();
                            int point=multipartFile.getOriginalFilename().lastIndexOf(".");
                            String type=multipartFile.getOriginalFilename().substring(point+1);
                            String fileName= UUID.randomUUID().toString()+"."+type;

                            Path filePath = Paths.get(realPath);

                            if (Files.notExists(filePath)) {
                                Files.createDirectories(filePath);
                            }


                            File saveFile=new File(realPath+"/"+fileName);
                            multipartFile.transferTo(saveFile);

                            String absolutPath=saveFile.getPath();
                            String path=absolutPath.substring(absolutPath.indexOf("resource"),absolutPath.length());

                            boolean updateUserImage=userService.updateUserImageByUserAccount(token,path);
                            String userAccount=userService.getUserByUserToken(token).getUserAccount();
                            if (updateUserImage){

                                User newUser=userService.getUserByUserAccount(userAccount);
                                userService.updateUserInUserPool(token,newUser);

                                message.setResult(Constant.FILEUPLOAD_SUCCESS);
                                message.setContent(path);
                                return message;
                            }else{
                                message.setResult(Constant.FILEUPLOAD_ERROR);
                                message.setResult(Constant.DBUPDATE_ERROR);
                                return message;
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            message.setResult(Constant.FILEUPLOAD_ERROR);
                            message.setContent(Constant.IO_ERROR);
                            return message;
                        }
                    }else{
                        message.setResult(Constant.FILEUPLOAD_ERROR);
                        message.setContent(Constant.FILE_EMPTY);
                        return message;
                    }
                }else{
                    message.setResult(Constant.USER_INVALID);
                    return message;
                }
        }catch (NullPointerException e){
            e.printStackTrace();
            message.setResult(Constant.TOKEN_EMPTY);
            return message;
        }
    }
}
