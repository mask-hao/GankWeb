package com.zhanghao.web;

import com.zhanghao.entity.GankSearchItem;
import com.zhanghao.model.CommonResponse;
import com.zhanghao.util.Constant;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghao on 2017/5/5.
 */
@Controller
@RequestMapping(value = "/api")
public class SearchController {

    private final String BASE_SEARCH_URL="http://gank.io/search?q=";

    @RequestMapping(value = "/search/{words}",method = RequestMethod.GET)
    public @ResponseBody
    CommonResponse<List<GankSearchItem>> getSearchResult(
            @PathVariable("words")String words){

        CommonResponse<List<GankSearchItem>> commonResponse=new CommonResponse<>();
        List<GankSearchItem> list=new ArrayList<>();
        try {
            Document document= Jsoup.connect(BASE_SEARCH_URL+words)
                    .timeout(10*1000)
                    .get();
            Elements elements=document.getElementsByClass("container content").select("ol>li");
            for (Element element : elements) {
               Element rowE= element.getElementsByClass("row").first();
               Element urlE = rowE.getElementsByTag("a").first();
               String url = urlE.attr("href");
               String title = urlE.text();
               String type = rowE.select("small").first().text();
               String who=rowE.getElementsByClass("u-pull-right").text();
                GankSearchItem item=new GankSearchItem(url,type,who,title);
                list.add(item);
            }
            if (list.size()==0){
                commonResponse.setResult(Constant.NO_MORE_DATA);
                commonResponse.setContent(null);
                return commonResponse;
            }else{
                commonResponse.setResult(Constant.GET_SEARCH_DATA_SUCCESS);
                commonResponse.setContent(list);
                return commonResponse;
            }

        } catch (IOException e) {
            e.printStackTrace();
            commonResponse.setResult(Constant.GET_SEARCH_DATA_ERROR);
            commonResponse.setContent(null);
            return commonResponse;
        }
    }
}
